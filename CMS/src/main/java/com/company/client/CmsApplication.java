package com.company.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.company.client")

public class CmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);

	}


}
