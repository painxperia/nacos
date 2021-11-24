package com.zpain.service.util.mapsturct;

import com.zpain.service.excel.OrderExcel;
import com.zpain.service.pojo.OrderInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhangjun
 * @date 2021/11/24  16:53
 */
@Mapper
public interface OrderInfoConverter {

    OrderInfoConverter INSTANCE = Mappers.getMapper(OrderInfoConverter.class);

    OrderExcel toOrderExcel(OrderInfo orderInfo);

    List<OrderExcel> toOrderExcelList(List<OrderInfo> orderInfo);

}
