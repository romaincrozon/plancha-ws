package com.theia.server.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.theia.services.FilesWatchService;


@SpringBootApplication(scanBasePackages = { "com.theia" })
public class TheiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheiaApplication.class, args);
		
//		FilesWatchService filesWatchService = new FilesWatchService("C:\\Users\\romai\\Downloads\\Series");
//		filesWatchService.run();
		
	}
}
