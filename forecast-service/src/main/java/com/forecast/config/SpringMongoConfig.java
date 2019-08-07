package com.forecast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class SpringMongoConfig {

    @SuppressWarnings("deprecation")
	public @Bean MongoClient mongoClient() throws Exception {
        return new MongoClient("localhost", 27017);
    }

    public @Bean MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), "metallica");
    }
}