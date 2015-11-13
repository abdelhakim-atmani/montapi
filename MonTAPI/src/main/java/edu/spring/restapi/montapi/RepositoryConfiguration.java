package edu.spring.restapi.montapi;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"edu.spring.restapi.montapi.mapping"})
@EnableJpaRepositories(basePackages = {"edu.spring.restapi.montapi.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
