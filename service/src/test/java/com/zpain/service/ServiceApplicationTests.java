package com.zpain.service;

import com.zpain.service.controller.ApplicationUtil;
import com.zpain.service.controller.TestBean;
import com.zpain.service.controller.User;
import com.zpain.service.domain.Result;
import com.zpain.service.mapper.OrderMapper;
import com.zpain.service.pojo.OrderInfo;
import com.zpain.service.service.OrderIService;
import com.zpain.service.service.OrderService;
import com.zpain.service.util.KeyGenerator;
import com.zpain.service.util.bloom.RedisBloom;
import kong.unirest.GenericType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@SpringBootTest
@Slf4j
class ServiceApplicationTests {
    public static final GenericType<User> user = new GenericType<User>() {
    };


    @Test
    void contextLoads() {
//        SnowflakeShardingKeyGenerator keyGenerator = new SnowflakeShardingKeyGenerator();
//        String s = keyGenerator.generateKey().toString();
//        System.out.println(s);
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            byte[] address = inetAddress.getAddress();
            System.out.println("address:" + address);
            System.out.println(address.length);
            System.out.println(inetAddress.getHostAddress());
        } catch (Exception e) {

        }
    }

    @Autowired
    private KeyGenerator keyGenerator;

    @Test
    public void getId() {
        String stringId = keyGenerator.generatorSnowflakeKey();
        System.out.println(stringId);
    }

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void batchInsert() {
        log.info("start time:{}", LocalDateTime.now());
        List<OrderInfo> list = IntStream.rangeClosed(1, 10000).mapToObj(i -> {
            OrderInfo orderInfo = new OrderInfo();
            Long id = keyGenerator.generatorSnowflakeKeyLong();
            orderInfo.setId(id);
            orderInfo.setOrderId(String.valueOf(i));
            orderInfo.setOrderName(String.format("%s:%s", "test", i));
            orderInfo.setCreateDate(LocalDateTime.now());
            orderInfo.setUpdateDate(LocalDateTime.now());
            return orderInfo;
        }).collect(Collectors.toList());
        orderMapper.insertBatchSomeColumn(list);
        log.info("start end:{}", LocalDateTime.now());
    }


    @Test
    public void get() {
        List<OrderInfo> list = orderMapper.selectList(null);
        log.info("list:{}", list);
    }

    @Autowired
    private OrderIService orderIService;

    @Test
    public void getAll() {
    }

    @Test
    public void insertBatch() {
        log.info("start time:{}", LocalDateTime.now());
        List<OrderInfo> list = IntStream.rangeClosed(1, 10000).mapToObj(i -> {
            OrderInfo orderInfo = new OrderInfo();
            Long id = keyGenerator.generatorSnowflakeKeyLong();
            orderInfo.setId(id);
            orderInfo.setOrderId(String.valueOf(i));
            orderInfo.setOrderName(String.format("%s:%s", "test", i));
            orderInfo.setCreateDate(LocalDateTime.now());
            orderInfo.setUpdateDate(LocalDateTime.now());
            return orderInfo;
        }).collect(Collectors.toList());
        orderIService.saveBatch(list);
        log.info("start end:{}", LocalDateTime.now());
    }

    @Autowired
    private OrderService orderService;

    @Test
    public void getAllInfo() {
//        Result<OrderInfo> all = orderService.getAll();
        Result<OrderInfo> all2 = orderService.getAll2();
    }

    @Test
    public void test() {
        List<OrderInfo> list = IntStream.rangeClosed(1, 2).mapToObj(i -> {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderId(String.valueOf(i));
            orderInfo.setCreateDate(LocalDateTime.now());
            return orderInfo;
        }).collect(Collectors.toList());

//        List<OrderExcel> orderExcels = OrderInfoConverter.INSTANCE.toOrderExcelList(list);
//        log.info("a:{}", JSON.toJSONString(orderExcels));
    }

    @Autowired
    private RedisBloom redisBloom;

    @Test
    public void redisBloom() {
        boolean b = redisBloom.checkBloom("z:1");
        log.info("b:{}", b);
    }


    @Autowired
    private ApplicationUtil applicationUtil;

    @Test
    public void testBean() {
        TestBean bean = applicationUtil.getBean(TestBean.class);
        String url = bean.a();
        log.info("url:{}", url);
    }

    @Test
    public void testSql() throws Exception {
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 3
                , TimeUnit.SECONDS, queue, new ThreadPoolExecutor.AbortPolicy());

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        for (int i = 0; i < list.size(); i++) {
            final int a = list.get(i);
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + a);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    System.out.println("error:" + e);
                }
            });
        }
    }

    public static void main(String[] args) throws Exception{
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(4);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 3
                , TimeUnit.SECONDS, queue, new ThreadPoolExecutor.CallerRunsPolicy());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            list.add(i);
        }
        for (int i = 0; i < list.size(); i++) {
            final int a = list.get(i);
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + a);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    System.out.println("error:" + e);
                }
            });
        }

//        ForkJoinPool pool = new ForkJoinPool(5);
//        pool.submit( ()->{
//            list.parallelStream().forEach( i ->{
//                try {
//                    System.out.println(Thread.currentThread().getName() + ":" + i);
//                    TimeUnit.SECONDS.sleep(2);
//                }catch (Exception e){
//
//                }
//            } );
//        } ).get();


    }


}
