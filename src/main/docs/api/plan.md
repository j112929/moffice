#1.商品录入监控(包含商家录入和MOffice录入)
#2.商品下订单支付之后减掉库存数量 ？
#----------------------------------------------------------
#3.商品监控模块监控到下单支付后，通知拆分模块拆分订单，然后分发子订单信息到不同商家（mq）
#4.后台监听子订单状态（从确认订单到完成订单，下单确认-》拆分确认-》正在配送-》完成订单）
#5.前台显示子订单状态
#6.后台监听子订单状态的同时更新状态信息到sap
#7.追踪订单，商家订单合并并展示给商家


#1.SalesChannels  销售渠道
#!2.SalesDelivery  销售发货
#!3.SalesOrder     销售订单
#4.SalesPriceList 销售价格列表
#5.SalesReason		销售原因
#6.SalesReturn    销售退货
#7.SalesSource    销售资源
#8.Invoice			发票
#9.PurchaseReceipts 购物清单

产品 价格 促销
产品类别  



使用以下凭据信息访问SAP Anywhere的开放应用程序接口。
应用标识
1412040728966662-WCR9hh6JvMZjcG5bUAAe34kaIFuj8FMW
应用密码
khdg5kd7fhyJRFYs2BQnCO8wBhnh
刷新令牌
998ef47b-d5bc-49ae-911a-a8ae5a782e75


微风自行车（bike） 
OCC账号：  gtm.bike@anywhere.cn   密码：Initial0 
B2C Eshop: http://weifeng.sapanywhere.cn/ 
B2C  Eshop登录账号：bike_b2c@anywhere.cn    密码：Initial0 

private static final String SEP1 = "#";  
    private static final String SEP2 = "|";  
  
     
    public static String ListToString(List<?> list) {  
        StringBuffer sb = new StringBuffer();  
        if (list != null && list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {  
                if (list.get(i) == null || list.get(i) == "") {  
                    continue;  
                }  
                // 如果值是list类型则调用自己  
                if (list.get(i) instanceof List) {  
                    sb.append(ListToString((List<?>) list.get(i)));  
                    sb.append(SEP1);  
                } else if (list.get(i) instanceof Map) {  
                    sb.append(MapToString((Map<?, ?>) list.get(i)));  
                    sb.append(SEP1);  
                } else {  
                    sb.append(list.get(i));  
                    sb.append(SEP1);  
                }  
            }  
        }  
        return "L" + StringUtils.EncodeBase64(sb.toString());  
    }  
  
     
    private static String EncodeBase64(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	public static String MapToString(Map<?, ?> map) {  
        StringBuffer sb = new StringBuffer();  
        // 遍历map  
        for (Object obj : map.keySet()) {  
            if (obj == null) {  
                continue;  
            }  
            Object key = obj;  
            Object value = map.get(key);  
            if (value instanceof List<?>) {  
                sb.append(key.toString() + SEP1 + ListToString((List<?>) value));  
                sb.append(SEP2);  
            } else if (value instanceof Map<?, ?>) {  
                sb.append(key.toString() + SEP1  
                        + MapToString((Map<?, ?>) value));  
                sb.append(SEP2);  
            } else {  
                sb.append(key.toString() + SEP1 + value.toString());  
                sb.append(SEP2);  
            }  
        }  
        return "M" + StringUtils.EncodeBase64(sb.toString());  
    }  
  private static String DecodeBase64(String listText) {
		// TODO Auto-generated method stub
		return null;
	} 
	public static List<Object> StringToList(String listText) {  
        if (listText == null || listText.equals("")) {  
            return null;  
        }  
        listText = listText.substring(1);  
  
        listText = StringUtils.DecodeBase64(listText);  
  
        List<Object> list = new ArrayList<Object>();  
        String[] text = listText.split(SEP1);  
        for (String str : text) {  
            if (str.charAt(0) == 'M') {  
                Map<?, ?> map = StringToMap(str);  
                list.add(map);  
            } else if (str.charAt(0) == 'L') {  
                List<?> lists = StringToList(str);  
                list.add(lists);  
            } else {  
                list.add(str);  
            }  
        }  
        return list;  
    }
	
