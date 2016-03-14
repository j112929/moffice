package com.plocc.moffice.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plocc.framework.support.DateHelper;
import com.plocc.moffice.client.SKUsClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.CurrencyPriceInfoEntity;
import com.plocc.moffice.entity.InventoryCountingEntity;
import com.plocc.moffice.entity.InventoryCountingLineEntity;
import com.plocc.moffice.entity.MoProductRelationEntity;
import com.plocc.moffice.entity.ProductEntity;
import com.plocc.moffice.entity.SKUAllWarehouseInfoEntity;
import com.plocc.moffice.entity.SKUInfoEntity;
import com.plocc.moffice.entity.SKUStandardPriceInfoEntity;
import com.plocc.moffice.entity.SKUWarehouseInfoEntity;
import com.plocc.moffice.entity.StandardPriceListEntity;
import com.plocc.moffice.entity.VariantValueInfoEntity;
import com.plocc.moffice.entity.WarehouseInfoEntity;
import com.plocc.moffice.repository.MoProductRelationRepository;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.InventoryCountingService;
import com.plocc.moffice.service.ProductService;
import com.plocc.moffice.service.SKUsService;
import com.plocc.moffice.service.StandardPriceListService;
import com.plocc.moffice.service.VariantValueService;
import com.plocc.moffice.service.WarehouseService;
import com.plocc.moffice.support.Constants;

/**
 * Created by yanghuan on 2016/2/29.
 */
@Service
public class SKUsServiceImpl implements SKUsService {
    
    @Autowired
    private SKUsClient skusClient;
    
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private InventoryCountingService inventoryCountingService;
    @Autowired
    private StandardPriceListService standardPriceListService;
    @Autowired
    private VariantValueService variantValueService;
    
    
    @Autowired
    private MoProductRelationRepository moProductRelationRepository;
    
    
    @Override
    public Integer add(String appId, SKUInfoEntity skuInfoEntity) throws Exception {
        Integer skuIdMaster = null;
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        CredentialEntity credentialChid = credentialService.getCredentialByAppId(appId);
        ProductEntity productChid = skuInfoEntity.getProduct();
        /**
         * 新增 SKU
         */
/*        SKUInfoEntity skuInfoEntityMasterNew = new SKUInfoEntity();
        BeanUtils.copyProperties(skuInfoEntity, skuInfoEntityMasterNew);
        skuInfoEntityMasterNew.remove("id");
        skuInfoEntityMasterNew.remove("relatedVariantValues");
        skuInfoEntityMasterNew.remove("product");*/
        
        skuInfoEntity.remove("id");
        skuInfoEntity.remove("relatedVariantValues");
        skuInfoEntity.remove("product");
        
        // SKU所属属性
        List<VariantValueInfoEntity> listVariantValues = skuInfoEntity.getRelatedVariantValues();
        variantValueService.getVariantValueListNew(appId, listVariantValues);
        
        
        // 根据主子帐号的产品对应关系查询出 主账号的产品id
        MoProductRelationEntity relationEntity = moProductRelationRepository.findByAppIdAndProductChildrenId(appId, productChid.getId());
        // 如果没有则是新增
        if (null == relationEntity) {
            return null;
        }
//        ProductEntity productEntityMaster = productService.findById(Integer.parseInt(relationEntity.getProductMasterId()));
        ProductEntity productEntityMaster = new ProductEntity();
//        productEntityMaster.setId(relationEntity.getProductMasterId());
        productEntityMaster.setId("996");
        skuInfoEntity.setProduct(productEntityMaster);
        // 新增SKU
        skuIdMaster = skusClient.add(credentialMaster, skuInfoEntity);
        
        
        
        /*// SKU 全部库存信息
        SKUAllWarehouseInfoEntity skuAllWarehouseInfoEntity = skusClient.findSKUALLWarehouseInfosById(credentialChid, Integer.parseInt(skuInfoEntity.getId()));
        if (null != skuAllWarehouseInfoEntity) {
            *//**
             * 库存盘点Line组装
             *//*
            InventoryCountingLineEntity inventoryCountingLineEntity = new InventoryCountingLineEntity();
            // 库存盘点SKU
            inventoryCountingLineEntity.setSku(skuInfoEntityMasterNew);
            // 库存总数
            BigDecimal inStockTotalChid = skuAllWarehouseInfoEntity.getInStockTotal();
            inventoryCountingLineEntity.setCountedQuantity(inStockTotalChid);
            // 获取仓库信息
            WarehouseInfoEntity warehouseInfoEntity = warehouseService.findByCode(credentialMaster, appId);
            if (null != warehouseInfoEntity && inStockTotalChid.compareTo(BigDecimal.ZERO) > 0) {
                *//**
                 * 库存盘点信息
                 *//*
                InventoryCountingEntity inventoryCountingMaster = new InventoryCountingEntity();
                inventoryCountingMaster.setWarehouse(warehouseInfoEntity);
                inventoryCountingMaster.setCountTime(DateHelper.format(Constants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, new Date()));
                inventoryCountingMaster.setCountedTotal(inStockTotalChid);
                inventoryCountingMaster.setLines(Arrays.asList(inventoryCountingLineEntity));
                inventoryCountingService.add(credentialMaster, inventoryCountingMaster);
            }
        }
        
        StandardPriceListEntity standardPriceListEntity = standardPriceListService.findBySKUId(credentialChid, skuInfoEntity.getId());
        if (null != standardPriceListEntity) {
            standardPriceListService.updateBySKUId(credentialMaster, standardPriceListEntity, String.valueOf(skuIdMaster));
        }*/
        return skuIdMaster;
    }
    
