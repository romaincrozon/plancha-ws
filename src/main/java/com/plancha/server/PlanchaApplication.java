package com.plancha.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = { "com.plancha" })
@EntityScan(basePackages = {"com.plancha.dto.entity"})  // scan JPA entities
@EnableJpaRepositories(basePackages = {"com.plancha.repositories"})
public class PlanchaApplication { 

	public static void main(String[] args) {
		SpringApplication.run(PlanchaApplication.class, args);
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
