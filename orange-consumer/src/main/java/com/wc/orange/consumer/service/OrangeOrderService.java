package com.wc.orange.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="orange-order",fallback = OrangeOrderHystrix.class)
public interface OrangeOrderService {
    @RequestMapping("/hello")
    public String hello();
}
