package com.bootcampPragma.Stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bootcampPragma.Stock")
public class EmazonApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmazonApplication.class, args);
	}
}