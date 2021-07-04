package com.wc.orange.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


import java.util.HashMap;
import java.util.Map;

@RestController
public class NotFoundController {
    @RequestMapping(value = "/fallback")
    public Mono<Map<String,String>> fallbackController(){
        Map<String,String> res=new HashMap();
        res.put("code","-100");
        res.put("data","service not available");
        return Mono.just(res);
    }
}
