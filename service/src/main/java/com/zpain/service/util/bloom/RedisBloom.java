package com.zpain.service.util.bloom;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhangjun
 * @date 2021/12/2  15:55
 */
@Component
@Slf4j
public class RedisBloom {

    @Resource(name = "redissonClient")
    private RedissonClient redissonClient;

    public boolean checkBloom(String key) {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("z");
        boolean bl = bloomFilter.tryInit(6, 0);

        for (int i = 0; i < 10; i++) {
            bloomFilter.add("z1:" + i);
        }
        log.info("size1:{}",bloomFilter.count());
        boolean contains = bloomFilter.contains(key);
        return contains;
    }


}
