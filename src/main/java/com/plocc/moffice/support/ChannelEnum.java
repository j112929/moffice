package com.plocc.moffice.support;

/**
 * Created by yang on 2016/3/1.
 */
public enum ChannelEnum {

    PRODUCT_CHANNEL("moffice_product", "产品"),
    ORDER_CHANNEL("moffice_order", "订单"),
    SKU_CHANNEL("moffice_sku", "sku");

    private String value;

    private String desc;

    ChannelEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ChannelEnum getEnumByValue(String code) {
        for (ChannelEnum channelEnum : ChannelEnum.values()) {
            if (channelEnum.value.equals(code)) {
                return channelEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
