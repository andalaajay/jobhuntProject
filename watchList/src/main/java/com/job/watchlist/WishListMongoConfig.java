package com.job.watchlist;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class WishListMongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongo.database}")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }
}