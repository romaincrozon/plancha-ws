package com.plancha.server.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = { "com.plancha" })
@EntityScan(basePackages = {"com.plancha.dto.entity"})  // scan JPA entities
@EnableJpaRepositories(basePackages = {"com.plancha.repositories"})
public class PlanchaApplication { 

	public static void main(String[] args) {
		SpringApplication.run(PlanchaApplication.class, args);
	}
}
