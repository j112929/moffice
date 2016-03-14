package com.plocc.moffice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.plocc.framework.support.JsonpHelper;
import com.plocc.framework.support.StringHelper;
import com.plocc.moffice.client.SalesDeliveryClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.MoOrderRelationEntity;
import com.plocc.moffice.entity.SalesDeliveryEntity;
import com.plocc.moffice.repository.MoOrderRelationRepository;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.SalesDeliveryService;
import com.plocc.moffice.support.Constants;

/** 
 *
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月19日 下午8:40:09 
 * @email 1129290218@qq.com
 * @description  
 */
@Service
public class SalesDeliveryServiceImpl implements SalesDeliveryService{
	@Autowired
    private SalesDeliveryClient salesDeliveryClient;
	@Autowired
    private CredentialService credentialService;
	@Autowired
    @Qualifier("mofficeStringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;
	@Autowired
    private MoOrderRelationRepository moOrderRelationRepository;

	public List<SalesDeliveryEntity> selectSalesDeliveryList(
			CredentialEntity credential) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createSalesDelivery(CredentialEntity credential,Map<?, ?> tmpMap) {
		// TODO Auto-generated method stub
		
	}

	public SalesDeliveryEntity selectById(CredentialEntity credential, int deliveryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Map<?, ?> paramsMap) {
		CredentialEntity credential = credentialService.getCredentialByAppId(paramsMap.get("appId").toString());
		//监控给我的参数格式为（子订单id，子订单状态）
		SalesDeliveryEntity salesDelivery = (SalesDeliveryEntity) paramsMap.get("result");
		Map<String, String> tmpMap = new HashMap<String, String>();
		tmpMap.put("status", "OPEN");
		/*List<Map> returnLines = (List<Map>) salesOrder.get("returnLines");
		if(null!=returnLines&&returnLines.size()>0) {
			for (Map obj : returnLines) {
				tmpMap.put("logisticsStatus", obj.get("logisticsStatus"));
				tmpMap.put("invoiceStatus",	obj.get("invoiceStatus"));
                System.out.println(returnLines);
			}
		}*/
		if(credential.getMaster()==true){//主订单更新
			MoOrderRelationEntity orderRelation = new MoOrderRelationEntity();
			//查询主订单的appId
			CredentialEntity credentialEntity = credentialService.getMastCredential();
			//再根据主订单id查主订单状态，
			List<?> list = moOrderRelationRepository.findByAppIdAndParentOrderId(credentialEntity.getAppId(), (Long)paramsMap.get("orderId"));
			int i=0;
			for (Object obj : list) {
				orderRelation = (MoOrderRelationEntity) obj;
				if(orderRelation.getChildrenOrderStatus()!=null){
					i++;
				}
			}	
			if(i==list.size()){
				orderRelation.setParentOrderStatus("CLOSED");
			}
			orderRelation.setParentOrderStatus("ALLOCATED");
			moOrderRelationRepository.save(orderRelation);
			//要给type赋值
		}else if(credential.getMaster()==false){
			//sap传参数给我，然后我解析数据，for循环解析，
				//将子订单状态插入本地库
			MoOrderRelationEntity orderRelation = new MoOrderRelationEntity();
			orderRelation.setId(Long.parseLong(salesDelivery.getId()));
			orderRelation.setChildrenOrderStatus(tmpMap.get("status").toString());
			orderRelation = moOrderRelationRepository.save(orderRelation);
			//根据子订单id去查主订单id,
			int parentOrderId = moOrderRelationRepository.findByAppIdAndChildrenOrderId(paramsMap.get("appId").toString(),Long.parseLong(salesDelivery.getId()));
			Map<String, Object> msgMap = new HashMap<String, Object>();
	        msgMap.put("type", "modify");
	        msgMap.put("orderId", parentOrderId);
	        msgMap.put("result", paramsMap);
	        //将主订单id跟order信息传到redis消息通道
	        stringRedisTemplate.convertAndSend(Constants.ORDER_CHANNEL, JsonpHelper.toString(msgMap));
		}
	}

	public void cancel(Map<?, ?> tmpMap) {
		// TODO Auto-generated method stub
		
	}

	public void confirmShip(Map<?, ?> tmpMap) {
		// TODO Auto-generated method stub
		
	}

	public void deliverySync(Map<?, ?> tmpMap) {
		// TODO Auto-generated method stub
		try {
            String type = (String) tmpMap.get("type");
            if (StringHelper.equalsIgnoreCase("add", type)) {
               
            }
            else if (StringHelper.equalsIgnoreCase("modify", type)) {
//            	update(tmpMap);
            }
            else if (StringHelper.equalsIgnoreCase("drop", type)) {
                
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}

}
