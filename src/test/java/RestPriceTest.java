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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.entity.Model;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.impl.BaseClient;
import com.plocc.moffice.entity.CustomerEntity;
import com.plocc.moffice.entity.ProductEntity;
import com.plocc.moffice.entity.SKUInfoEntity;
import com.plocc.moffice.entity.SKUStandardPriceInfoEntity;
import com.plocc.moffice.entity.StandardPriceListEntity;
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
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RestPriceTest {
    private BaseClient template = new BaseClient();
    private static String accessToken;

    @Before
    public void before() {
        Map params = new HashMap();
        params.put("client_id", "1412040728966662-WCR9hh6JvMZjcG5bUAAe34kaIFuj8FMW");
        params.put("client_secret", "khdg5kd7fhyJRFYs2BQnCO8wBhnh");
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", "998ef47b-d5bc-49ae-911a-a8ae5a782e75");
        
/*        params.put("client_id", "3487563372286924-o1sXuROWn3uTkPRDSXLI2BlLq6aWJVOq");
        params.put("client_secret", "Ycfp0CsO_9Crf3b5xPV0Tnmw6tHi");
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", "6ec50830-1e03-4685-916b-391e5b35ab5b");*/
        // 获取凭证
        Model result = template.getForObject(Constants.CLIENT_ACCESS_TOKEN, Model.class, params);
        accessToken = result.getString("access_token");
    }

    
    @Test
    public void getPrice() {  
        try {
            Map params = new HashMap();
            params.put("access_token", accessToken);
//        params.put("id", "964");
            params.put("id", 1053);
//        params.put("code", "P102");
            Model result = template.getForObject(Constants.CLIENT_API_URL_STANDARDPRICELIST_SKUPRICE_ID_GET, Model.class, params);
            List<SKUStandardPriceInfoEntity> list = JsonpHelper.toObject(JsonpHelper.toString(result.get("skuPrices")), new TypeReference<List<SKUStandardPriceInfoEntity>>() {
            });
            System.out.println("---->" + list);
            
            for(SKUStandardPriceInfoEntity SKUStandardPrice : list) {
                if(SKUStandardPrice.getString("pricingMethod").equals("NET_PRICE")) {
                    System.out.println("NET_PRICE---->" + SKUStandardPrice.get("price"));
                }
            }
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }
    }
    
    
/*    *//**
     * 查询单个订单
     *//*
    @Test
    public void patchSku() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "964");
        SKUInfoEntity skuInfoEntity = new SKUInfoEntity();
        BigDecimal bigDecimal = new BigDecimal(956);
        skuInfoEntity.setGrossPurchasePrice(bigDecimal);
        skuInfoEntity.setNetPurchasePrice(bigDecimal);
        skuInfoEntity.setName("2222222");
        ResponseEntity result = template.patchForEntity("https://api.sapanywhere.cn/v1/SKUs/{id}?access_token={access_token}", skuInfoEntity, String.class, params);
        if (result.getStatusCode() == HttpStatus.OK) {
            System.out.println(result);
        }
    }*/
    
    
/*    
    @Test
    public void updatePrice() {  
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "11");
        StandardPriceListEntity standardPriceListEntity = new StandardPriceListEntity();
        
        List<SKUStandardPriceInfoEntity> skuPrices = new ArrayList<SKUStandardPriceInfoEntity>();
        SKUStandardPriceInfoEntity skuPriceInfoEntity = new SKUStandardPriceInfoEntity();
        skuPriceInfoEntity.setCurrencyCode("RMB");
        skuPriceInfoEntity.setPricingMethod("NET_PRICE");
        skuPriceInfoEntity.setPrice(new BigDecimal(88888));
        skuPrices.add(skuPriceInfoEntity);
        SKUStandardPriceInfoEntity skuPriceInfoEntity2 = new SKUStandardPriceInfoEntity();
        skuPriceInfoEntity2.setCurrencyCode("RMB");
        skuPriceInfoEntity2.setPricingMethod("GROSS_PRICE");
        skuPriceInfoEntity2.setPrice(new BigDecimal(77777));
        skuPrices.add(skuPriceInfoEntity2);
        standardPriceListEntity.setSkuPrices(skuPrices);
        
        ResponseEntity resp = template.patchForEntity(Constants.CLIENT_API_URL_INVENTORYCOUNTING_SKUPRICE_ID_PATCH_EXPAND, new HashMap(standardPriceListEntity), String.class, params);
        System.out.println("NET_PRICE---->" + resp);
    }*/

}
