package com.plocc.moffice.service.handle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.plocc.moffice.service.SalesOrderService;
import com.plocc.moffice.service.TaskConsumerHandle;
import com.plocc.moffice.support.TaskTypeEnum;

/** 
 * TaskOrderAddConsumerHandleImpl
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年3月1日 下午8:55:20 
 * @email 1129290218@qq.com
 * @description  拆单
 */
public class TaskOrderAddConsumerHandleImpl implements TaskConsumerHandle {
	@Autowired
    private SalesOrderService salesOrderService;
	@Override
	public void handle(String appId, Object result) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramsMap = new ConcurrentHashMap<String, Object>();
        paramsMap.put("appId", appId);
        paramsMap.put("type", TaskTypeEnum.ADD);
        paramsMap.put("result", result);
		salesOrderService.createChildrenOrder(paramsMap);
	}
	
}
