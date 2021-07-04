package com.wc.orange.consumer.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class OrangeOrderHystrix implements OrangeOrderService {
    @RequestMapping("/hello")
    public String hello(){
        return "sorry,hello service call failed.";
    }
}
