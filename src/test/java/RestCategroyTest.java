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

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.entity.Model;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.impl.BaseClient;
import com.plocc.moffice.entity.CategoryEntity;
import com.plocc.moffice.support.Constants;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class RestCategroyTest {
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

    
    @Test
    public void getCategroies() {
        Map params = new HashMap();
        params.put("access_token", accessToken);
        String result = template.getForObject(Constants.CLIENT_API_URL_CATEGORIES, String.class, params);
        List<CategoryEntity> products = JsonpHelper.toObject(result, new TypeReference<List<CategoryEntity>>() {
        });
        System.out.println(products);
    }

    
    
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
    
}
