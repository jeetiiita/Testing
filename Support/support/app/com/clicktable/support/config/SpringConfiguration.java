package com.clicktable.support.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.clicktable.util.Constants;
//import org.springframework.context.annotation.Configuration;

/**
 */
@ComponentScan({"com.clicktable.support.controllers",
        "com.clicktable.support.dao.intf", "com.clicktable.support.dao.impl",
        "com.clicktable.support.service.impl",
        "com.clicktable.support.service.intf",
        "com.clicktable.support.validate",
        "com.clicktable.support.factory"
})
@org.springframework.context.annotation.Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class SpringConfiguration {

    /**
     * Method getTransactionManager.
     *
     * @return PlatformTransactionManager
     */
    @Bean(name = Constants.TRANSACTION_MANAGER)
    public PlatformTransactionManager getTransactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    /**
     * Method entityManagerFactory.
     *
     * @return EntityManagerFactory
     */
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("defaultPersistenceUnit");
    }
}
