package com.bootcamp.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.bootcamp.stock")
@EnableFeignClients
public class StockService {
	public static void main(String[] args) {
		SpringApplication.run(StockService.class, args);
	}
}