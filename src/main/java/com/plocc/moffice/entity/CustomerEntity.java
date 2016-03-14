package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

import java.util.Map;

public class CustomerEntity extends Pojo {
    private Object customFields;
    private String customerCode;
    private String customerType;
    private String customerName;
    private String lastName;
    private String firstName;
    private String displayName;
    private String membershipNo;
    private String vatRegistrationNumber;
    private String title;
    private String position;
    private int mainContactId;
    private String stage;
    private String mobile;
    private String phone;
    private String fax;
    private String email;
    private String webSite;
    //状态3种:ACTIVE,INACTIVE,DUPLICATED
    private String status;
    private String remarks;
    private int creditLimit;
    private LanguageInfoEntity language;
    private String gender;
    private String dateOfBirth;
    //4种状态:SUBSCRIBED,UNSUBSCRIBED,CLEANED,UNKNOWN
    private String marketingStatus;
    //2种状态:LIABLE,EXEMPT
    private String taxType;
    private String creationTime;
    private String updateTime;

    public CustomerEntity() {
    }

    public CustomerEntity(Map entity) {
        super(entity);
    }

    private CustomerPriceListLineEntity[] customerPriceListLines;

    public Object getCustomFields() {
        return getString("customFields");
    }

    public void setCustomFields(Object customFields) {
        set("customFields", customFields);
    }

    public String getCustomerCode() {
        return getString("customerCode");
    }

    public void setCustomerCode(String customerCode) {
        set("customerCode", customerCode);
    }

    public String getCustomerType() {
        return getString("customerType");
    }

    public void setCustomerType(String customerType) {
        set("customerType", customerType);
    }

    public String getCustomerName() {
        return getString("customerName");
    }

    public void setCustomerName(String customerName) {
        set("customerName", customerName);
    }

    public String getLastName() {
        return getString("lastName");
    }

    public void setLastName(String lastName) {
        set("lastName", lastName);
    }

    public String getFirstName() {
        return getString("firstName");
    }

    public void setFirstName(String firstName) {
        set("firstName", firstName);
    }

    public String getDisplayName() {
        return getString("displayName");
    }

    public void setDisplayName(String displayName) {
        set("displayName", displayName);
    }

    public String getMembershipNo() {
        return getString("membershipNo");
    }

    public void setMembershipNo(String membershipNo) {
        set("membershipNo", membershipNo);
    }

    public String getVatRegistrationNumber() {
        return getString("vatRegistrationNumber");
    }

    public void setVatRegistrationNumber(String vatRegistrationNumber) {
        set("vatRegistrationNumber", vatRegistrationNumber);
    }

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        set("title", title);
    }

    public String getPosition() {
        return getString("position");
    }

    public void setPosition(String position) {
        set("position", position);
    }

    public int getMainContactId() {
        return getInt("mainContactId");
    }

    public void setMainContactId(int mainContactId) {
        set("mainContactId", mainContactId);
    }

    public String getStage() {
        return getString("stage");
    }

    public void setStage(String stage) {
        set("stage", stage);
    }

    public String getMobile() {
        return getString("mobile");
    }

    public void setMobile(String mobile) {
        set("mobile", mobile);
    }

    public String getPhone() {
        return getString("phone");
    }

    public void setPhone(String phone) {
        set("phone", phone);
    }

    public String getFax() {
        return getString("fax");
    }

    public void setFax(String fax) {
        set("fax", fax);
    }

    public String getEmail() {
        return getString("email");
    }

    public void setEmail(String email) {
        set("email", email);
    }

    public String getWebSite() {
        return getString("webSite");
    }

    public void setWebSite(String webSite) {
        set("webSite", webSite);
    }

    public String getStatus() {
        return getString("status");
    }

    public void setStatus(String status) {
        set("status", status);
    }

    public String getRemarks() {
        return getString("remarks");
    }

    public void setRemarks(String remarks) {
        set("remarks", remarks);
    }

    public int getCreditLimit() {
        return getInt("creditLimit");
    }

    public void setCreditLimit(int creditLimit) {
        set("creditLimit", creditLimit);
    }

    public LanguageInfoEntity getLanguage() {
        return (LanguageInfoEntity) get("language");
    }

    public void setLanguage(LanguageInfoEntity language) {
        set("language", language);
    }

    public String getGender() {
        return getString("gender");
    }

    public void setGender(String gender) {
        set("gender", gender);
    }

    public String getDateOfBirth() {
        return getString("dateOfBirth");
    }

    public void setDateOfBirth(String dateOfBirth) {
        set("dateOfBirth", dateOfBirth);
    }

    public String getMarketingStatus() {
        return getString("marketingStatus");
    }

    public void setMarketingStatus(String marketingStatus) {
        set("marketingStatus", marketingStatus);
    }

    public String getTaxType() {
        return getString("taxType");
    }

    public void setTaxType(String taxType) {
        set("taxType", taxType);
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

    public CustomerPriceListLineEntity[] getCustomerPriceListLines() {
        return (CustomerPriceListLineEntity[]) get("customerPriceListLines");
    }

    public void setCustomerPriceListLines(
            CustomerPriceListLineEntity[] customerPriceListLines) {
        set("customerPriceListLines", customerPriceListLines);
    }

}
