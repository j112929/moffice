/**
 * 宝龙电商
 * com.plocc.moffice.service.impl
 * ProductServiceImpl.java
 * <p/>
 * 2016-02-19
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.plocc.moffice.client.ProductClient;
import com.plocc.moffice.client.SKUsClient;
import com.plocc.moffice.entity.AttachmentEntity;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.MoProductRelationEntity;
import com.plocc.moffice.entity.ProductEntity;
import com.plocc.moffice.entity.SKUInfoEntity;
import com.plocc.moffice.entity.VendorEntity;
import com.plocc.moffice.entity.VendorInfoEntity;
import com.plocc.moffice.repository.MoProductRelationRepository;
import com.plocc.moffice.repository.MofficeRedisRepository;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.InventoryCountingService;
import com.plocc.moffice.service.ProductService;
import com.plocc.moffice.service.SKUsService;
import com.plocc.moffice.service.StandardPriceListService;
import com.plocc.moffice.service.VendorsService;
import com.plocc.moffice.service.WarehouseService;
import com.plocc.moffice.support.Constants;

/**
 * ProductServiceImpl
 *
 * @author chenyong
 * @version 1.0.0
 * @time 16:12
 * @email chenyong2@powerlong.com
 * @description 产品接口实现类
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    
    @Autowired
    private ProductClient productClient;
    @Autowired
    private SKUsClient skusClient;
    
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private SKUsService skusService;
    @Autowired
    private VendorsService vendorsService;
    @Autowired
    private InventoryCountingService inventoryCountingService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private StandardPriceListService standardPriceListService;
    
    @Autowired
    private MoProductRelationRepository moProductRelationRepository;
    @Autowired
    private MofficeRedisRepository mofficeRedisRepository;
    
    @Autowired
    @Qualifier("mofficeStringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    @Qualifier("mofficeObjectRedisTemplate")
    private RedisOperations redisOperations;
    
    
    @Override
    public Integer count(CredentialEntity credential, Map<String, Object> params) throws Exception {
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        return productClient.count(credentialMaster, params);
    }

    @Override
    public ProductEntity findById(String productId) throws Exception {
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        ProductEntity productEntity = getProductFromRedis(String.valueOf(productId));
        if (null == productEntity) {
            productEntity = productClient.findById(credentialMaster, productId);
            productEntity = getProductPriceAndInStockQuantity(credentialMaster, productEntity);
            this.setProductToRedis(productEntity);
        }
        return productEntity;
    }

    @Override
    public void update(String appId, ProductEntity productEntityChildren) throws Exception {
        // 主账号信息
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        String proChildrenIdStr = productEntityChildren.getId();
        // 根据主子帐号的产品对应关系查询出 主账号的产品id
        MoProductRelationEntity relationEntity = moProductRelationRepository.findByAppIdAndProductChildrenId(appId, proChildrenIdStr);
        // 如果没有则是新增
        if (null == relationEntity) {
            this.add(appId, productEntityChildren);
            return;
        }
        
        // 修改主账号信息
        ProductEntity productEntityMaster = getProductFromRedis(relationEntity.getProductMasterId());
        productEntityMaster.setId(relationEntity.getProductMasterId());
        productEntityMaster.setName(productEntityChildren.getName()); // 产品名称
        productEntityMaster.setBarCode(productEntityChildren.getBarCode()); // 条形码
        productEntityMaster.setType(productEntityChildren.getType()); // 类型
        
        // 主图片附件
        AttachmentEntity attachmentChildren = productEntityChildren.getMainImage();
        if (null != attachmentChildren) {
            CredentialEntity credentialChid = credentialService.getCredentialByAppId(appId);
            AttachmentEntity attachmentDetail = productClient.findAttachmentById(credentialChid, proChildrenIdStr, attachmentChildren.getId());
            attachmentDetail.setId(null);
            productEntityMaster.setMainImage(attachmentDetail);
        }
        productClient.update(credentialMaster, productEntityMaster, productEntityMaster.getId());
        
        // 修改产品信息放到redis
        this.setProductToRedis(productEntityMaster);
    }

    @Override
    public void delete(String appId, String productId) throws Exception {
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        // 根据主子帐号的产品对应关系查询出 主账号的产品id
        MoProductRelationEntity relationEntity = moProductRelationRepository.findByAppIdAndProductChildrenId(appId, productId);
        if (null != relationEntity) {
            productClient.delete(credentialMaster, relationEntity.getProductMasterId());
        } 
        else {
            throw new Exception("主子帐号的产品无对应关系！");
        }
    }

    @Override
    public void add(String appId, ProductEntity productEntityChildren) throws Exception {
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        CredentialEntity credentialChid = credentialService.getCredentialByAppId(appId);
        /**
         * 主账号插入产品信息
         */
        // 插入新的产品信息    
        Integer productMasterId = null;
        ProductEntity productEntityMaster = new ProductEntity();
        // 产品的供应商信息
        VendorEntity vendorEntity = vendorsService.findVendorByCode(credentialMaster, appId);
        if (null != vendorEntity) {
            LOGGER.error("查询出供应商：code" + vendorEntity.getVendorCode() + ", name=" + vendorEntity.getVendorName());
            productEntityMaster.setCode(productEntityChildren.getCode() + appId); // 为了防止code 重复，把appid加上
            productEntityMaster.setName(productEntityChildren.getName()); // 产品名称
//            productEntityMaster.setBarCode(productEntityChildren.getBarCode()); // 条形码
            // 供应商信息 把子账号作为供应商
            VendorInfoEntity vendorInfoEntity = new VendorInfoEntity();
            vendorInfoEntity.setId(vendorEntity.getId());
            vendorInfoEntity.setCode(vendorEntity.getVendorCode());
            vendorInfoEntity.setName(vendorEntity.getVendorName());
            productEntityMaster.setVendor(vendorInfoEntity);
            
            // 主图片附件
            AttachmentEntity attachmentChildren = productEntityChildren.getMainImage();
            if (null != attachmentChildren) {
                LOGGER.error("查询出主图片：" + attachmentChildren.getFileName());
                AttachmentEntity attachmentDetail = productClient.findAttachmentById(credentialChid, productEntityChildren.getId(), attachmentChildren.getId());
                attachmentDetail.setId(null);
                productEntityMaster.setMainImage(attachmentDetail);
            }
            // 插入新的产品信息    
            productMasterId = productClient.add(credentialMaster, productEntityMaster);
            
            // 产品信息放到redis
            setProductToRedis(productEntityMaster);
            
            /**
             * 插入主子帐号产品关联
             */
            MoProductRelationEntity relationEntity = new MoProductRelationEntity();
            relationEntity.setProductMasterId(String.valueOf(productMasterId));
            relationEntity.setProductChildrenId(productEntityChildren.getId());
            relationEntity.setAppId(appId);
            moProductRelationRepository.save(relationEntity);
            
           /* productEntityMaster.setId(String.valueOf(productMasterId));
            // 该产品下的所有SKU
            List<SKUInfoEntity> listSKU = productEntityChildren.getSkus();
            
            SKUInfoEntity skuInfoEntityMasterNew = null;
            // SKU 全部库存信息
            SKUAllWarehouseInfoEntity skuAllWarehouseInfoEntity = null;
            // SKU 全部库存数量
            BigDecimal inStockTotalChid = null;
            // 库存盘点lines 信息
            List<InventoryCountingLineEntity> lines = new ArrayList<InventoryCountingLineEntity>();
            InventoryCountingLineEntity inventoryCountingLineEntity = null;
            // 库存盘点新增 总数量
            BigDecimal countedTotal = new BigDecimal(0);
            
            for(SKUInfoEntity skuInfoEntity : listSKU) {
                *//**
                 * 新增 SKU
                 *//*
                skuInfoEntityMasterNew = new SKUInfoEntity();
                SKUInfoEntity skuInfoPrice = skusClient.findById(credentialChid, Integer.parseInt(skuInfoEntity.getId()));
                BeanUtils.copyProperties(skuInfoPrice, skuInfoEntityMasterNew);
                skuInfoEntityMasterNew.remove("id");
                skuInfoEntityMasterNew.remove("product");
                skuInfoEntityMasterNew.remove("relatedVariantValues");
                skuInfoEntityMasterNew.setProduct(productEntityMaster);
                Integer skuMatserId= skusClient.add(credentialMaster, skuInfoEntityMasterNew);
                
                // 原商品ID查询 库存
                skuAllWarehouseInfoEntity = skusClient.findSKUALLWarehouseInfosById(credentialChid, Integer.parseInt(skuInfoEntity.getId()));
                if (null != skuAllWarehouseInfoEntity) {
                    *//**
                     * 库存盘点Line组装
                     *//*
                    inventoryCountingLineEntity = new InventoryCountingLineEntity();
                    // 库存盘点SKU
                    skuInfoEntityMasterNew.setId(String.valueOf(skuMatserId));
                    inventoryCountingLineEntity.setSku(skuInfoEntityMasterNew);
                    // 库存总数
                    inStockTotalChid = skuAllWarehouseInfoEntity.getInStockTotal();
                    inventoryCountingLineEntity.setCountedQuantity(inStockTotalChid);
                    lines.add(inventoryCountingLineEntity);
                    // 统计本次库存盘点总数量
                    countedTotal = countedTotal.add(inStockTotalChid);
                }
            }
            
            // 获取仓库信息
            WarehouseInfoEntity warehouseInfoEntity = warehouseService.findByCode(credentialMaster, appId);
            if (null != warehouseInfoEntity && countedTotal.compareTo(BigDecimal.ZERO) > 0) {
                *//**
                 * 库存盘点信息
                 *//*
                InventoryCountingEntity inventoryCountingMaster = new InventoryCountingEntity();
                inventoryCountingMaster.setWarehouse(warehouseInfoEntity);
                inventoryCountingMaster.setCountTime(DateHelper.format(Constants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, new Date()));
                inventoryCountingMaster.setCountedTotal(countedTotal);
                inventoryCountingMaster.setLines(lines);
                inventoryCountingService.add(credentialMaster, inventoryCountingMaster);
            }*/
            
        }
    }

    /**
     * @see com.plocc.moffice.service.ProductService#pager(com.plocc.moffice.entity.CredentialEntity, java.util.Map, org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<ProductEntity> pager(CredentialEntity credential, Map params, Pageable pager) throws Exception {
        return productClient.pager(credential, params, pager);
    }

    @Override
    public List<ProductEntity> findByCategoryId(String categoryId) throws Exception {
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        // redis取值
        List<ProductEntity> listProByCate = getProductByCategroyFromRedis(categoryId);
        listProByCate = productClient.findByCategoryId(credentialMaster, categoryId, null);
        if (null != listProByCate && listProByCate.size() > 0) {
            for (ProductEntity productEntity : listProByCate) {
                productEntity = getProductPriceAndInStockQuantity(credentialMaster, productEntity);
                productEntity.remove("category");
                
            }
            // 放入redis
            setProductByCategroyToRedis(categoryId, listProByCate);
        } else {
            listProByCate = new ArrayList<ProductEntity>();
        }
        
        
/*        if (null == listProByCate) {
 *         if (null != listProByCate && listProByCate.size() > 0) {
            for (ProductEntity productEntity : listProByCate) {
                productEntity = getProductPrice(credentialMaster, productEntity);
            }
            // 放入redis
            setProductByCategroyToRedis(categoryId, listProByCate);
        } else {
            listProByCate = new ArrayList<ProductEntity>();
        }
        }*/
        // 放入redis
        setProductByCategroyToRedis(categoryId, listProByCate);
        return listProByCate;
    }
    
    /**
     * 重新封装sku的库存
     * 
     * @param credentialMaster
     * @param productEntity
     * @return
     * @throws Exception
     */
    private ProductEntity getProductPriceAndInStockQuantity(CredentialEntity credentialMaster, ProductEntity productEntity) throws Exception{
        List<SKUInfoEntity> listSKUNew = new ArrayList<SKUInfoEntity>();
        List<SKUInfoEntity> listSKU = productEntity.getSkus();
        for (SKUInfoEntity skuInfoEntity : listSKU) {
            skuInfoEntity = skusService.findById(credentialMaster, skuInfoEntity.getId());
            skuInfoEntity.remove("product");
            listSKUNew.add(skuInfoEntity);
        }
        productEntity.setSkus(listSKUNew);
        return productEntity;
    }
    
    
    /**
     * 产品信息放入到redis中
     * 
     * @param productEntity
     */
    private void setProductToRedis(ProductEntity productEntity) {
        // 修改产品信息放到redis
        ValueOperations<String, ProductEntity> operations = redisOperations.opsForValue();
        String key = Constants.REDIS_BUSINESS_PRODUCT_ID_MASTER + productEntity.getId();
        operations.set(key, productEntity);
    }
    
    /**
     * 根据产品Id，从 redis中获取产品信息
     * 
     * @param productId
     * @return
     */
    private ProductEntity getProductFromRedis(String productId) {
        ValueOperations<String, ProductEntity> operations = redisOperations.opsForValue();
        String key = Constants.REDIS_BUSINESS_PRODUCT_ID_MASTER + productId;
        ProductEntity productEntity = operations.get(key);
        return productEntity;
    }
    
    /**
     * 产品种类查询的产品信息放到redis中
     * 放一个小时
     * @param productEntity
     */
    private void setProductByCategroyToRedis(String categoryId, List<ProductEntity> listProByCate) {
        ValueOperations<String, List<ProductEntity>> operations = redisOperations.opsForValue();
        String key = Constants.REDIS_BUSINESS_PRODUCT_BY_CATEGROY_MASTER + categoryId;
        operations.set(key, listProByCate, 1L, TimeUnit.HOURS);
    }
    
    /**
     * 从redis中查询按照产品种类分类的产品信息
     * 
     * @param productEntity
     */
    private List<ProductEntity> getProductByCategroyFromRedis(String categoryId) {
        ValueOperations<String, List<ProductEntity>> operations = redisOperations.opsForValue();
        String key = Constants.REDIS_BUSINESS_PRODUCT_BY_CATEGROY_MASTER + categoryId;
        List<ProductEntity> listProByCate = operations.get(key);
        return listProByCate;
    }
    
}
