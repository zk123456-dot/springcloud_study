package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
/**
 * Hystrix服务的图形化监控程序
 */
public class OrderDashBoardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(OrderDashBoardMain9001.class,args);
    }
}
