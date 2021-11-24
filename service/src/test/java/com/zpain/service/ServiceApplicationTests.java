package com.zpain.service;

import com.alibaba.fastjson.JSON;
import com.zpain.service.controller.User;
import com.zpain.service.domain.Result;
import com.zpain.service.excel.OrderExcel;
import com.zpain.service.mapper.OrderMapper;
import com.zpain.service.pojo.OrderInfo;
import com.zpain.service.service.OrderIService;
import com.zpain.service.service.OrderService;
import com.zpain.service.util.KeyGenerator;
import com.zpain.service.util.mapsturct.OrderInfoConverter;
import kong.unirest.GenericType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SpringBootTest
@Slf4j
class ServiceApplicationTests {
    public static final GenericType<User> user = new GenericType<User>() {
    };


    @Test
    void contextLoads() {
//        SnowflakeShardingKeyGenerator keyGenerator = new SnowflakeShardingKeyGenerator();
//        String s = keyGenerator.generateKey().toString();
//        System.out.println(s);
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            byte[] address = inetAddress.getAddress();
            System.out.println("address:" + address);
            System.out.println(address.length);
            System.out.println(inetAddress.getHostAddress());
        } catch (Exception e) {

        }
    }

    @Autowired
    private KeyGenerator keyGenerator;

    @Test
    public void getId() {
        String stringId = keyGenerator.generatorSnowflakeKey();
        System.out.println(stringId);
    }

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void batchInsert() {
        log.info("start time:{}", LocalDateTime.now());
        List<OrderInfo> list = IntStream.rangeClosed(1, 10000).mapToObj(i -> {
            OrderInfo orderInfo = new OrderInfo();
            Long id = keyGenerator.generatorSnowflakeKeyLong();
            orderInfo.setId(id);
            orderInfo.setOrderId(String.valueOf(i));
            orderInfo.setOrderName(String.format("%s:%s", "test", i));
            orderInfo.setCreateDate(LocalDateTime.now());
            orderInfo.setUpdateDate(LocalDateTime.now());
            return orderInfo;
        }).collect(Collectors.toList());
        orderMapper.insertBatchSomeColumn(list);
        log.info("start end:{}", LocalDateTime.now());
    }


    @Test
    public void get() {
        List<OrderInfo> list = orderMapper.selectList(null);
        log.info("list:{}", list);
    }

    @Autowired
    private OrderIService orderIService;

    @Test
    public void getAll() {
        OrderInfo orderInfo = orderIService.getById(670225350130663424L);
        log.info("orderInfo:{}", orderInfo);
    }

    @Test
    public void insertBatch() {
        log.info("start time:{}", LocalDateTime.now());
        List<OrderInfo> list = IntStream.rangeClosed(1, 10000).mapToObj(i -> {
            OrderInfo orderInfo = new OrderInfo();
            Long id = keyGenerator.generatorSnowflakeKeyLong();
            orderInfo.setId(id);
            orderInfo.setOrderId(String.valueOf(i));
            orderInfo.setOrderName(String.format("%s:%s", "test", i));
            orderInfo.setCreateDate(LocalDateTime.now());
            orderInfo.setUpdateDate(LocalDateTime.now());
            return orderInfo;
        }).collect(Collectors.toList());
        orderIService.saveBatch(list);
        log.info("start end:{}", LocalDateTime.now());
    }

    @Autowired
    private OrderService orderService;

    @Test
    public void getAllInfo() {
//        Result<OrderInfo> all = orderService.getAll();
        Result<OrderInfo> all2 = orderService.getAll2();
    }

    @Test
    public void test() {
        List<OrderInfo> list = IntStream.rangeClosed(1,2).mapToObj( i->{
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderId(String.valueOf(i));
            orderInfo.setCreateDate(LocalDateTime.now());
            return orderInfo;
        } ).collect(Collectors.toList());

        List<OrderExcel> orderExcels = OrderInfoConverter.INSTANCE.toOrderExcelList(list);
        log.info("a:{}", JSON.toJSONString(orderExcels));
    }


}
