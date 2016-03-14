/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * SalesOrderEntity.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import java.util.List;

import javax.persistence.Entity;

import com.plocc.framework.entity.Pojo;
import com.plocc.framework.support.JsonpHelper;

/**
 * SalesOrderEntity
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 12:44
 * @email zhanggj@powerlong.com
 * @description 订单
 */
public class SalesOrderEntity extends Pojo {
    /*//扩展字段
  	private Object customFields;*/
  	//readonly 文档编号
  	private String docNumber;
  	//readonly 系列
  	private int series;
  	//渠道信息，为了查channel  required unchangeable
  	private ChannelInfoEntity channelInfoEntity;
  	//客户信息，查customer required unchangeable
  	private CustomerInfoEntity customerInfoEntity;
  	//订单时间 "2015-09-22T06:10:00.000Z"
  	private String orderTime;
  	//发货时间 "2015-09-22T06:10:00.000Z"
  	private String deliveryTime;
  	//送货时间
  	private String shippingTime;
  	//readonly 订单状态
  	/*private String status;*/
  	//readonly 支付状态
  	private String paymentStatus;
  	//readonly 发票总额
  	private int invoicedTotal;
  	//readonly 支付总额
  	private int paidTotal;
  	//fulfillmentMethod 执行方法信息
  	private FulfillmentMethodInfoEntity fulfillmentMethodInfoEntity;
  	//paymentType 支付类型信息
  	private PaymentTypeInfoEntity paymentTypeInfoEntity;
  	//extMerchantInfo 外部商业信息
  	private ExternalMerchantInfoEntity externalMerchantInfoEntity;
  	//发货类型名称
  	private String deliveryTypeName;
  	//发货类型id
  	private int deliveryTypeId;
  	//carrier 航空公司信息
  	private CarrierInfoEntity carrierInfoEntity;
  	//campaign readonly 活动信息
  	private CampaignInfoEntity campaignInfoEntity;
  	//unchangeable Possible values:
  	//	SALES_ORDER
  	//	RETURN_ORDER
  	//	EXCHANGE_ORDER 订单类型
  	private String orderType;
  	//promotion readonly 优惠信息
  	private PromotionInfoEntity promotionInfoEntity;
  	//salesEmployee 员工信息
  	private EmployeeInfoEntity employeeInfoEntity;
  	//currency 货币信息
  	private CurrencyInfoEntity currencyInfoEntity;
  	//required	unchangeable 定价方法
  	private String pricingMethod;
  	//是否手动交税
  	private boolean isManualTax;
  	//netTotal,grossTotal,taxAmount,netDiscountSum,grossDiscountSum,shippingAddress,billingAddress
  	//readonly 数量信息
//  	private AmountInfoEntity amountInfoEntity;
  	private int netTotal;
  	private int grossTotal;
  	private int taxAmount;
  	private int netDiscountSum;
  	private int grossDiscountSum;
  	//送货地址
  	private AddressInfoEntity shippingAddress;
  	//发票地址
  	private AddressInfoEntity billingAddress;
  	//折扣
  	private int discount;
  	//处理人备注
  	private String processorRemark;
  	//客户备注
  	private String customerRemark;
  	//毛利率
  	private int grossProfitMargin;
  	//毛利额
  	private int grossProfitAmount;
  	//销售总额
  	private int totalSoldAmount;
  	//产品线
  	private Object[] productLines;
  	//返回线
  	private Object[] returnLines;
  	//送货线
  	private Object[] shippingLines;
  	//详细的税务信息
  	private Object[] detailedTaxInfo;
  	//创建时间"2015-09-22T06:10:00.000Z"
  	private String creationTime;
  	//更新时间"2015-09-22T06:10:00.000Z"
  	private String updateTime;
  	//退货理由
  	private String returnReason;
  	//paymentTerm unchangeable 付款方式的信息
  	private PaymentTermInfoEntity PaymentTermInfoEntity;
  	//原订单id
  	private int originalOrderId;
  	//采购单id
  	private int purchaseOrderId;
  	
