package com.zpain.service.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zpain.service.domain.Result;
import com.zpain.service.excel.OrderExcel;
import com.zpain.service.mapper.OrderMapper;
import com.zpain.service.pojo.OrderInfo;
import com.zpain.service.service.OrderService;
import com.zpain.service.util.KeyGenerator;
import com.zpain.service.util.mapsturct.OrderInfoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

    @Override
    public void getAll(HttpServletResponse response) throws IOException {
        LocalDateTime now1 = LocalDateTime.now();
        int threadSize = 5;
        int num = 4000;
        //查询数据库表数据量
        Long count = orderMapper.selectCount(null);
        if (count > 0) {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            long batch = (count / num + 1);
            int batchNum = (int) batch;
            ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("zpain-g-pool").build();
            ThreadPoolExecutor executor = new ThreadPoolExecutor(threadSize, threadSize, 60
                    , TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), factory);
            List<Future<List<OrderInfo>>> futureList = new ArrayList<>();
            for (int i = 1; i <= threadSize; i++) {
                int pageNum = i;
                Future<List<OrderInfo>> listFuture = executor.submit(() -> {
                    List<OrderInfo> list = new ArrayList<>();
                    Page<OrderInfo> page = new Page<OrderInfo>(pageNum, num, true);
                    IPage<OrderInfo> orderInfo = orderMapper.getOrderInfo(page);
                    if (orderInfo.getSize() != 0) {
                        list = orderInfo.getRecords();
                    }
                    return list;
                });
                futureList.add(listFuture);
            }
            List<OrderExcel> listExcel = new ArrayList<>();
            for (Future<List<OrderInfo>> f : futureList) {
                try {
                    List<OrderInfo> list = f.get();
                    List<OrderExcel> orderExcels = OrderInfoConverter.INSTANCE.toOrderExcelList(list);
                    listExcel.addAll(orderExcels);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            EasyExcel.write(response.getOutputStream(), OrderExcel.class).sheet("order").doWrite(
                    () -> {
                        return null;
                    }
            );

        }
        LocalDateTime now2 = LocalDateTime.now();
        log.info("time:{}", Duration.between(now1, now2));
    }

    @Override
    public Result<OrderInfo> getAll2() {
        LocalDateTime now1 = LocalDateTime.now();
        List<OrderInfo> list = orderMapper.selectList(null);
        log.info("sum:{}", list.size());
        LocalDateTime now2 = LocalDateTime.now();
        log.info("time:{}", Duration.between(now1, now2));
        return null;
    }

    public static void main(String[] args) {
        int i = 10 / 2;
        System.out.println(i);
    }
}
