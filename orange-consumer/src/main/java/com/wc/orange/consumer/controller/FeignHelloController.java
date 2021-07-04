package com.wc.orange.consumer.controller;

import com.wc.orange.consumer.service.OrangeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignHelloController {
    @Autowired
    private OrangeOrderService orangeOrderService;

    @RequestMapping("/feign/call")
    public String call(){
        return orangeOrderService.hello();
    }
}
