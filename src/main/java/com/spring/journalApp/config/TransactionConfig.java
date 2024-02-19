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
                                                                // MongoDBFactory -> helps to connect w/ MongoDB
                                                                // and it creates session with DB

        // return an implementation of PlatformTransactionManager which maintain txn session (@Transactional)
        return  new MongoTransactionManager(mongoDatabaseFactory);
    }
}
