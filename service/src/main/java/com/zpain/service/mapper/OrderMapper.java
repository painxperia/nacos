package com.zpain.service.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zpain.service.pojo.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangjun
 * @date 2021/11/15  15:57
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderMapper> {

    /**
     * 插入数据
     *
     * @param orderInfo
     * @return
     */
    int insertOrder(OrderInfo orderInfo);

    /**
     * 分页查询
     * @param orderInfoPage
     * @return
     */
    IPage<OrderInfo> getOrderInfo(Page<OrderInfo> orderInfoPage);

}
