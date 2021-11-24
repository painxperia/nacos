package com.zpain.service.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhangjun
 * @date 2021/11/24  16:08
 */
@Getter
@Setter
@ToString
public class OrderExcel implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelProperty("订单号")
    private String orderId;

    @ExcelProperty("订单名称")
    private String orderName;

    @ExcelProperty("创建时间")
    private LocalDateTime createDate;

}
