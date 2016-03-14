/**
 * 宝龙电商
 * com.plocc.moffice.web
 * SdkController.java
 * <p/>
 * 2016-02-23
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.entity.JsonResult;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.entity.CategoryEntity;
import com.plocc.moffice.entity.CustomerEntity;
import com.plocc.moffice.entity.ProductEntity;
import com.plocc.moffice.entity.SalesOrderEntity;
import com.plocc.moffice.service.CategoryService;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.CustomerService;
import com.plocc.moffice.service.ProductService;
import com.plocc.moffice.service.SalesOrderService;

/**
 * SdkController
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 17:48
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Controller("mofficeSdkController")
public class SdkController {

    private Logger LOGGER = LoggerFactory.getLogger(SdkController.class);
    
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private CustomerService customerService;
    
    
    /**
     * 查询所有产品分类
     * 
     * @return
     */
    @RequestMapping(value = "/categorys")
    @ResponseBody
    public Object getCategoryList() {
        JsonResult result = JsonpHelper.build();
        try {
            List<CategoryEntity> list = categoryService.findAll();
            result.setData(list);
            result.setSuccess(true);
        }
        catch (Exception e) {
            LOGGER.error("getCategoryList 出错！", e);
            result.setData(null);
            result.setSuccess(false);
        }
        return result;
    }
    
    /**
     * 根据产品分类获取产品
     * 
     * @return
     */
    @RequestMapping(value = "/products")
    @ResponseBody
    public Object getProductList(@RequestParam(value = "categoryId", required = true) String categoryId) {
        JsonResult result = JsonpHelper.build();
        List<ProductEntity> list = new ArrayList<ProductEntity>();
        try {
            list = productService.findByCategoryId(categoryId);
            result.setData(list);
            result.setSuccess(true);
        }
        catch (Exception e) {
            LOGGER.error("getProductList 出错！", e);
            result.setData(list);
            result.setSuccess(false);
        }
        return result;
    }
    
    /**
     * 查询商品详情
     * 
     * @return
     */
    @RequestMapping(value = "/details")
    @ResponseBody
    public Object productDetail(@RequestParam(value = "productId", required = true) String productId) {
        JsonResult result = JsonpHelper.build();
        try {
            if (null == productId) {
                return null;
            }
            ProductEntity productEntity= productService.findById(productId);
            result.setData(productEntity);
            result.setSuccess(true);
        }
        catch (Exception e) {
            LOGGER.error("productDetail 出错！", e);
            result.setData(null);
            result.setSuccess(false);
        }
        return result;
    }
	@RequestMapping(value ="/getOrders",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult getOrders(@RequestParam(value = "customerId", required = true) String customerId) {
		JsonResult modelMap = new JsonResult();
		try {
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("customerId", customerId);
			int page=1;
			int size=10;
			PageRequest pageRequest = new PageRequest(page,size);
			Page<SalesOrderEntity> pager = salesOrderService.findByCustomerId(credentialService.getMastCredential(),paramsMap,pageRequest);
			
			modelMap.setData(pager);
			modelMap.setCode(1);
			modelMap.setMessage("查询订单列表完成！");
		} catch (Exception e) {
			LOGGER.error("查询订单列表出错!", e);
			modelMap.setData("");
			modelMap.setCode(0);
			modelMap.setMessage("查询订单列表完成！");
		}
		return modelMap;
	}
	@RequestMapping(value ="/createOrder",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult createOrder(@RequestParam(value = "paramsList", required = true) String paramsList) {
		JsonResult modelMap = new JsonResult();
		try {
			List<?> tmpList = JsonpHelper.toObject(paramsList, new TypeReference<List<?>>() {
			});
			String parentOrderId = salesOrderService.createParentOrder(credentialService.getMastCredential(), tmpList);
			modelMap.setData(parentOrderId);
			modelMap.setCode(1);
			modelMap.setMessage("下单完成！");
		} catch (Exception e) {
			LOGGER.error("下单出错!", e);
			modelMap.setData("");
			modelMap.setCode(0);
			modelMap.setMessage("下单失败！");
		}
		return modelMap;
	}
    @RequestMapping(value = "/customer",method=RequestMethod.GET)
    @ResponseBody
    public Object customer(@RequestParam(value = "customerCode", required = true) String customerCode) {
        try {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setCustomerCode(customerCode);
            customerEntity.setCustomerName(customerCode);
            customerEntity.setCustomerType("INDIVIDUAL_CUSTOMER");
            customerEntity.setStage("CUSTOMER");
            customerEntity.setStatus("ACTIVE");
            customerEntity.setMarketingStatus("SUBSCRIBED");
            customerEntity.set("customFields", new HashMap() {
                {
                    put("ext_default_UDF1", "value");
                    put("ext_default_UDF2", 12);
                }
            });
            
            int customerId = customerService.createCustomer(credentialService.getMastCredential(), customerEntity);
            LOGGER.info(String.valueOf(customerId));
            return "success";
        }
        catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }
    
}

