package com.zpain.service.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangjun
 * @date 2021/10/19  15:43
 */
@FeignClient("order")
public interface OrderService {

//    @GetMapping("/order")
//    public String getOrder(@RequestParam("name") String name);
}
