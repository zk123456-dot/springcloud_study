package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
/**
 * 配置gateway自定义的filter过滤器
 */
public class GateWayFilter implements GlobalFilter, Ordered {
    /**
     * 实际的过滤器操作逻辑方法
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*********com in GateWayFilter："+new Date());
        String name = exchange.getRequest().getQueryParams().getFirst("name");
        if (name == null) {
            log.info("***********非法用户**************");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 值过滤器的返回级别
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
