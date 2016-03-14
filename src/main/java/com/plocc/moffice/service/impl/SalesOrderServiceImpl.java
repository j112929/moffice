/**
 * 宝龙电商
 * com.plocc.moffice.service.impl
 * OrderServiceImpl.java
 * <p/>
 * 2016-02-15
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.support.DataHelper;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.framework.support.StringHelper;
import com.plocc.moffice.client.SalesOrderClient;
import com.plocc.moffice.entity.*;
import com.plocc.moffice.repository.MoOrderRelationRepository;
import com.plocc.moffice.repository.MoProductRelationRepository;
import com.plocc.moffice.repository.MofficeRedisRepository;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.ProductService;
import com.plocc.moffice.service.SalesChannelService;
import com.plocc.moffice.service.SalesOrderService;
import com.plocc.moffice.support.ChannelEnum;
import com.plocc.moffice.support.Constants;
import com.plocc.moffice.support.TaskTypeEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * OrderServiceImpl
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 16:14
 * @email zhanggj@powerlong.com
 * @description 订单实现
 */
@Service
public class SalesOrderServiceImpl implements SalesOrderService {
	private Logger LOGGER = LoggerFactory.getLogger(SalesOrderServiceImpl.class);
    @Autowired
    private SalesOrderClient salesOrderClient;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private ProductService productService; 
    @Autowired
    @Qualifier("mofficeStringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MofficeRedisRepository mofficeRedisRepository;
    @Autowired
    private MoOrderRelationRepository moOrderRelationRepository;
    @Autowired
    private MoProductRelationRepository moProductRelationRepository; 
    @Autowired
    private SalesChannelService salesChannelService;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String createParentOrder(CredentialEntity credential,List paramsList) throws Exception{
		List tmpList = (List) paramsList.get(1);
		Map tmpMap = (Map) paramsList.get(0);
//		Map paramsMap = JsonpHelper.toObject(tmpList, Map.class);
		String type = "master";
		SalesOrderEntity salesOrder = commonOrderFieldsSet(credential,tmpMap.get("seat").toString(),type);
		List productLines = commonProductSet(credential,tmpList);
		salesOrder.put("productLines", productLines);
		@SuppressWarnings("unused")
		List returnLines = commonReturnSet(credential,tmpList);
//		salesOrder.put("returnLines", returnLines);
        String orderId = salesOrderClient.createOrder(credential, salesOrder);
        TaskEntity task = new TaskEntity();
        task.setAppId(credential.getAppId());
        List list = new ArrayList();
        list.add(0, tmpList);
        list.add(1, tmpMap.get("seat"));
        list.add(2, orderId);
        task.setResult(list);
        task.setType(TaskTypeEnum.ADD);
        //将主订单id跟order信息传到redis消息通道
        mofficeRedisRepository.createTask(ChannelEnum.ORDER_CHANNEL, task);
        //后期可能会通知客户到几号口来取
        return orderId;
	}
	private List<Map<String, String>> commonReturnSet(CredentialEntity credential, List<?> params) throws Exception{
		List<Map<String, String>> returnLines = new ArrayList<Map<String, String>>();
        Map<String, String> returnMap = new HashMap<String, String>();
        for (int i = 0; i < params.size(); i++) {
	        @SuppressWarnings("unused")
			Map<?, ?> tmpMap = JsonpHelper.toObject(JsonpHelper.toString(params.get(i)), Map.class);
	        	returnMap.put("id","1");
	        	returnMap.put("logisticsStatus", "ORDERED");
	        	returnLines.add(returnMap);
        }
		return returnLines;
	}
	/**
	 * 主订单    解析前台参数，然后根据productId查出商品信息，将数据插入order中
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List commonProductSet(CredentialEntity credential,List params) throws Exception{
		List productLines = new ArrayList();
        Map product = null;
        for (int i = 0; i < params.size(); i++) {
        	product = new HashMap();
        	Map tmpMap = JsonpHelper.toObject(JsonpHelper.toString(params.get(i)), Map.class);
    		product.put("id", tmpMap.get("id"));
    		product.put("quantity", tmpMap.get("quantity"));
    		product.put("calculationBase", "BY_DEFAULT");//单价
    		Map sku = new HashMap();
    		sku.put("id", tmpMap.get("skuId"));
    		product.put("sku", sku);
    		productLines.add(product);
		}
		return productLines;
	}
	/**
	 * Order公共字段传值
	 */
	private SalesOrderEntity commonOrderFieldsSet(CredentialEntity credential,String seatNumber,String type) throws Exception{
		SalesOrderEntity salesOrder = new SalesOrderEntity();
		ChannelInfoEntity channelInfo = new ChannelInfoEntity();
		ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
		String channelId = "";
		if(type.equals("child")){
			String masterAppId = credentialService.getMastCredential().getAppId();
			channelId = operations.get(Constants.BUSINESS_SALES_CHANNEL_CHILD+masterAppId);
			if(null==channelId){
				SalesChannelsEntity salesChannelsEntity =  salesChannelService.selectByAppId(credential, masterAppId);
				//将（appId,channelId）放入redis
				channelId = salesChannelsEntity.getId();
				operations.set(Constants.BUSINESS_SALES_CHANNEL_CHILD+credential.getAppId(),salesChannelsEntity.getId());
			}
		}else if(type.equals("master")){
			channelId = operations.get(Constants.BUSINESS_SALES_CHANNEL_MASTER+"moffice_weixin");
			if(null==channelId){
				SalesChannelsEntity salesChannelsEntity =  salesChannelService.selectByAppId(credential, "moffice_weixin");
				//将（appId,channelId）放入redis
				channelId = salesChannelsEntity.getId();
				operations.set(Constants.BUSINESS_SALES_CHANNEL_MASTER+"moffice_weixin",salesChannelsEntity.getId());
			}
		}
        channelInfo.setId(channelId);//渠道id 应为网店ID
        
        salesOrder.setChannel(channelInfo);
        CustomerInfoEntity customerInfo = new CustomerInfoEntity();
        customerInfo.setId("1");
        /*customerInfo.setName(salesOrderEntity.getCustomerName());
        customerInfo.setCode(salesOrderEntity.getCustomerCode());*/
        salesOrder.setCustomer(customerInfo);
        CurrencyInfoEntity currencyInfo = new CurrencyInfoEntity();
        currencyInfo.setExchangeRate(1);
        currencyInfo.setCode("RMB");
        currencyInfo.setIsoCode("CNY");//人民币
        salesOrder.setCurrencyInfo(currencyInfo);
        salesOrder.setPricingMethod("GROSS_PRICE");
        AddressInfoEntity addressInfo = new AddressInfoEntity();
        addressInfo.setStreet1(seatNumber);
		salesOrder.setShippingAddress(addressInfo);
		return salesOrder;
	}
	/**
	 * 拆单
	 * 根据appId 拆单
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List createChildrenOrder(Map paramsMap) throws Exception{
		List listChildrenId = new ArrayList(); 
		Map productVendorMap = new HashMap();
        List productLines = new ArrayList();
        Map product = null;
        String str = JsonpHelper.toString(paramsMap.get("result"));
        List list = JsonpHelper.toObject(str, new TypeReference<List>() {
		});
        List resultMap = new ArrayList();
        String seatNumber = "";
        String parentOrderId = "";
        if(list.size()>0){
        	resultMap = JsonpHelper.toObject(JsonpHelper.toString(list.get(0)),new TypeReference<List>() {
			});
        	seatNumber = list.get(1).toString();
        	parentOrderId = list.get(2).toString();
        }
        Map tmpMap = null;
        for (int i = 0; i < resultMap.size(); i++) {
	        // 根据appId 拆分订单
        	tmpMap = new HashMap();
        	tmpMap = (HashMap) resultMap.get(i);
				// 获取对应关系
				String tmpStr = tmpMap.get("id").toString();
				MoProductRelationEntity productRelationEntity = null;
				try{
					productRelationEntity = moProductRelationRepository.findByProductMasterId(tmpStr);
					
				}catch(Exception e){
					LOGGER.error("查询关系表数据为空！", e);
					throw e;
				}
				String appId = "";
				String chiProductId = "";
				if(productRelationEntity!=null){
					// 子帐号的appId
					appId = productRelationEntity.getAppId();
					// 子账号订单Id
					chiProductId = productRelationEntity.getProductChildrenId();
				}
	            //将查出来的信息加入productLines
	            //新增的字段写到这里 
	            //第一次查出来对应供应商的value为空
				if(appId!=null && chiProductId!=null){
					product = new HashMap();
		            if (null == productVendorMap.get(appId)) {
			            ((Map<String, Object>) product).put("id", chiProductId);
						(product).put("quantity", tmpMap.get("quantity"));
						(product).put("calculationBase", "BY_DEFAULT");//单价
						Map<String, String> sku = new HashMap<String, String>();
						//需要根据主账号的skuId 查子帐号中的skuId TODO
						sku.put("id", tmpMap.get("skuId").toString());
						(product).put("sku", sku);
						//加入新的数据
						productLines.add(product);
						productVendorMap.put(appId, productLines);
		            }else {
		            	//取出之前放的数据
		            	productLines = (List) productVendorMap.get(appId);
		            	((Map<String, Object>) product).put("id", chiProductId);
						(product).put("quantity", tmpMap.get("quantity"));
						(product).put("calculationBase", "BY_DEFAULT");//单价
						Map<String, String> sku = new HashMap<String, String>();
						//需要根据主账号的skuId 查子帐号中的skuId TODO
						sku.put("id", tmpMap.get("skuId").toString());
						(product).put("sku", sku);
						//加入新的数据
						productLines.add(product);
						productVendorMap.put(appId, productLines);
		            }
				}
        }

		// 根据拆分的 子账户 新增订单
		Iterator it = productVendorMap.keySet().iterator();
		while(it.hasNext()) {
			String appId = (String) it.next();
			CredentialEntity credential = null;
			try {
				credential = credentialService.getCredentialByAppId(appId);
			} catch (Exception e) {
				LOGGER.error("查询子账号信息失败！", e);
				throw e;
			}
			productLines = (List) productVendorMap.get(appId);
			String type = "child";
			SalesOrderEntity salesOrder = commonOrderFieldsSet(credential,seatNumber,type);
			salesOrder.put("productLines", productLines);
//			List returnLines = commonReturnSet(credential,paramsMap.get("result").toString());
//			salesOrder.put("returnLines", returnLines);
			String childOrderId = salesOrderClient.createOrder(credential, salesOrder);
			MoOrderRelationEntity orderRelation = new MoOrderRelationEntity();
			try {
				if(parentOrderId!=null&&!"".equals(childOrderId)&&!"".equals(appId)){
					orderRelation.setParentOrderId(DataHelper.getLong(parentOrderId));
					orderRelation.setChildrenOrderId(DataHelper.getLong(childOrderId));
					orderRelation.setAppId(appId);
					orderRelation = moOrderRelationRepository.save(orderRelation);
				}
			} catch (Exception e) {
				LOGGER.error("新增订单实体关联信息失败！", e);
				throw e;
			}
			
			listChildrenId.add(childOrderId);
		}
		return listChildrenId;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void commonProductSetForChildren(Map productVendorMap,
			List productLines, Map product, Map tmpMap, String appId,
			String chiProductId) {
		((Map<String, Object>) product).put("id", chiProductId);
		(product).put("quantity", tmpMap.get("quantity"));
		(product).put("calculationBase", "BY_DEFAULT");//单价
		Map<String, String> sku = new HashMap<String, String>();
		sku.put("id", tmpMap.get("skuId").toString());
		(product).put("sku", sku);
		//加入新的数据
		productLines.add(product);
		productVendorMap.put(appId, productLines);
	}
	public Page<SalesOrderEntity> pager(CredentialEntity credential,Map<?, ?> params, Pageable pager) throws Exception{
		Page<SalesOrderEntity> page = salesOrderClient.pager(credential, params, pager);
		return page;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void update(Map paramsMap) throws Exception{
		CredentialEntity credential = credentialService.getCredentialByAppId(paramsMap.get("appId").toString());
		//监控给我的参数格式为（子订单id，子订单状态）
		String str = JsonpHelper.toString(paramsMap.get("result"));
		SalesOrderEntity salesOrder = JsonpHelper.toObject(str,SalesOrderEntity.class);
		List<Map> returnLines = (List<Map>) salesOrder.get("returnLines");
		if(null!=returnLines&&returnLines.size()>0) {
			for (@SuppressWarnings("unused") Map obj : returnLines) {
				
//				tmpMap.put("logisticsStatus", obj.get("logisticsStatus"));
//				tmpMap.put("invoiceStatus",	obj.get("invoiceStatus"));
                System.out.println(returnLines);
			}
		}
		if(credential.getMaster()==true){//主订单更新
			MoOrderRelationEntity orderRelation = new MoOrderRelationEntity();
			//查询主订单的appId
			CredentialEntity credentialEntity = credentialService.getMastCredential();
			//再根据主订单id查主订单状态，
			String parentOrderId = paramsMap.get("appId").toString();
			List list = moOrderRelationRepository.findByAppIdAndParentOrderId(credentialEntity.getAppId(), Long.parseLong(parentOrderId));
			int i=0;
			for (Object obj : list) {
				orderRelation = (MoOrderRelationEntity) obj;
				if(orderRelation.getChildrenOrderStatus()!=null){
					i++;
				}
			}	
			String parentOrderStatus = "";
			if(i==list.size()){
				parentOrderStatus = "CLOSED";
			}
			parentOrderStatus = "ALLOCATED";
			try {
				moOrderRelationRepository.setFixedParentOrderStatusFor(parentOrderStatus, DataHelper.getLong(parentOrderId));
			} catch (Exception e) {
				LOGGER.error("修改主订单状态失败！", e);
				throw e;
			}	
			//要给type赋值
		}else if(credential.getMaster()==false){
			//sap传参数给我，然后我解析数据，for循环解析，
				//将子订单状态插入本地库
			try {
				@SuppressWarnings("unused")
				int rowNumber = moOrderRelationRepository.setFixedChildrenOrderStatusFor("CLOSED", DataHelper.getLong(salesOrder.getId()));
			} catch (Exception e) {
				LOGGER.error("修改子订单状态失败！", e);
				throw e;
			}
			//根据子订单id去查主订单id,
			try{
			    int parentOrderId = moOrderRelationRepository.findByAppIdAndChildrenOrderId(paramsMap.get("appId").toString(),DataHelper.getLong(salesOrder.getId()));
			}catch(Exception e){
				LOGGER.error("查询主订单Id失败！", e);
				throw e;
			}
					
			TaskEntity task = new TaskEntity();
	        task.setAppId(paramsMap.get("appId").toString());
	        task.setResult(paramsMap);
	        task.setType(TaskTypeEnum.MODIFY);
	        //将主订单id跟order信息传到redis消息通道
	        mofficeRedisRepository.createTask(ChannelEnum.ORDER_CHANNEL, task);
		}
	}
	public void sleepThread(ResponseEntity<?> responseEntity){
		if(responseEntity.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void cancel(Map<?, ?> paramsMap) throws Exception{
		CredentialEntity credential = credentialService.getCredentialByAppId(paramsMap.get("appId").toString());
		SalesOrderEntity salesOrder = new SalesOrderEntity();
		Map<Object, Object> params = new HashMap<Object, Object>();
		//TODO
		salesOrderClient.cancelOrder(credential, salesOrder, params);
	}
	
    public SalesOrderEntity selectById(CredentialEntity credential,Long orderId) throws Exception{
		SalesOrderEntity salesOrder = salesOrderClient.findById(credential, orderId);
        return salesOrder;
    }
	@SuppressWarnings("rawtypes")
	public void orderSync(Map paramsMap) throws Exception {
            String type = (String) paramsMap.get("type");
            if (StringHelper.equalsIgnoreCase("add", type)) {
                createChildrenOrder(paramsMap);
            }
            else if (StringHelper.equalsIgnoreCase("modify", type)) {
            	update(paramsMap);
            }
            else if (StringHelper.equalsIgnoreCase("drop", type)) {
                
            }
	}
	@Override
	public Page<SalesOrderEntity> findByCustomerId(CredentialEntity credential,
			Map<?, ?> params, Pageable pager) throws Exception {
		Page<SalesOrderEntity> page = salesOrderClient.findByCustomerId(credential, params, pager);
		return page;
	}


}
