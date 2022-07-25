package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8002 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(PaymentMain8002.class,args);
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for(String name:beanDefinitionNames) {
//            System.out.println(name);
//        }
    }
}
