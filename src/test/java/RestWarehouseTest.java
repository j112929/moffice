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
import org.springframework.asm.TypeReference;
import org.springframework.web.client.RestClientException;

import com.plocc.framework.entity.Model;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.impl.BaseClient;
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
public class RestWarehouseTest {
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

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void getWarehouses() {
        WarehouseInfoEntity warehouseInfoEntity = new WarehouseInfoEntity();
        Map params = new HashMap();
        params.put("access_token", accessToken);
        params.put("code", "3487563372286924-o1sXuROWn3uTkPRDSXLI2BlLq6aWJVOq");
        List<Map> results = template.getForObject(Constants.CLIENT_API_URL_WAREHOUSES_GET + "&filter=code eq '{code}'", List.class, params);
        if (null != results && results.size() > 0) {
            warehouseInfoEntity = new WarehouseInfoEntity(results.get(0));
        }
        System.out.println(warehouseInfoEntity);
    }

}
