package com.plocc.moffice.service.handle;

import com.plocc.moffice.service.SalesOrderService;
import com.plocc.moffice.service.TaskConsumerHandle;
import com.plocc.moffice.support.TaskTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TaskOrderModifyConsumerHandleImpl
 * @author jzl
 * @version 1.0.0
 * @time 创建时间：2016年3月1日 下午7:46:30
 * @email 1129290218@qq.com
 * @description 更新订单
 */
public class TaskOrderModifyConsumerHandleImpl implements TaskConsumerHandle {
    @Autowired
    private SalesOrderService salesOrderService;

    public void handle(String appId, Object result) throws Exception {
        // TODO Auto-generated method stub
        Map<String, Object> paramsMap = new ConcurrentHashMap<String, Object>();
        paramsMap.put("appId", appId);
        paramsMap.put("type", TaskTypeEnum.MODIFY);
        paramsMap.put("result", result);
        salesOrderService.update(paramsMap);
    }

}