    private List<VariantValueInfoEntity> getNewVariantValueInfoEntity(List<VariantValueInfoEntity> listVariantValue) {
        for (VariantValueInfoEntity variantValueInfoEntity : listVariantValue) {
            variantValueInfoEntity.remove("id");
        }
        return listVariantValue;
    }
    
    @Override
    public void delete(String appId, String skuId) throws Exception {
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        skusClient.delete(credentialMaster, skuId);
    }

    @Override
    public SKUInfoEntity findById(CredentialEntity credential, String skuId) throws Exception {
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        SKUInfoEntity skuInfoEntity = skusClient.findById(credentialMaster, skuId);
        if (null != skuInfoEntity) {
            // SKU全部库存信息
            SKUAllWarehouseInfoEntity skuAllWarehouseInfoEntity = null;
            skuAllWarehouseInfoEntity = skusClient.findSKUALLWarehouseInfosById(credentialMaster, skuId);
            if (null != skuAllWarehouseInfoEntity && null != skuAllWarehouseInfoEntity.getInStockTotal()) {
                skuInfoEntity.setInStockQuantity(skuAllWarehouseInfoEntity.getInStockTotal());
            } 
            else {
                skuInfoEntity.setInStockQuantity(BigDecimal.ZERO);
            }
            
            // SKU价格信息
            StandardPriceListEntity standardPrice = standardPriceListService.findBySKUId(credentialMaster, String.valueOf(skuId));
            List<SKUStandardPriceInfoEntity> listPrice = standardPrice.getSkuPrices();
            if (null != listPrice && listPrice.size() > 0) {
                for(SKUStandardPriceInfoEntity skuStandardPriceInfoEntity : listPrice) {
                    if (skuStandardPriceInfoEntity.getPricingMethod().equalsIgnoreCase("NET_PRICE")) {
                        if (null != skuStandardPriceInfoEntity.getPrice()) {
                            skuInfoEntity.setNetStandardPrice(skuStandardPriceInfoEntity.getPrice());
                        } 
                        else {
                            skuInfoEntity.setNetStandardPrice(BigDecimal.ZERO);
                        }
                    } else if (skuStandardPriceInfoEntity.getPricingMethod().equalsIgnoreCase("GROSS_PRICE")) {
                        if (null != skuStandardPriceInfoEntity.getPrice()) {
                            skuInfoEntity.setGrossStandardPrice(skuStandardPriceInfoEntity.getPrice());
                        } 
                        else {
                            skuInfoEntity.setGrossStandardPrice(BigDecimal.ZERO);
                        }
                    }
                }
            }
        }
        return skuInfoEntity;
    }

    @Override
    public void update(String appId, SKUInfoEntity skuInfoEntity) throws Exception {
        CredentialEntity credentialMaster = credentialService.getMastCredential();
//        skusClient.update(credentialMaster, skuInfoEntity);
    }

    @Override
    public SKUAllWarehouseInfoEntity findSKUALLWarehouseInfosById(CredentialEntity credential, String skuId) throws Exception {
        return skusClient.findSKUALLWarehouseInfosById(credential, skuId);
    }
    
}
