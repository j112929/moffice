import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plocc.framework.entity.Model;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.impl.BaseClient;
import com.plocc.moffice.entity.BaseDocumentInfo;
import com.plocc.moffice.entity.SalesDeliveryEntity;
import com.plocc.moffice.entity.SalesDeliveryProductLineEntity;
import com.plocc.moffice.service.SalesDeliveryService;
import com.plocc.moffice.support.Constants;

/** 
 *
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月23日 下午5:16:21 
 * @email 1129290218@qq.com
 * @description  
 */
public class RestDeliveryTest {
	private BaseClient template = new BaseClient();
    private static String accessToken;
    @Autowired
    private SalesDeliveryService salesDeliveryService;

    @Before
    public void before() {
        Map params = new HashMap();
//        params.put("client_id", "1412040728966662-WCR9hh6JvMZjcG5bUAAe34kaIFuj8FMW");
//        params.put("client_secret", "khdg5kd7fhyJRFYs2BQnCO8wBhnh");
//        params.put("grant_type", "refresh_token");
//        params.put("refresh_token", "998ef47b-d5bc-49ae-911a-a8ae5a782e75");
        params.put("client_id", "3487563372286924-o1sXuROWn3uTkPRDSXLI2BlLq6aWJVOq");
        params.put("client_secret", "Ycfp0CsO_9Crf3b5xPV0Tnmw6tHi");
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", "6ec50830-1e03-4685-916b-391e5b35ab5b");
        // 获取凭证
        Model result = template.getForObject(Constants.CLIENT_ACCESS_TOKEN, Model.class, params);
        accessToken = result.getString("access_token");
    }
    /**
     * 查询订单列表
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public void getDeliveries() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("offset", 0);
        params.put("limit", 10);
        params.put("orderBy", "creationTime desc");
        /*List result = template.getForObject(Constants.CLIENT_API_URL_SALES_ORDERS, List.class, params);
        List<SalesOrderEntity> orders = JSON.parseArray(JSON.toJSONString(result), SalesOrderEntity.class);*/
        String result = template.getForObject(Constants.CLIENT_API_URL_SALES_DELIVERIES, String.class, params);
        List<Model> deliveries = JsonpHelper.toObject(result, new TypeReference<List<Model>>() {
        });
        System.out.println(deliveries);
    }
    /**
     * 修改发货    
     * 提交什么字段修改什么字段、因为某些字段为只读字段提交上去会 修改失败。所以只提交需要修改的字段
     */
    @Test
    public void update() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "4");
        SalesDeliveryEntity salesDelivery = new SalesDeliveryEntity();
        salesDelivery.setStatus("OPEN");
        ResponseEntity responseEntity = template.patchForEntity(Constants.CLIENT_API_URL_SALES_DELIVERY, salesDelivery, String.class, params);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
        	salesDelivery = template.getForObject(Constants.CLIENT_API_URL_SALES_DELIVERY, SalesDeliveryEntity.class, params);
        	Map mapTmp = new HashMap(salesDelivery);
            System.out.println("修改完成：status->" + mapTmp.get("status"));//修改完之后查询status 不显示
        }
    }
    /**
     * 查询单个发货
     */
    @SuppressWarnings("unchecked")
	@Test
    public void selectById() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "515");
        SalesDeliveryEntity result = template.getForObject(Constants.CLIENT_API_URL_SALES_DELIVERY, SalesDeliveryEntity.class, params);
        System.out.println(result);
    }
    @Test
    public void cancel() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "4");
        SalesDeliveryEntity salesDelivery = new SalesDeliveryEntity();
//        salesDelivery.setStatus("OPEN");
        ResponseEntity result = template.postForEntity(Constants.CLIENT_API_URL_SALES_DELIVERY_CANCEL, salesDelivery, String.class, params);
        System.out.println(result.getBody());
    }
    @Test
    public void confirmShip() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "515");
        SalesDeliveryEntity salesDelivery = new SalesDeliveryEntity();
//        salesDelivery.setStatus("OPEN");
        ResponseEntity result = template.postForEntity(Constants.CLIENT_API_URL_SALES_DELIVERY_CONFIRM, salesDelivery, String.class, params);
        System.out.println(result.getBody());
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void createDelivery() throws IOException{
    	try {
			Map params = new HashMap();
			params.put("access_token", accessToken);
			SalesDeliveryEntity salesDelivery = new SalesDeliveryEntity();
				SalesDeliveryProductLineEntity salesDeliveryProductLineEntity = new SalesDeliveryProductLineEntity();
				salesDeliveryProductLineEntity.setDeliveryQuantity(1);
					BaseDocumentInfo baseDocumentInfo = new BaseDocumentInfo();
					baseDocumentInfo.setBaseId(1);
					baseDocumentInfo.setBaseLineId(1);
				salesDeliveryProductLineEntity.setBaseDocument(baseDocumentInfo);
			List tmpList  = new ArrayList();
			tmpList.add(salesDeliveryProductLineEntity);
//			String str = JsonpHelper.toString(salesDeliveryProductLineEntity);
//			List list = JsonpHelper.toObject(str, new TypeReference<List>() {
//			});
//			Object[] objectArray = readList(str,Object.class);
			salesDelivery.put("lines", tmpList);
//			salesDelivery.setLines(objectArray);
			String deliveryId = template.postForObject(Constants.CLIENT_API_URL_SALES_DELIVERY_CREATE, new HashMap(salesDelivery), String.class, params);
			System.out.println(deliveryId);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
    }
    public Object[] readList(String json, Class clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        JavaType javaType = getCollectionType(Object[].class, clazz);
        return mapper.readValue(json, javaType);
    }

    public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
