package com.didan.learn.course.config;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author dannd1
 * @since 5/13/2025
 */
@Slf4j
@Configuration
@ConditionalOnProperty(
    value = "app.mongodb.enabled",
    havingValue = "true"
)
@EnableMongoRepositories(basePackages = {"com.didan.learn.course.repository"})
public class MongoConfig {

  @Value("${app.mongodb.uri:#{null}}")
  private String uriForLog;

  @Primary
  @Bean(name = {"mongoProperties"})
  @ConfigurationProperties(prefix = "app.mongodb")
  public MongoProperties mongoProperties() {
    return new MongoProperties();
  }

  @Primary
  @Bean(name = "mongoClientFactory")
  public MongoClient mongoClient() {
    log.info("Mongo config uri: {}", uriForLog);
    return MongoClients.create(mongoProperties().getUri());
  }

  @Primary
  @Bean(name = "mongoTemplateFactory")
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoClient(), mongoProperties().getDatabase());
  }

  @Bean
  public Gson gson() {
    return new Gson();
  }
}
