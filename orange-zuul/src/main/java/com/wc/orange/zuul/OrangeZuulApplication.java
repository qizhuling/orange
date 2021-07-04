package com.wc.orange.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableZuulProxy
public class OrangeZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrangeZuulApplication.class, args);
    }

}
