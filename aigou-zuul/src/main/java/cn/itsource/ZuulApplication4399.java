package cn.itsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient//可以不写
public class ZuulApplication4399{
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication4399.class,args);
    }
}
