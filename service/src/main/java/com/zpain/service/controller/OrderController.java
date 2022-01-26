package com.zpain.service.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zpain.service.domain.Result;
import com.zpain.service.pojo.OrderInfo;
import com.zpain.service.pojo.UserRole;
import com.zpain.service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zhangjun
 * @date 2021/11/16  9:50
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/insert_order")
    public Result<Boolean> insertOrder(@RequestBody OrderInfo orderInfo) {
        return orderService.insertOrder(orderInfo);
    }

    @GetMapping("/order_list")
    public Result<IPage<OrderInfo>> getOrderList(@RequestParam("pageNum") Integer pageNum
            , @RequestParam("pageSize") Integer pageSize) {
        return orderService.getOrderList(pageNum, pageSize);
    }

    @PostMapping("/export_excel")
    public void exportExcel(HttpServletResponse response, HttpServletRequest request) throws Exception{
        orderService.getAll(response);
    }

    @GetMapping("/delete_order")
    @CacheEvict(value = "orderList",allEntries = true)
    public Result<Boolean> delete(){
        return Result.success(true);
    }

    @GetMapping("/order_list2")
    public Result<List<OrderInfo>> getOrderList() {
        return Result.success(orderService.getOrderList());
    }

    @PostMapping("/user_role")
    public void getUser(@RequestBody UserRole userRole){
        log.info("userRole:{}", JSON.toJSONString(userRole));
    }
}
