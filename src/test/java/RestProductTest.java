/**
 * 宝龙电商
 * PACKAGE_NAME
 * Application.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestClientException;

import com.plocc.framework.entity.Model;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.impl.BaseClient;
import com.plocc.moffice.entity.ProductEntity;
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
public class RestProductTest {
    private BaseClient template = new BaseClient();
    private static String accessToken;

    @Before
    public void before() {
        try {
            Map params = new HashMap();
            params.put("client_id", "1412040728966662-WCR9hh6JvMZjcG5bUAAe34kaIFuj8FMW");
            params.put("client_secret", "khdg5kd7fhyJRFYs2BQnCO8wBhnh");
            params.put("grant_type", "refresh_token");
            params.put("refresh_token", "998ef47b-d5bc-49ae-911a-a8ae5a782e75");
            // 获取凭证
            Model result = template.getForObject(Constants.CLIENT_ACCESS_TOKEN, Model.class, params);
            accessToken = result.getString("access_token");
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    
/*    @Test
    public void getProducts() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("offset", 0);
        params.put("limit", 10);
        params.put("orderby", "creationTime desc");
        String result = template.getForObject(Constants.CLIENT_API_URL_PRODUCTS, String.class, params);
        List<Model> products = JsonpHelper.toObject(result, new TypeReference<List<Model>>() {
        });
        System.out.println(products);
    }
*/
    
    
/*    
    @Test
    public void getProductById() {  
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "1");
        Model result = template.getForObject(Constants.CLIENT_API_URL_PRODUCT_ID, Model.class, params);
        System.out.println(result);
    }
*/    
    
    
    @Test
    public void getProductByCode() {  
        try {
            Map params = new HashMap();
            params.put("access_token", accessToken);
            params.put("code", "testadd008");
            Model result = template.getForObject(Constants.CLIENT_API_URL_PRODUCTS_ALL + "filter=code eq '{code}'", Model.class, params);
            System.out.println(result);
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }
    }


    
    
    /**
     * 修改商品
     * 提交什么字段修改什么字段、因为某些字段为只读字段提交上去会 修改失败。所以只提交需要修改的字段
     *//*
    @Test
    public void getProductUpdate() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "910");
        // 待修改字段
        Model product = new Model();
        
        VendorInfoEntity vendorInfoEntity = new VendorInfoEntity();
        params.put("vendorCode", "3487563372286924-o1sXuROWn3uTkPRDSXLI2BlLq6aWJVOq");
        List<Map> results = template.getForObject(Constants.CLIENT_API_URL_VENDORS + "&filter=vendorCode eq '{vendorCode}'", List.class, params);
        if (null != results && results.size() > 0) {
            vendorInfoEntity = new VendorInfoEntity(results.get(0));
        }
        VendorEntity vendorInfo = new VendorEntity();
        vendorInfo.setId(vendorInfoEntity.getId());
        vendorInfo.setVendorCode(vendorInfoEntity.getCode());
        vendorInfo.setVendorName(vendorInfoEntity.getName());
        product.put("vendor", vendorInfo);
        
        product.put("name", "幻云石:" + new Date().getTime());
        ResponseEntity responseEntity = template.patchForEntity(Constants.CLIENT_API_URL_PRODUCT_ID, product, String.class, params);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            product = template.getForObject(Constants.CLIENT_API_URL_PRODUCT_ID, Model.class, params);
            System.out.println("修改完成：name->" + product.getString("name"));
        }
    }*/

/*    
    @Test
    public void createProduct() {
        try {
            Map params = new HashMap();
            params.put("access_token", accessToken);
            ProductEntity productEntity = new ProductEntity();
            productEntity.setCode("asdfg11");
            productEntity.setName("asdfg");
            Integer integer = template.postForObject(Constants.CLIENT_API_URL_PRODUCT_CREATE, productEntity, Integer.class, params);
            System.out.println("integer---" + integer);
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }
    }
    */
    
/*    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void productUpdateImage() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
//        params.put("id", "910");
        params.put("code", "HA0610");
        // 待修改字段
        Model product = new Model();
        
        ProductEntity productEntity = new ProductEntity();
        List<Map> results = template.getForObject(Constants.CLIENT_API_URL_PRODUCT_CREATE + "&filter=code eq '{code}'", List.class, params);
        if (null != results && results.size() > 0) {
            productEntity = new ProductEntity(results.get(0));
        }
        
        product.put("name", "幻云石:" + new Date().getTime());
        ResponseEntity responseEntity = template.patchForEntity(Constants.CLIENT_API_URL_PRODUCT_ID, product, String.class, params);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            product = template.getForObject(Constants.CLIENT_API_URL_PRODUCT_ID, Model.class, params);
            System.out.println("修改完成：name->" + product.getString("name"));
        }
    }*/

}
