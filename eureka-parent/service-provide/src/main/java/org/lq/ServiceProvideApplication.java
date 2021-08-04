package org.lq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//如果配置文件中配置了Eureka注册中心，默认会开启 @EnableEurekaClient
//@EnableEurekaClient
@SpringBootApplication
public class ServiceProvideApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProvideApplication.class);
    }
}
