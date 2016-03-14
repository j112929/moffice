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
import com.plocc.moffice.entity.VendorEntity;
import com.plocc.moffice.support.Constants;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URISyntaxException;
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
public class RestVendorTest {
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
    
    
    /**
     * 单条商品
     */
    @Test
    public void createVendor() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setVendorCode("qqqqqq333");
        vendorEntity.setVendorName("2222wwww333");
        vendorEntity.setStatus("ACTIVE");
        vendorEntity.setTaxType("LIABLE");
        Map map = new HashMap();
        map.put("ext_default_UDF1", "11111");
        map.put("ext_default_UDF2", "222222");
        vendorEntity.setCustomFields(map);
        ResponseEntity<String> result = template.postForEntity(Constants.CLIENT_API_URL_VENDORS, new HashMap(vendorEntity), String.class, params);
        System.out.println(result.getStatusCode());
    }

    @Test
    public void findVendorByCode() throws Exception {
//        String vendorCode = "3487563372286924-o1sXuROWn3uTkPRDSXLI2BlLq6aWJVOq";
        String vendorCode = "qqqqqq333";
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("vendorCode", vendorCode);
        List<Map> results = template.getForObject(Constants.CLIENT_API_URL_VENDORS + "&filter=vendorCode eq '{vendorCode}'", List.class, params);
        System.out.println("--" + results);
    }
    
    @Test
    public void findVendorById() throws Exception {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", 15);
        VendorEntity results = template.getForObject(Constants.CLIENT_API_URL_VENDOR_ID + "&select=id, customFields", VendorEntity.class, params);
        System.out.println("--" + results);
    }
    
    
    @Test
    public void updateVendorById() throws Exception {
/*        try {
            String vendorCode = "qqqqqq333";
            Map params = new HashMap();
            params.put("access_token", accessToken);
            params.put("id", 15);
            VendorEntity vendorEntity = new VendorEntity();
            vendorEntity.setVendorCode("qqqqqq333");
            vendorEntity.setVendorName("2222wwww3331111");
            vendorEntity.setStatus("ACTIVE");
            vendorEntity.setTaxType("LIABLE");
            Map map = new HashMap();
            map.put("ext_default_UDF1", "11111");
            map.put("ext_default_UDF2", "222222");
            vendorEntity.setCustomFields(map);
            String results = template.patchForObject(Constants.CLIENT_API_URL_VENDOR_ID, new HashMap(vendorEntity), String.class, params);
            System.out.println("--" + results);
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    
    @Test
    public void findVendorByCodeq() throws Exception {
//        String vendorCode = "3487563372286924-o1sXuROWn3uTkPRDSXLI2BlLq6aWJVOq";
        String vendorCode = "qqqqqq333";
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("vendorCode", vendorCode);
        Object results = template.getForObject(Constants.CLIENT_API_URL_VENDOR_CUSTOMFIELDS, String.class, params);
        System.out.println("--" + results);
    }
    
}
