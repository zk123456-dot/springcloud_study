package com.atguigu.cloud.controller;

import com.atguigu.cloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HystrixOrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String payment_OK(@PathVariable("id") Integer id) {
        return orderService.payment_OK(id);
    }

//    @HystrixCommand(fallbackMethod = "payment_OUT_fallback",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @GetMapping("/consumer/payment/hystrix/out/{id}")
    public String payment_OUT(@PathVariable("id") Integer id) {
        return orderService.payment_OUT(id);
    }

}
