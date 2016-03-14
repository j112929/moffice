/**
 * 宝龙电商
 * PACKAGE_NAME
 * Application.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */

import com.plocc.framework.entity.Model;
import com.plocc.moffice.client.impl.BaseClient;
import com.plocc.moffice.entity.CustomerEntity;
import com.plocc.moffice.support.Constants;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
 * @description 职责描述
 */
public class RestCustomerTest {
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
     * 单条客户
     */
    @Test
    public void getCustomer() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "56");
        CustomerEntity result = template.getForObject(Constants.CLIENT_API_URL_CUSTOMER, CustomerEntity.class, params);
        System.out.println(result);
    }

    @Test
    public void getCustomFieldsMeta() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        Model result = template.getForObject(Constants.CLIENT_API_URL_CUSTOMER_FIELDS_META, Model.class, params);
        System.out.println(result);
    }

    @Test
    public void getCreateCustomFieldsMeta() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        Map meta = new HashMap();
        ResponseEntity<Model> responseEntity = template.patchForEntity(Constants.CLIENT_API_URL_CUSTOMER_FIELDS_META, new String("asdf"), Model.class, params);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
    }


    @Test
    public void findCustomerByCode() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("customerCode", "1412040728966662-WCR9hh6JvMZjcG5bUAAe34kaIFuj8FMW");
        List<String> results = template.getForObject(Constants.CLIENT_API_URL_CUSTOMER_ORDERS + "&filter=customerCode eq '{customerCode}'", List.class, params);
        System.out.println(results);
    }

    /**
     * 创建客户
     */
    @Test
    public void createCustomer() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerCode("17202");
        customerEntity.setCustomerName("arsenal");
        customerEntity.setCustomerType("INDIVIDUAL_CUSTOMER");
        customerEntity.setStage("SUSPECT");
        customerEntity.setStatus("ACTIVE");
        customerEntity.setMarketingStatus("SUBSCRIBED");
        customerEntity.set("customFields", new HashMap() {
            {
                put("ext_default_UDF1", "value");
                put("ext_default_UDF2", 12);
            }
        });
        try {
            String result = template.postForObject(Constants.CLIENT_API_URL_CUSTOMER_ORDERS, new HashMap(customerEntity), String.class, params);
            System.out.println(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ;
    }

    /**
     * 修改客户
     * 提交什么字段修改什么字段、因为某些字段为只读字段提交上去会 修改失败。所以只提交需要修改的字段
     */
    @Test
    public void getCustomerUpdate() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("id", "52");
        // 待修改字段
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerName("arsenal");
        customerEntity.setCustomerCode("xxxx");
        ResponseEntity responseEntity = template.patchForEntity(Constants.CLIENT_API_URL_CUSTOMER, customerEntity, String.class, params);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            customerEntity = template.getForObject(Constants.CLIENT_API_URL_CUSTOMER, CustomerEntity.class, params);
            System.out.println("修改完成：customerName->" + customerEntity.getCustomerCode());
        }
    }
}
