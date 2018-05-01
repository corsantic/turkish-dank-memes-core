package com.turkishdank.turkishdankmemes.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
@PropertySource(value = "classpath:application.properties")
public class MongoConfiguration extends AbstractMongoConfiguration
{
    @Autowired
    private Environment environment;

    @Override
    protected String getDatabaseName()
    {
        return environment.getRequiredProperty("spring.data.mongodb.database");
    }

    @Override
    public Mongo mongo() {
//        String host = environment.getRequiredProperty("spring.data.mongodb.host");
//        int port = Integer.parseInt(environment.getRequiredProperty("spring.data.mongodb.port"));
        String url = environment.getProperty("spring.data.mongodb.uri");
        MongoClientURI uri = new MongoClientURI(url);
        return new MongoClient(uri);
    }


    @Override
    protected String getMappingBasePackage()
    {
        return "com.turkishdank.turkishdankmemes.entity";
    }

}