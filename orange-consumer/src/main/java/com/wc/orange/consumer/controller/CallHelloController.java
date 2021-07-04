package com.wc.orange.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CallHelloController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @RequestMapping("/call")
    public String call(){
        ServiceInstance serviceInstance=loadBalancerClient.choose("orange-order");
        System.out.println("服务地址："+serviceInstance.getUri());
        System.out.println("服务名称:"+serviceInstance.getServiceId());
        //使用RestTemplate进行远程调用
        String callServiceResult=new RestTemplate().getForObject(serviceInstance.getUri().toString()+"/hello",String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }
}
