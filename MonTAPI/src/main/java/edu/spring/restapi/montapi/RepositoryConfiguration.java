package edu.spring.restapi.montapi;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Repository config class. All configuration needed for the reporisitories. In
 * this file, it is defined the folder to be scanned for the ORM and the
 * repositories.
 * 
 * @author abdelhakim
 *
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "edu.spring.restapi.montapi.mapping" })
@EnableJpaRepositories(basePackages = { "edu.spring.restapi.montapi.repositories" })
@EnableTransactionManagement
public class RepositoryConfiguration {
}
