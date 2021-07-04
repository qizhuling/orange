package com.wc.orange.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrangeOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrangeOrderApplication.class, args);
    }

}
