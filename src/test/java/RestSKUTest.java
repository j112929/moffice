/**
 * 宝龙电商
 * PACKAGE_NAME
 * Application.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.entity.Model;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.impl.BaseClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.ProductEntity;
import com.plocc.moffice.entity.SKUAllWarehouseInfoEntity;
import com.plocc.moffice.entity.SKUInfoEntity;
import com.plocc.moffice.entity.VariantValueInfoEntity;
import com.plocc.moffice.entity.VendorEntity;
import com.plocc.moffice.support.Constants;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Application
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 15:01
 * @email zhanggj@powerlong.com
 * @description 产品 Rest Api 测试
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RestSKUTest {
    private BaseClient template = new BaseClient();
    private static String accessToken;

    @Before
    public void before() {
        Map params = new HashMap();
/*        params.put("client_id", "1412040728966662-WCR9hh6JvMZjcG5bUAAe34kaIFuj8FMW");
        params.put("client_secret", "khdg5kd7fhyJRFYs2BQnCO8wBhnh");
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", "998ef47b-d5bc-49ae-911a-a8ae5a782e75");*/
        
        params.put("client_id", "3487563372286924-o1sXuROWn3uTkPRDSXLI2BlLq6aWJVOq");
        params.put("client_secret", "Ycfp0CsO_9Crf3b5xPV0Tnmw6tHi");
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", "6ec50830-1e03-4685-916b-391e5b35ab5b");
        
        // 获取凭证
        Model result = template.getForObject(Constants.CLIENT_ACCESS_TOKEN, Model.class, params);
        accessToken = result.getString("access_token");
    }

    
/*    @Test
    public void addSKU() throws Exception {
        try {
            Map params = new HashMap();
            params.put("access_token", accessToken);
            SKUInfoEntity skuInfoEntity = new SKUInfoEntity();
            skuInfoEntity.setCode("testadd0028");
            skuInfoEntity.setName("testadd0028");
            VariantValueInfoEntity variantValueInfoEntity = new VariantValueInfoEntity();
            variantValueInfoEntity.setId("1");
            skuInfoEntity.setRelatedVariantValues(Arrays.asList(variantValueInfoEntity));
            
            ProductEntity product = new ProductEntity();
            product.setId("996");
            skuInfoEntity.setProduct(product);
            
            ResponseEntity<Integer> resp = template.postForEntity(Constants.CLIENT_API_URL_SKUS_CREATE_POST, new HashMap<String, Object>(skuInfoEntity), Integer.class, params);
            System.out.println("--" + resp);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    
/*    @Test
    public void findSKUAllWarehouseById() throws Exception {
        Map params = new HashMap();
        params.put("access_token", accessToken);
//        params.put("id", 987);str
        ResponseEntity<String> resp = template.getForEntity(Constants.CLIENT_API_URL_SKUS_ALL_GET, String.class, params);
        
        List<Map> list = JsonpHelper.toObject(resp.getBody(), new TypeReference<List<Map>>() {
        });
        System.out.println("--" + list);
    }*/
    
    
    @Test
    public void findSKUById() throws Exception {
        try {
            Map params = new HashMap();
            params.put("access_token", accessToken);
//        params.put("id", 987);
//        params.put("id", 21); // 杨欢
//            params.put("id", 964); // 高江
//            params.put("id", 1053); // 测试属性
            params.put("id", 1020); // 测试属性
            
            ResponseEntity<SKUInfoEntity> resp = template.getForEntity(Constants.CLIENT_API_URL_SKUS_ID_GET, SKUInfoEntity.class, params);
            
            SKUInfoEntity SKUInfoEntity = resp.getBody();
            System.out.println("--" + SKUInfoEntity.get("relatedVariantValues"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
/*    @Test
    public void findSKUByCode() throws Exception {
        try {
            Map params = new HashMap();
            params.put("access_token", accessToken);
//        params.put("id", 987);
//        params.put("id", 21); // 杨欢
//            params.put("id", 964); // 高江
            params.put("code", "测试sku-02"); // 测试属性
            
            ResponseEntity<SKUInfoEntity> resp = template.getForEntity(Constants.CLIENT_API_URL_SKUS_ALL_GET + "&filter=code eq '{code}'" , SKUInfoEntity.class, params);
            
            SKUInfoEntity SKUInfoEntity = resp.getBody();
            System.out.println("--" + SKUInfoEntity.get("relatedVariantValues"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
    
    
}
