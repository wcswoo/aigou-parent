package cn.itsource.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//注冊中心
@EnableEurekaServer
public class EurekaApplication1000 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication1000.class,args);
    }
}
