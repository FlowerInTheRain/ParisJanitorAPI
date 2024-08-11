package fr.jypast.parisjanitorapi.bootstrap.config.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"fr.jypast.parisjanitorapi.server.entity"})
@EnableJpaRepositories(basePackages = {"fr.jypast.parisjanitorapi.server.repository"})
@ComponentScan(basePackages = {"fr.jypast.parisjanitorapi.server.adapter"})
public class DomainConfiguration {

}
