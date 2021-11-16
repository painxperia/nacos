package com.zpain.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zpain.service.domain.Result;
import com.zpain.service.pojo.OrderInfo;

/**
 * @author zhangjun
 * @date 2021/11/16  9:54
 */
public interface OrderService {

    /**
     * 插入数据
     *
     * @param orderInfo
     * @return
     */
    Result<Boolean> insertOrder(OrderInfo orderInfo);

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<IPage<OrderInfo>> getOrderList(Integer pageNum, Integer pageSize);

}
