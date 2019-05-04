package com.theia.server.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.theia"})
public class TheiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheiaApplication.class, args);
	}
}
