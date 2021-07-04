package com.wc.orange.backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.wc.orange"})
@EnableDiscoveryClient
public class OrangeBackupApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrangeBackupApplication.class, args);
	}

}
