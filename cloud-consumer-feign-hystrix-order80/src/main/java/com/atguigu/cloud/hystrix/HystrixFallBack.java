package com.atguigu.cloud.hystrix;

import com.atguigu.cloud.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class HystrixFallBack implements OrderService {

    @Override
    public String payment_OK(Integer id) {
        return "服务器异常，请稍后再试——OK";
    }

    @Override
    public String payment_OUT(Integer id) {
        return "服务器异常，请稍后再试——OUT";
    }
}
