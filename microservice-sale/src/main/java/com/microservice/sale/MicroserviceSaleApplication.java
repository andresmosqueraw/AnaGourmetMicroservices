package com.microservice.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSaleApplication.class, args);
	}

}
