package com.zpain.service.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zpain.service.domain.Result;
import com.zpain.service.mapper.OrderMapper;
import com.zpain.service.pojo.OrderInfo;
import com.zpain.service.service.OrderService;
import com.zpain.service.util.KeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangjun
 * @date 2021/11/16  9:55
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private KeyGenerator keyGenerator;

    @Override
    public Result<Boolean> insertOrder(OrderInfo orderInfo) {
        try {
            String id = keyGenerator.generatorSnowflakeKey();
            orderInfo.setId(Long.valueOf(id));
            int i = orderMapper.insertOrder(orderInfo);
            if (i > 0) {
                return Result.success();
            } else {
                return Result.fail("插入失败");
            }
        } catch (Exception e) {
            log.error("OrderServiceImpl[insertOrder] error:", e);
            return Result.fail("插入失败" + e.getMessage());
        }
    }

    @Override
    public Result<IPage<OrderInfo>> getOrderList(Integer pageNum, Integer pageSize) {
        Page<OrderInfo> page = new Page<OrderInfo>(pageNum, pageSize, true);
        return Result.success(orderMapper.getOrderInfo(page));
    }
}
