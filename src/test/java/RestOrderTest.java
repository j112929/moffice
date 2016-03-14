import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.entity.Model;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.impl.BaseClient;
import com.plocc.moffice.entity.ChannelInfoEntity;
import com.plocc.moffice.entity.CurrencyInfoEntity;
import com.plocc.moffice.entity.CustomerEntity;
import com.plocc.moffice.entity.CustomerInfoEntity;
import com.plocc.moffice.entity.ProductEntity;
import com.plocc.moffice.entity.SKUAllWarehouseInfoEntity;
import com.plocc.moffice.entity.SKUWarehouseInfoEntity;
import com.plocc.moffice.entity.SalesOrderEntity;
import com.plocc.moffice.service.SalesOrderService;
import com.plocc.moffice.support.Constants;

/**
 * @author jzl
 * @version 1.0.0
 * @time 创建时间：2016年2月19日 下午5:16:34
 * @email 1129290218@qq.com
 * @description
 */
public class RestOrderTest {
    private BaseClient template = new BaseClient();
    private static String accessToken;
    @Autowired
    private SalesOrderService salesOrderService;
    
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
    public void getOrders() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("offset", 0);
        params.put("limit", 100);
        params.put("orderBy", "creationTime desc");
		params.put("customerId", "1");
        /*List result = template.getForObject(Constants.CLIENT_API_URL_SALES_ORDERS, List.class, params);
        List<SalesOrderEntity> orders = JSON.parseArray(JSON.toJSONString(result), SalesOrderEntity.class);*/
//        String result = template.getForObject(Constants.CLIENT_API_URL_SALES_ORDERS, String.class, params);
        ResponseEntity resp = template.getForEntity(Constants.CLIENT_API_URL_SALES_ORDERS_CUSTOMER+"&filter=customer.id eq {customerId}", String.class, params);
        List<Model> orders = JsonpHelper.toObject(resp.getBody().toString(), new TypeReference<List<Model>>(){});
        System.out.println(orders);
        for (Model m : orders) {
            List<Map> returnList = (List<Map>) m.get("returnLines");
            if(null!=returnList&&returnList.size()>0) {
            	for (Map obj : returnList) {
    				
//    				System.out.println(obj.get("logisticsStatus"));
//    				System.out.println(obj.get("invoiceStatus"));
    			}
            }
           /* List productList = (List)m.get("productLines");
            if(null!=productList&&productList.size()>0) {
                System.out.println(productList);
                System.out.println(m);
            }
            List shippingList = (List)m.get("shippingLines");
            if(null!=shippingList&&shippingList.size()>0) {
                System.out.println(shippingList);
                System.out.println(m);
            }
            List detailedTaxInfo = (List)m.get("detailedTaxInfo");
            if(null!=detailedTaxInfo&&detailedTaxInfo.size()>0) {
                System.out.println(detailedTaxInfo);
                System.out.println(m);
            }*/
        }

    }
    @Test
    public void getOrderList() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("offset", 0);
        params.put("limit", 100);
        params.put("orderBy", "creationTime desc");
        String result = template.getForObject(Constants.CLIENT_API_URL_SALES_ORDERS, String.class, params);
        List<Model> orders = JsonpHelper.toObject(result, new TypeReference<List<Model>>(){});
        for (Model m : orders) {
            List<Map> returnList = (List<Map>) m.get("returnLines");
            if(null!=returnList&&returnList.size()>0) {
            	for (Map obj : returnList) {
    				
    				System.out.println(obj.get("logisticsStatus"));
    				System.out.println(obj.get("invoiceStatus"));
    			}
            }
        }
    }
    /**
     * 创建订单   有问题？？？
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void createOrder() {
    	SalesOrderEntity salesOrder = new SalesOrderEntity();
        ChannelInfoEntity channelInfo = new ChannelInfoEntity();
        channelInfo.setId("1");
        salesOrder.put("channel",channelInfo);
        CustomerInfoEntity customerInfo = new CustomerInfoEntity();
        customerInfo.setId("1");
        customerInfo.setName("jzl");
        customerInfo.setCode("1700");
        salesOrder.put("customer",customerInfo);
        CurrencyInfoEntity currencyInfo = new CurrencyInfoEntity();
        currencyInfo.setExchangeRate(1);
        currencyInfo.setCode("RMB");
        currencyInfo.setIsoCode("CNY");//人民币
        salesOrder.put("currency",currencyInfo);
        //productLines 要给值
        salesOrder.setPricingMethod("NET_PRICE");
        Map uriVariables = new HashMap();
        uriVariables.put("access_token", accessToken);
        Map product = new HashMap();
        product.put("quantity", "12.345");
        product.put("calculationBase", "BY_DEFAULT");
        Map sku = new HashMap();
        sku.put("id", "1");
        product.put("sku", sku);
        List productLines = new ArrayList();
        productLines.add(product);
        salesOrder.put("productLines", productLines);
//        String result = salesOrderService.createParentOrder(salesOrder);
        String result = template.postForObject(Constants.CLIENT_API_URL_SALES_ORDER_CREATE, new HashMap(salesOrder), String.class, uriVariables);
        System.out.println(result);
    }

    /**
     * 查询一条记录出来、去除只读字段在次提交
     */
    @Test
    public void createOrder2() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "52");
        Model result = template.getForObject(Constants.CLIENT_API_URL_SALES_ORDER, Model.class, params);
        System.out.println(result);
        if (true) {
            result.remove("id");
            result.remove("series");
            result.remove("discount");
            result.remove("netDiscountSum");
            result.remove("grossProfitAmount");
            result.remove("paymentStatus");
            result.remove("totalSoldAmount");
            result.remove("creationTime");
            result.remove("paidTotal");
            result.remove("grossDiscountSum");
            result.remove("isManualTax");
            result.remove("invoicedTotal");
            result.remove("invoicedTotal");
            result.remove("status");
            result.remove("grossTotal");
            result.remove("docNumber");
            result.remove("updateTime");
            result.remove("netTotal");
            result.remove("grossProfitMargin");
            result.remove("detailedTaxInfo");
            Map product = new HashMap();
            product.put("quantity", "12.345");
            product.put("calculationBase", "BY_DEFAULT");
            Map sku = new HashMap();
            sku.put("id", "1");
            product.put("sku", sku);
            List productLines = new ArrayList();
            productLines.add(product);
            result.put("productLines", productLines);
            String rs = template.postForObject(Constants.CLIENT_API_URL_SALES_ORDER_CREATE, result, String.class, params);
            System.out.println(rs);
        }
    }

    /**
     * 查询单个订单
     */
    @Test
    public void selectById() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "520");
        
        SalesOrderEntity result = template.getForObject(Constants.CLIENT_API_URL_SALES_ORDER, SalesOrderEntity.class, params);
        System.out.println(result);
        List list = result.getProductLines();
        for (Object obj : list) {
        	Map map =  (Map) obj;
//        	String productId = ((Pojo) map).getId();
        	System.out.println(map.get("id"));//852,853
		}
    }

    /**
     * 修改订单    有问题
     * 提交什么字段修改什么字段、因为某些字段为只读字段提交上去会 修改失败。所以只提交需要修改的字段
     */
    @Test
    public void update() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "435");
        SalesOrderEntity salesOrder = new SalesOrderEntity();
        salesOrder.setDeliveryTypeName("空运");
        ResponseEntity responseEntity = template.patchForEntity(Constants.CLIENT_API_URL_SALES_ORDER, salesOrder, String.class, params);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
        	System.out.println(responseEntity);
        	System.out.println(responseEntity.getBody());
