package com.zpain.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpain.service.mapper.OrderMapper;
import com.zpain.service.pojo.OrderInfo;
import com.zpain.service.service.OrderIService;
import org.springframework.stereotype.Service;

/**
 * @author zhangjun
 * @date 2021/11/24  13:16
 */
@Service
public class OrderIServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderIService {
}
