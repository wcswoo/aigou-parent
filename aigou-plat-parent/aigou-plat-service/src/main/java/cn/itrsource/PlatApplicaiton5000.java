package cn.itrsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PlatApplicaiton5000 {
    public static void main(String[] args) {
        SpringApplication.run(PlatApplicaiton5000.class,args);
    }
}
