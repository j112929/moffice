/**
 * 宝龙电商
 * com.plocc.framework.entity
 * DemoDbConfiguration.java
 * <p/>
 * 2016-01-04
 * 2016宝龙公司-版权所有
 */
package com.plocc.framework.config;

import com.plocc.framework.entity.Pojo;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * DemoDbConfiguration
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 16:19
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Configuration
@EnableJpaRepositories(
        basePackages = {"com.plocc.im.repository","com.plocc.moffice.repository"},
        entityManagerFactoryRef = "mofficeEntityManagerFactory",
        transactionManagerRef = "mofficeTransactionManager"
)
public class DbConfig {
    private final Logger logger = LoggerFactory.getLogger(DbConfig.class);

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.moffice")
    public DataSource mofficeDataSource() {
        logger.debug("Configuration Moffice Data Source！");
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mofficeEntityManagerFactory(EntityManagerFactoryBuilder builder, JpaProperties jpaProperties, @Qualifier("mofficeDataSource") DataSource mofficeDataSource) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.putAll(jpaProperties.getHibernateProperties(mofficeDataSource));
        properties.put("hibernate.ejb.interceptor", hibernateInterceptor());
        return builder.
                dataSource(mofficeDataSource).
                properties(properties).
                packages("com.plocc.im.entity", "com.plocc.moffice.entity").
                persistenceUnit("moffice").
                build();
    }

    @Bean(name = "mofficeTransactionManager")
    @Primary
    public PlatformTransactionManager demoTransactionManager(EntityManagerFactoryBuilder builder, @Qualifier("mofficeEntityManagerFactory") LocalContainerEntityManagerFactoryBean mofficeEntityManagerFactory) {
        return new JpaTransactionManager(mofficeEntityManagerFactory.getObject());
    }

    /**
     * Hibernate 拦截器
     * http://blog.csdn.net/ljhabc1982/article/details/6319251
     *
     * @return
     */
    public EmptyInterceptor hibernateInterceptor() {
        return new EmptyInterceptor() {
            @Override
            public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
                // System.out.println("onSave");
                if (entity instanceof Pojo) {
                    for (int i = 0; i < propertyNames.length; i++) {
                        if ("createDate".equalsIgnoreCase(propertyNames[i]) || "updateDate".equalsIgnoreCase(propertyNames[i])) {
                            state[i] = new Date();
                        }
                    }
                    return true;
                }
                return super.onSave(entity, id, state, propertyNames, types);
            }

            @Override
            public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
                // System.out.println("onFlushDirty");
                if (entity instanceof Pojo) {
                    for (int i = 0; i < propertyNames.length; i++) {
                        if ("updateDate".equalsIgnoreCase(propertyNames[i])) {
                            currentState[i] = new Date();
                        }
                    }
                    return true;
                }
                return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
            }
        };
    }
}
