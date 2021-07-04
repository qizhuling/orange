package com.wc.orange.zuul;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 路由熔断
 */
@Component
public class MyFallbackProvider implements FallbackProvider {
    @Override
    //只针对consumer服务进行熔断。如果需要所有的路由都加熔断功能，需要在getRoute()方法上返回“*”匹配符
    //getBody()方法返回发送熔断时的反馈信息，这里在发送熔断时返回信息“Sorry, the service is unavailabel now.”
    public String getRoute(){
        return "orange-consumer";
    }
    @Override
    public ClientHttpResponse fallbackResponse(String router,Throwable cause){
        System.out.println("route:"+router);
        System.out.println(("exception:"+cause.getMessage()));
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "ok";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("Sorry, the service is unavailabel now".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers=new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
