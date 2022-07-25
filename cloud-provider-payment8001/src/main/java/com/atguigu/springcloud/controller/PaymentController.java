package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("****插入结果："+result);
        if (result > 0) {
            return new CommonResult(200,"插入数据成功,serverPort:  "+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPayment(id);
        log.info("****查询结果："+payment);
        if (payment != null) {
            return new CommonResult(200,"查询数据成功,serverPort:  "+serverPort,payment);
        }else {
            return new CommonResult(444,"查询数据失败",null);
        }
    }

    @GetMapping("/payment/getDiscovery")
    public Object getDiscovery() {
        List<String> services = discoveryClient.getServices();
        for(String str:services) {
            log.info("****element："+str);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance:instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/timeout")
    public String setTimeout() {
//        try {
//            Thread.sleep(3000);
//        }catch (Exception e) {
//
//        }
        return serverPort;
    }
}