//            salesOrder = template.getForObject(Constants.CLIENT_API_URL_SALES_ORDER, SalesOrderEntity.class, params);
//            System.out.println("修改完成：DeliveryTypeName->" + salesOrder.getDeliveryTypeName());
        }
    }
    @SuppressWarnings("unchecked")
	@Test
    public void cancel() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "12");
        SalesOrderEntity salesOrder = new SalesOrderEntity();
        String r = template.postForObject(Constants.CLIENT_API_URL_SALES_ORDER, salesOrder, String.class, params);
        System.out.println(r);
//        salesOrder.setDeliveryTypeName("空运");
//        ResponseEntity responseEntity = template.postForEntity(Constants.CLIENT_API_URL_SALES_ORDER, salesOrder, String.class, params);
//        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//            salesOrder = template.getForObject(Constants.CLIENT_API_URL_SALES_ORDER, SalesOrderEntity.class, params);
//            System.out.println("修改完成：DeliveryTypeName->" + salesOrder.getDeliveryTypeName());
//        }
    }
    @SuppressWarnings("unchecked")
	@Test
    public void selectSKUByProductId() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id",72);
        ProductEntity pe = new ProductEntity();
        pe.setId("852");
        params.put("product", pe);
        String r = template.getForObject(Constants.CLIENT_API_URL_SKUS_ID_GET_WAREHOUSEINFOS, String.class, params);
        
        SKUAllWarehouseInfoEntity skus = JsonpHelper.toObject(r, SKUAllWarehouseInfoEntity.class);
        System.out.println(skus);
        for (Object obj : (List)skus.getWarehouseInfoList()) {
        	SKUWarehouseInfoEntity sku = JsonpHelper.toObject(JsonpHelper.toString(obj),SKUWarehouseInfoEntity.class);
			System.out.println(sku);
		}
    }
}
