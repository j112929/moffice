/**
 * 宝龙电商
 * PACKAGE_NAME
 * Application.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.plocc.framework.entity.Model;
import com.plocc.framework.support.DateHelper;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.impl.BaseClient;
import com.plocc.moffice.entity.InventoryCountingEntity;
import com.plocc.moffice.entity.InventoryCountingLineEntity;
import com.plocc.moffice.entity.SKUInfoEntity;
import com.plocc.moffice.entity.WarehouseInfoEntity;
import com.plocc.moffice.support.Constants;

/**
 * Application
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 15:01
 * @email zhanggj@powerlong.com
 * @description 产品 Rest Api 测试
 */
public class RestInventoryCountingTest {
    private BaseClient template = new BaseClient();
    private static String accessToken;

    @Before
    public void before() {
        Map params = new HashMap();
        params.put("client_id", "1412040728966662-WCR9hh6JvMZjcG5bUAAe34kaIFuj8FMW");
        params.put("client_secret", "khdg5kd7fhyJRFYs2BQnCO8wBhnh");
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", "998ef47b-d5bc-49ae-911a-a8ae5a782e75");
        // 获取凭证
        Model result = template.getForObject(Constants.CLIENT_ACCESS_TOKEN, Model.class, params);
        accessToken = result.getString("access_token");
    }

    
/*    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void getWarehouses() {
        InventoryCountingEntity inventoryCountingEntity = new InventoryCountingEntity();
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("code", "3487563372286924-o1sXuROWn3uTkPRDSXLI2BlLq6aWJVOq");
        List<Map> results = template.getForObject(Constants.CLIENT_API_URL_WAREHOUSES_GET + "&filter=code eq '{code}'", List.class, params);
        if (null != results && results.size() > 0) {
            inventoryCountingEntity = new InventoryCountingEntity(results.get(0));
        }
        System.out.println(inventoryCountingEntity);
    }
  */  
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void createInventoryCounting() {
        try {
            Map params = new HashMap();
            params.put("access_token", accessToken);
            params.put("code", "3487563372286924-o1sXuROWn3uTkPRDSXLI2BlLq6aWJVOq");
            InventoryCountingEntity inventoryCountingEntity = new InventoryCountingEntity();
            WarehouseInfoEntity warehouseInfoEntity = new WarehouseInfoEntity();
            List<Map> results = template.getForObject(Constants.CLIENT_API_URL_WAREHOUSES_GET + "&filter=code eq '{code}'", List.class, params);
            if (null != results && results.size() > 0) {
                warehouseInfoEntity = new WarehouseInfoEntity(results.get(0));
            }
            WarehouseInfoEntity warehouseInfoEntityNew = new WarehouseInfoEntity();
            warehouseInfoEntityNew.setId(warehouseInfoEntity.getId());
            warehouseInfoEntityNew.setCode(warehouseInfoEntity.getCode());
            warehouseInfoEntityNew.setName(warehouseInfoEntity.getName());
            // 仓库信息
            inventoryCountingEntity.setWarehouse(warehouseInfoEntityNew);
            inventoryCountingEntity.setCountTime(DateHelper.format("yyyy-MM-dd HH:mm:ss", new Date()));
            
            
            InventoryCountingLineEntity inventoryCountingLine = new InventoryCountingLineEntity();
            // sku
            SKUInfoEntity sku = new SKUInfoEntity();
            sku.setId("987");
            inventoryCountingLine.setSku(sku);
            // countedQuantity
            inventoryCountingLine.setCountedQuantity(new BigDecimal(16));
            
            List list = new ArrayList();
            list.add(inventoryCountingLine);
            // lines
            inventoryCountingEntity.put("lines", Arrays.asList(inventoryCountingLine));
            
            System.out.println(JsonpHelper.toString(inventoryCountingEntity));
            
//            Integer integerId = template.postForObject(Constants.CLIENT_API_URL_INVENTORYCOUNTINGS_CREATE_POST, JsonpHelper.toString(inventoryCountingEntity), Integer.class, params);
            ResponseEntity<Integer> integerId = template.postForEntity(Constants.CLIENT_API_URL_INVENTORYCOUNTINGS_CREATE_POST, inventoryCountingEntity, ResponseEntity.class, params);
            System.out.println(integerId);
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }
    }
    
    
/*    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void createInventoryCountingMap() {
        try {
            Map inventoryCountingMap = new HashMap();
            
            WarehouseInfoEntity warehouseInfoEntity = new WarehouseInfoEntity();
            Map params = new HashMap();
            params.put("access_token", accessToken);
            params.put("code", "3487563372286924-o1sXuROWn3uTkPRDSXLI2BlLq6aWJVOq");
            List<Map> results = template.getForObject(Constants.CLIENT_API_URL_WAREHOUSES_GET + "&filter=code eq '{code}'", List.class, params);
            if (null != results && results.size() > 0) {
                warehouseInfoEntity = new WarehouseInfoEntity(results.get(0));
            }
            // 仓库信息
            Map warehouseMap = new HashMap();
            warehouseMap.put("id", 14);
//            warehouseMap.put("id", warehouseInfoEntity.getId());
//            warehouseMap.put("code", warehouseInfoEntity.getCode());
//            warehouseMap.put("name", warehouseInfoEntity.getName());
            inventoryCountingMap.put("warehouse", warehouseMap);
            // countTime
            inventoryCountingMap.put("countTime", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            // lines
            Map skuMap = new HashMap();
            skuMap.put("id", 987);
            Map linesMap = new HashMap();
            linesMap.put("sku", skuMap);
            linesMap.put("countedQuantity", 16);
            
            inventoryCountingMap.put("lines", linesMap);

            
            System.out.println(JsonpHelper.toString(inventoryCountingMap));
            
            Map params2 = new HashMap();
            params2.put("access_token", accessToken);
            Integer integerId = template.postForObject(Constants.CLIENT_API_URL_INVENTORYCOUNTINGS_CREATE_POST, JsonpHelper.toString(inventoryCountingMap), Integer.class, params2);
            System.out.println(integerId);
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }
    }*/
    
    
/*    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void getInventoryCountingAll() {
        try {
            Map params = new HashMap();
            params.put("access_token", accessToken);
            List<Map> results = template.getForObject(Constants.CLIENT_API_URL_INVENTORYCOUNTINGS + "&expand=*", List.class, params);
            System.out.println(results);
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }
    }
*/
}
