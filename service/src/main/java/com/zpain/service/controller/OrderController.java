package com.zpain.service.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zpain.service.domain.Result;
import com.zpain.service.pojo.OrderInfo;
import com.zpain.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangjun
 * @date 2021/11/16  9:50
 */
@RestController
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
}
