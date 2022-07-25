package com.atguigu.springcloud.service;
import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl {

    @Value("${server.port}")
    private String serverPort;

    public String payment_OK(Integer id) {
        return "服务器访问正常，立马返回："+Thread.currentThread().getName()+"\t"+"  "+id+"   欢迎下次再来";
    }

    /**
     * @HystrixCommand  Hystrix用于服务降级的注解  fallbackMethod：触发服务降级后调用的兜底方法
     * @HystrixProperty  服务降级配置属性
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "payment_OUT_fallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String payment_OUT(Integer id) {

        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e) {

        }
        return "服务器延迟响应："+id;
    }

    public String payment_OUT_fallback(Integer id) {
        return "服务器运行繁忙，请稍后再试！！"+id;
    }

    //服务熔断示例
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("**********id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber+"  端口："+serverPort;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id不能为负数，请稍后再试，id："+id+"  端口："+serverPort;
    }
}
