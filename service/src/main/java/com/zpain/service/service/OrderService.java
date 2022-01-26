package com.zpain.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zpain.service.domain.Result;
import com.zpain.service.pojo.OrderInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<IPage<OrderInfo>> getOrderList(Integer pageNum, Integer pageSize);

    List<OrderInfo> getOrderList();

    /**
     * 获取所有数据
     *
     * @return
     */
    void getAll(HttpServletResponse response) throws IOException;

    /**
     * 查全表
     *
     * @return
     */
    Result<OrderInfo> getAll2();
}
