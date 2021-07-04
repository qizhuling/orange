package com.wc.orange.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.wc.orange"})
public class OrangeAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrangeAdminApplication.class, args);
	}

}

