package com.spring.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class TransactionConfig {
    @Bean
    public PlatformTransactionManager createDBTransactionManager(MongoDatabaseFactory mongoDatabaseFactory) {
        return  new MongoTransactionManager(mongoDatabaseFactory);
    }
}
