# moffice
#调sap的接口 实现订单，商品等接口
#框架是spring+ibatis,entity 全部继承pojo,pojo继承Model,
#多种基于json的数据转换方法，包括String转entity,String转List/Map,String跟日期的互转；
#spring scheduler异步监听sap方的数据更新从而形成流式处理；
#将商品信息存储到redis,以减少请求次数过多sap暂停服务；
#将订单/sku更新，商品信息crud的操作通过放入redis消息队列进行处理，后期将改造为kafka进行处理，以减小redis服务器压力；
#DAO层通过spring-jpa实现，只需写接口，不需要写实现，具体使用方式请看spring-jpa官网；
#容器启动使用了spring-boot进行了封装，内嵌了tomcat插件，原先需要maven clean spring-boot:run命令进行启动，现在通过运行startup.java类就可以启动