	/*public Object getCustomFields() {
		return get("customFields");
	}
	public void setCustomFields(Object customFields) {
		set("customFields", customFields);
	}*/
	public String getDocNumber() {
		return getString("docNumber");
	}
	public void setDocNumber(String docNumber) {
		set("docNumber", docNumber);
	}
	public int getSeries() {
		return getInt("series");
	}
	public void setSeries(int series) {
		set("series", series);
	}
	public ChannelInfoEntity getChannel() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("channel")),ChannelInfoEntity.class);
	}
	public void setChannel(ChannelInfoEntity channelInfoEntity) {
		set("channel", channelInfoEntity);
	}
	public CustomerInfoEntity getCustomer() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("customer")),CustomerInfoEntity.class);
	}
	public void setCustomer(CustomerInfoEntity customerInfoEntity) {
		set("customer", customerInfoEntity);
	}
	public String getOrderTime() {
		return getString("orderTime");
	}
	public void setOrderTime(String orderTime) {
		set("orderTime", orderTime);
	}
	public String getDeliveryTime() {
		return getString("deliveryTime");
	}
	public void setDeliveryTime(String deliveryTime) {
		set("deliveryTime", deliveryTime);
	}
	public String getShippingTime() {
		return getString("shippingTime");
	}
	public void setShippingTime(String shippingTime) {
		set("shippingTime", shippingTime);
	}
	/*public String getStatus() {
		return getString("status");
	}
	public void setStatus(String status) {
		set("status", status);
	}*/
	public String getPaymentStatus() {
		return getString("paymentStatus");
	}
	public void setPaymentStatus(String paymentStatus) {
		set("paymentStatus", paymentStatus);
	}
	public int getInvoicedTotal() {
		return getInt("invoicedTotal");
	}
	public void setInvoicedTotal(int invoicedTotal) {
		set("invoicedTotal", invoicedTotal);
	}
	public int getPaidTotal() {
		return getInt("paidTotal");
	}
	public void setPaidTotal(int paidTotal) {
		set("paidTotal", paidTotal);
	}
	public FulfillmentMethodInfoEntity getFulfillmentMethodInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("fulfillmentMethod")),FulfillmentMethodInfoEntity.class);
	}
	public void setFulfillmentMethodInfo(FulfillmentMethodInfoEntity fulfillmentMethodInfoEntity) {
		set("fulfillmentMethod", fulfillmentMethodInfoEntity);
	}
	public PaymentTypeInfoEntity getPaymentTypeInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("paymentType")),PaymentTypeInfoEntity.class);
	}
	public void setPaymentTypeInfo(PaymentTypeInfoEntity paymentTypeInfoEntity) {
		set("paymentType", paymentTypeInfoEntity);
	}
	public ExternalMerchantInfoEntity getExternalMerchantInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("extMerchantInfo")),ExternalMerchantInfoEntity.class);
	}
	public void setExternalMerchantInfo(ExternalMerchantInfoEntity externalMerchantInfoEntity) {
		set("extMerchantInfo", externalMerchantInfoEntity);
	}
	public String getDeliveryTypeName() {
		return getString("deliveryTypeName");
	}
	public void setDeliveryTypeName(String deliveryTypeName) {
		set("deliveryTypeName", deliveryTypeName);
	}
	public int getDeliveryTypeId() {
		return getInt("deliveryTypeId");
	}
	public void setDeliveryTypeId(int deliveryTypeId) {
		set("deliveryTypeId", deliveryTypeId);
	}
	public CarrierInfoEntity getCarrierInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("carrier")),CarrierInfoEntity.class);
	}
	public void setCarrierInfo(CarrierInfoEntity carrierInfoEntity) {
		set("carrier", carrierInfoEntity);
	}
	public CampaignInfoEntity getCampaignInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("campaign")),CampaignInfoEntity.class);
	}
	public void setCampaignInfo(CampaignInfoEntity campaignInfoEntity) {
		set("campaign", campaignInfoEntity);
	}
	public String getOrderType() {
		return getString("orderType");
	}
	public void setOrderType(String orderType) {
		set("orderType", orderType);
	}
	public PromotionInfoEntity getPromotionInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("promotion")),PromotionInfoEntity.class);
	}
	public void setPromotionInfo(PromotionInfoEntity promotionInfoEntity) {
		set("promotion", promotionInfoEntity);
	}
	public EmployeeInfoEntity getEmployeeInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("salesEmployee")),EmployeeInfoEntity.class);
	}
	public void setEmployeeInfo(EmployeeInfoEntity employeeInfoEntity) {
		set("salesEmployee", employeeInfoEntity);
	}
	public CurrencyInfoEntity getCurrencyInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("currency")),CurrencyInfoEntity.class);
	}
	public void setCurrencyInfo(CurrencyInfoEntity currencyInfoEntity) {
		set("currency", currencyInfoEntity);
	}
	public String getPricingMethod() {
		return getString("pricingMethod");
	}
	public void setPricingMethod(String pricingMethod) {
		set("pricingMethod", pricingMethod);
	}
	public boolean isManualTax() {
		return getBoolean("isManualTax");
	}
	public void setManualTax(boolean isManualTax) {
		set("isManualTax", isManualTax);
	}
	public AmountInfoEntity getAmountInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("amountInfoEntity")),AmountInfoEntity.class);
	}
	public void setAmountInfo(AmountInfoEntity amountInfoEntity) {
		set("amountInfoEntity", amountInfoEntity);
	}
	public int getDiscount() {
		return getInt("discount");
	}
	public void setDiscount(int discount) {
		set("discount", discount);
	}
	public String getProcessorRemark() {
		return getString("processorRemark");
	}
	public void setProcessorRemark(String processorRemark) {
		set("processorRemark", processorRemark);
	}
	public String getCustomerRemark() {
		return getString("customerRemark");
	}
	public void setCustomerRemark(String customerRemark) {
		set("customerRemark", customerRemark);
	}
	public int getGrossProfitMargin() {
		return getInt("grossProfitMargin");
	}
	public void setGrossProfitMargin(int grossProfitMargin) {
		set("grossProfitMargin", grossProfitMargin);
	}
	public int getGrossProfitAmount() {
		return getInt("grossProfitAmount");
	}
	public void setGrossProfitAmount(int grossProfitAmount) {
		set("grossProfitAmount", grossProfitAmount);
	}
	public int getTotalSoldAmount() {
		return getInt("totalSoldAmount");
	}
	public void setTotalSoldAmount(int totalSoldAmount) {
		set("totalSoldAmount", totalSoldAmount);
	}
	public List<ProductEntity> getProductLines() {
		return (List<ProductEntity>) get("productLines");
	}
	public void setProductLines(List<ProductEntity> productLines) {
		set("productLines", productLines);
	}
	public List getReturnLines() {
		return (List) get("returnLines");
	}
	public void setReturnLines(List returnLines) {
		set("returnLines", returnLines);
	}
	public List getShippingLines() {
		return (List) get("shippingLines");
	}
	public void setShippingLines(List shippingLines) {
		set("shippingLines", shippingLines);
	}
	public Object[] getDetailedTaxInfo() {
		return (Object[]) get("detailedTaxInfo");
	}
	public void setDetailedTaxInfo(List detailedTaxInfo) {
		set("detailedTaxInfo", detailedTaxInfo);
	}
	public String getCreationTime() {
		return getString("creationTime");
	}
	public void setCreationTime(String creationTime) {
		set("creationTime", creationTime);
	}
	public String getUpdateTime() {
		return getString("updateTime");
	}
	public void setUpdateTime(String updateTime) {
		set("updateTime", updateTime);
	}
	public String getReturnReason() {
		return getString("returnReason");
	}
	public void setReturnReason(String returnReason) {
		set("returnReason", returnReason);
	}
	public PaymentTermInfoEntity getPaymentTermInfo() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("PaymentTermInfoEntity")),PaymentTermInfoEntity.class);
	}
	public void setPaymentTermInfo(PaymentTermInfoEntity paymentTermInfoEntity) {
		set("paymentTermInfo", paymentTermInfoEntity);
	}
	public int getOriginalOrderId() {
		return getInt("originalOrderId");
	}
	public void setOriginalOrderId(int originalOrderId) {
		set("originalOrderId", originalOrderId);
	}
	public int getPurchaseOrderId() {
		return getInt("purchaseOrderId");
	}
	public void setPurchaseOrderId(int purchaseOrderId) {
		set("purchaseOrderId", purchaseOrderId);
	}
	public int getNetTotal() {
		return getInt("netTotal");
	}
	public void setNetTotal(int netTotal) {
		set("netTotal", netTotal);
	}
	public int getGrossTotal() {
		return getInt("grossTotal");
	}
	public void setGrossTotal(int grossTotal) {
		set("grossTotal", grossTotal);
	}
	public int getTaxAmount() {
		return getInt("taxAmount");
	}
	public void setTaxAmount(int taxAmount) {
		set("taxAmount", taxAmount);
	}
	public int getNetDiscountSum() {
		return getInt("netDiscountSum");
	}
	public void setNetDiscountSum(int netDiscountSum) {
		set("netDiscountSum", netDiscountSum);
	}
	public int getGrossDiscountSum() {
		return getInt("grossDiscountSum");
	}
	public void setGrossDiscountSum(int grossDiscountSum) {
		set("grossDiscountSum", grossDiscountSum);
	}
	public AddressInfoEntity getShippingAddress() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("shippingAddress")),AddressInfoEntity.class);
	}
	public void setShippingAddress(AddressInfoEntity shippingAddress) {
		set("shippingAddress", shippingAddress);
	}
	public AddressInfoEntity getBillingAddress() {
		return JsonpHelper.toObject(JsonpHelper.toString(get("billingAddress")),AddressInfoEntity.class);
	}
	public void setBillingAddress(AddressInfoEntity billingAddress) {
		set("billingAddress", billingAddress);
	}
	
}
