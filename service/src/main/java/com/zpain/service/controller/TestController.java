//package com.zpain.service.controller;
//
//import com.zpain.config.util.Constant;
//import com.zpain.service.gateway.OrderService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author zhangjun
// * @date 2021/7/12  15:54
// */
//@RestController
//@RefreshScope
//@Slf4j
//public class TestController {
//
//    @Value("${name:zpain2021}")
//    private String name;
//
//    @Autowired
//    private OrderService orderService;
//
//
//    @GetMapping("/test")
//    public String a() {
//        log.info("aaaaa:{}",Constant.URL);
//        return Constant.URL;
//    }
//
//    @GetMapping("/getOrder")
//    public String getOrder(){
////        orderService.getOrder("okokok");
//        return null;
//    }
//
////    @Autowired
////    private RestTemplate restTemplate;
////
////    @GetMapping("/getName")
////    public String getName() {
////        log.info("name:{}",name);
////        Unirest.post("a").asString();
////        User user = new User();
////        User user1 = restTemplate.postForObject("1", user, User.class);
////        return name;
////    }
//
//}
