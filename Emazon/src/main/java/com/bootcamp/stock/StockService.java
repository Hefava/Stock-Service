package com.bootcamp.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bootcamp.stock")
public class StockService {
	public static void main(String[] args) {
		SpringApplication.run(StockService.class, args);
	}
}