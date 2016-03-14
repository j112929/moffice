/**
 * 宝龙电商
 * com.plocc.moffice.support
 * Constants.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.support;

/**
 * Constants
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 14:11
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public class Constants extends com.plocc.framework.support.Constants {
    // SAP API URL
    public static String SAP_CLIENT_API_URL = "https://api.sapanywhere.cn/v1/";
    // AccessToken
    public static String CLIENT_ACCESS_TOKEN = "https://accounts.sapanywhere.cn/sld/oauth2/token?client_id={client_id}&client_secret={client_secret}&grant_type={grant_type}&refresh_token={refresh_token}";


    /*********************** 产品 ********************/
    /** 产品列表 **/
    public static String CLIENT_API_URL_PRODUCTS = SAP_CLIENT_API_URL + "Products?access_token={access_token}&offset={offset}&limit={limit}&orderby={orderby}";
    
    public static String CLIENT_API_URL_PRODUCTS_ALL = SAP_CLIENT_API_URL + "Products?access_token={access_token}&expand=*";
    /** 产品 根据ID删除产品 **/
    public static String CLIENT_API_URL_PRODUCT_ID_DELETE = SAP_CLIENT_API_URL + "Products/{id}?access_token={access_token}&expand=*";
    /** 产品 根据ID查询产品 **/
    public static String CLIENT_API_URL_PRODUCT_ID_GET = SAP_CLIENT_API_URL + "Products/{id}?access_token={access_token}&expand=*";
    /** 产品 根据ID更新产品 **/
    public static String CLIENT_API_URL_PRODUCT_ID_PATCH = SAP_CLIENT_API_URL + "Products/{id}?access_token={access_token}&expand=*";
    /** 查询产品总数量 **/
    public static String CLIENT_API_URL_PRODUCT_COUNT = SAP_CLIENT_API_URL + "Products/count?access_token={access_token}";
    /** 新增产品 **/
    public static String CLIENT_API_URL_PRODUCT_CREATE = SAP_CLIENT_API_URL + "Products?access_token={access_token}&expand=*";
    /** 客户自定义字段 **/
    public static String CLIENT_API_URL_PRODUCT_CUSTOMFIELDSMETA = SAP_CLIENT_API_URL + "Products/CustomFieldsMeta?access_token={access_token}";
    
    
    /** 产品附件列表 **/ 
    public static String CLIENT_API_URL_PRODUCT_ATTACHMENT_SELECT_LIST_GET = SAP_CLIENT_API_URL + "Products/{id}/Attachments?access_token={access_token}";
    /** 产品附件新增 **/
    public static String CLIENT_API_URL_PRODUCT_ATTACHMENT_CREATE_POST = SAP_CLIENT_API_URL + "Products/{id}/Attachments?access_token={access_token}";
    /** 产品附件删除 **/
    public static String CLIENT_API_URL_PRODUCT_ATTACHMENT_DELETE_ID_DELETE = SAP_CLIENT_API_URL + "Products/{id}/Attachments/{attachmentId}?access_token={access_token}";
    /** 产品附件详情 **/
    public static String CLIENT_API_URL_PRODUCT_ATTACHMENT_SELECT_ID_GET = SAP_CLIENT_API_URL + "Products/{id}/Attachments/{attachmentId}?access_token={access_token}";
    
    
    /** 图片附件列表 **/
    public static String CLIENT_API_URL_PRODUCT_IMAGE_SELECT_LIST_GET = SAP_CLIENT_API_URL + "Products/{id}/Images?access_token={access_token}";
    /** 图片附件新增并设置为主图片 **/
    public static String CLIENT_API_URL_PRODUCT_IMAGE_CREATE_POST = SAP_CLIENT_API_URL + "Products/{id}/Images?access_token={access_token}";
    /** 产品附件详情 **/
    public static String CLIENT_API_URL_PRODUCT_IMAGE_SELECT_ID_GET = SAP_CLIENT_API_URL + "Products/{id}/Images/{attachmentId}?access_token={access_token}";
    

    /*********************** 供应商 ********************/
    /** 查询所有供应商、新增供应商 **/
    public static String CLIENT_API_URL_VENDORS = SAP_CLIENT_API_URL + "Vendors?access_token={access_token}&expand=*";
    /** 产品 根据ID查询、删除、更新供应商 **/
    public static String CLIENT_API_URL_VENDOR_ID = SAP_CLIENT_API_URL + "Vendors/{id}?access_token={access_token}";
    /** 供应商客户自定义字段 **/
    public static String CLIENT_API_URL_VENDOR_CUSTOMFIELDS = SAP_CLIENT_API_URL + "Vendors/CustomFieldsMeta?access_token={access_token}";
    
    
    /*********************** 库存 ********************/
    /** 库存列表 **/
    public static String CLIENT_API_URL_INVENTORYCOUNTINGS = SAP_CLIENT_API_URL + "InventoryCountings?access_token={access_token}";
    /** 库存列表 **/
    public static String CLIENT_API_URL_INVENTORYCOUNTINGS_CREATE_POST = SAP_CLIENT_API_URL + "InventoryCountings?access_token={access_token}";
    /** 库存 根据ID查询 **/
    public static String CLIENT_API_URL_INVENTORYCOUNTINGS_ID = SAP_CLIENT_API_URL + "InventoryCountings/{id}?access_token={access_token}&id={id}&expand=*";
    /** 取消库存 **/
    public static String CLIENT_API_URL_INVENTORYCOUNTINGS_CANCEL_POST = SAP_CLIENT_API_URL + "InventoryCountings/{id}/cancel?access_token={access_token}";
    /** 确认库存 **/
    public static String CLIENT_API_URL_INVENTORYCOUNTINGS_CONFIRM_POST = SAP_CLIENT_API_URL + "InventoryCountings/{id}/confirm?access_token={access_token}";
    
    
    /*********************** 标准价格 ********************/
    /** 标准价格列表 **/
    public static String CLIENT_API_URL_STANDARDPRICELIST_GET = SAP_CLIENT_API_URL + "StandardPriceList?access_token={access_token}";
    /** 标准价格列表更新 **/
    public static String CLIENT_API_URL_STANDARDPRICELIST_PATCH = SAP_CLIENT_API_URL + "StandardPriceList?access_token={access_token}";
    /** SKU标准价格 **/
    public static String CLIENT_API_URL_STANDARDPRICELIST_SKUPRICE_ID_GET = SAP_CLIENT_API_URL + "StandardPriceList/SKUPrice/{id}?access_token={access_token}&expand=*";
    /** SKU标准价格修改 **/
    public static String CLIENT_API_URL_INVENTORYCOUNTING_SKUPRICE_ID_PATCH = SAP_CLIENT_API_URL + "StandardPriceList/SKUPrice/{id}?access_token={access_token}";
    
    public static String CLIENT_API_URL_INVENTORYCOUNTING_SKUPRICE_ID_PATCH_EXPAND = SAP_CLIENT_API_URL + "StandardPriceList/SKUPrice/{id}?access_token={access_token}&expand=*";
    
    
    /*********************** 产品类别 ********************/
    /** 查询所有产品类别 **/
    public static String CLIENT_API_URL_CATEGORIES = SAP_CLIENT_API_URL + "Categories?access_token={access_token}&expand=*";
    
    
    /*********************** 仓库信息 ********************/
    /** 仓库列表 **/
    public static String CLIENT_API_URL_WAREHOUSES_GET = SAP_CLIENT_API_URL + "Warehouses?access_token={access_token}";
    /** 仓库详情 **/
    public static String CLIENT_API_URL_WAREHOUSES_ID_GET = SAP_CLIENT_API_URL + "Warehouses/{id}?access_token={access_token}";
    

    /*********************** SKU信息 ********************/
    /** SKU列表 **/
    public static String CLIENT_API_URL_SKUS_ALL_GET = SAP_CLIENT_API_URL + "SKUs?access_token={access_token}&expand=*";
    
    public static String CLIENT_API_URL_SKUS_CREATE_POST = SAP_CLIENT_API_URL + "SKUs?access_token={access_token}&expand=*";
    
    public static String CLIENT_API_URL_SKUS_ID_ALLWAREHOUSEINFOS_GET = SAP_CLIENT_API_URL + "SKUs/{id}/WarehouseInfos?access_token={access_token}";
    
    public static String CLIENT_API_URL_SKUS_ID_GET = SAP_CLIENT_API_URL + "SKUs/{id}?access_token={access_token}&expand=*";
    
    public static String CLIENT_API_URL_SKUS_ID_PATCH = SAP_CLIENT_API_URL + "SKUs/{id}?access_token={access_token}&expand=*";
    
    public static String CLIENT_API_URL_SKUS_ID_DELETE = SAP_CLIENT_API_URL + "SKUs/{id}?access_token={access_token}&expand=*";
    
    public static String CLIENT_API_URL_SKUS_ID_GET_WAREHOUSEINFOS = SAP_CLIENT_API_URL + "SKUs/{id}/WarehouseInfos?access_token={access_token}&expand=*";
    
    public static String CLIENT_API_URL_SKUS_ID_GET_WAREHOUSEINFO = SAP_CLIENT_API_URL + "SKUs/{id}/WarehouseInfos/{warehouseId}?access_token={access_token}&expand=*";
   
    /**************************属性 Variant***********************/
    /** 查询所有的属性类型 **/
    public static String CLIENT_API_URL_VARIANTS_ALL_GET = SAP_CLIENT_API_URL + "Variants?access_token={access_token}&expand=*";
    /** 新建属性类型 **/
    public static String CLIENT_API_URL_VARIANTS_CREATE_POST = SAP_CLIENT_API_URL + "Variants?access_token={access_token}&expand=*";
    /** ID查询属性类型 **/
    public static String CLIENT_API_URL_VARIANTS_ID_GET = SAP_CLIENT_API_URL + "Variants/{id}?access_token={access_token}&expand=*";
    /** ID修改属性类型 **/
    public static String CLIENT_API_URL_VARIANTS_ID_PATCH = SAP_CLIENT_API_URL + "Variants/{id}?access_token={access_token}&expand=*";
    
    
    /**************************属性 VariantValues***********************/
    
    public static String CLIENT_API_URL_VARIANTVALUES_ALL_GET = SAP_CLIENT_API_URL + "VariantValues?access_token={access_token}&expand=*";
    
    public static String CLIENT_API_URL_VARIANTVALUES_CREATE_POST = SAP_CLIENT_API_URL + "VariantValues?access_token={access_token}&expand=*";
    
    public static String CLIENT_API_URL_VARIANTVALUES_ID_GET = SAP_CLIENT_API_URL + "VariantValues/{id}?access_token={access_token}&expand=*";
    
    public static String CLIENT_API_URL_VARIANTVALUES_ID_PATCH = SAP_CLIENT_API_URL + "VariantValues/{id}?access_token={access_token}&expand=*";
    
    
    
    // 订单列表
    public static String CLIENT_API_URL_SALES_ORDERS = SAP_CLIENT_API_URL + "SalesOrders?access_token={access_token}&offset={offset}&limit={limit}&orderby={orderBy}&expand=*";
    //根据客户Id查订单列表
    public static String CLIENT_API_URL_SALES_ORDERS_CUSTOMER = SAP_CLIENT_API_URL + "SalesOrders?access_token={access_token}&offset={offset}&limit={limit}&orderby={orderBy}&expand=*";
    //单个订单的创建
    public static String CLIENT_API_URL_SALES_ORDER_CREATE = SAP_CLIENT_API_URL + "SalesOrders?access_token={access_token}&expand=*";
    // 订单总数
    public static String CLIENT_API_URL_SALES_ORDER_COUNT = SAP_CLIENT_API_URL + "SalesOrders/count?access_token={access_token}";
    // 单个订单、查询、修改
    public static String CLIENT_API_URL_SALES_ORDER = SAP_CLIENT_API_URL + "SalesOrders/{id}?access_token={access_token}&id={id}&expand=*";
    // 单个订单、取消
    public static String CLIENT_API_URL_SALES_ORDER_CANCEL = SAP_CLIENT_API_URL + "SalesOrders/{id}/cancel?access_token={access_token}&id={id}&expand=*";
    //查询、创建用户
    public static String CLIENT_API_URL_CUSTOMER_ORDERS = SAP_CLIENT_API_URL + "Customers?access_token={access_token}&expand=*";
    //单个用户查询、修改
    public static String CLIENT_API_URL_CUSTOMER = SAP_CLIENT_API_URL + "Customers/{id}?access_token={access_token}";
    public static String CLIENT_API_URL_CUSTOMER_FIELDS_META = SAP_CLIENT_API_URL + "Customers/CustomFieldsMeta?access_token={access_token}";

    // 发货列表
    public static String CLIENT_API_URL_SALES_DELIVERIES = SAP_CLIENT_API_URL + "SalesDeliveries?access_token={access_token}&offset={offset}&limit={limit}&expand=*";
    //单个发货的创建
    public static String CLIENT_API_URL_SALES_DELIVERY_CREATE = SAP_CLIENT_API_URL + "SalesDeliveries?access_token={access_token}&expand=*";
    //单个发货的查询，修改
    public static String CLIENT_API_URL_SALES_DELIVERY = SAP_CLIENT_API_URL + "SalesDeliveries/{id}?access_token={access_token}&id={id}&expand=*";
    //单个发货的取消
    public static String CLIENT_API_URL_SALES_DELIVERY_CANCEL = SAP_CLIENT_API_URL + "SalesDeliveries/{id}/cancel?access_token={access_token}&id={id}&expand=*";
    //单个发货的确认
    public static String CLIENT_API_URL_SALES_DELIVERY_CONFIRM = SAP_CLIENT_API_URL + "SalesDeliveries/{id}/confirmShip?access_token={access_token}&id={id}&expand=*";
    //销售渠道列表查询
    public static String CLIENT_API_URL_SALES_CHANNELS = SAP_CLIENT_API_URL + "SalesChannels?access_token={access_token}&offset={offset}&limit={limit}&expand=*";
    //单个销售渠道查询
    public static String CLIENT_API_URL_SALES_CHANNEL_ID = SAP_CLIENT_API_URL + "SalesChannels/{id}?access_token={access_token}&id={id}&expand=*";
    //单个销售渠道查询
    public static String CLIENT_API_URL_SALES_CHANNEL_NAME = SAP_CLIENT_API_URL + "SalesChannels?access_token={access_token}&expand=*";
    
    /**
     * Redis Keys
     */
    // 凭证 key 前缀
    public static final String REDIS_CREDENTIALS_PREFIX_KEY = "credentials:%s";
    // Redis 分布式锁 Key
    public static final String REDIS_LOCK_KEY = "lock:%s";
    //order channel
    public static final String ORDER_CHANNEL = "moffice_order";
    // warehouse 仓库 key
    public static final String MOFFICE_REDIS_WAREHOUSE = "MOFFICE_REDIS_WAREHOUSE_";
    // vendor 供应商key
    public static final String MOFFICE_REDIS_VENDOR = "MOFFICE_REDIS_VENDOR_";
    //
    public static final String BUSINESS_SALES_CHANNEL_CHILD = "BUSINESS_SALES_CHANNEL_CHILD_";
    
    public static final String BUSINESS_SALES_CHANNEL_MASTER = "BUSINESS_SALES_CHANNEL_MASTER_";
    // 主账号的sku信息key
    public static final String REDIS_BUSINESS_SKU_PRICE_MASTER = "REDIS_BUSINESS_SKU_PRICE_MASTER_";
    
    public static final String REDIS_BUSINESS_PRODUCT_ID_MASTER = "REDIS_BUSINESS_PRODUCT_ID_MASTER_";
    /** 商品种类 **/
    public static final String REDIS_BUSINESS_PRODUCT_BY_CATEGROY_MASTER = "REDIS_BUSINESS_PRODUCT_BY_CATEGROY_MASTER_";
    
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * HTTP_RESPONSE_CODE
     */
    public static final String HTTP_RESPONSE_CODE_200 = "200";
    public static final String HTTP_RESPONSE_CODE_201 = "201";
    public static final String HTTP_RESPONSE_CODE_400 = "400";
    public static final String HTTP_RESPONSE_CODE_401 = "401";
    public static final String HTTP_RESPONSE_CODE_403 = "403";
    public static final String HTTP_RESPONSE_CODE_404 = "404";
    public static final String HTTP_RESPONSE_CODE_405 = "405";
    /** API 调用次数过多 **/
    public static final String HTTP_RESPONSE_CODE_429 = "429";
    public static final String HTTP_RESPONSE_CODE_500 = "500";
    public static final String HTTP_RESPONSE_CODE_503 = "503";
    
    /**
     * THREAD_SLEEP
     */
    public static final Long THREAD_SLEEP_3000 = 3000L;

    public static final Integer MAX_RETRY_TIMES = 5;

}
