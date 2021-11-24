package com.zpain.service.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * @author zhangjun
 * @date 2021/11/15  13:48
 */
@Component
@Slf4j
public class KeyGenerator implements InitializingBean {

    private static String workerId = "worker.id";

    SnowflakeShardingKeyGenerator generator = new SnowflakeShardingKeyGenerator();

    public String generatorSnowflakeKey() {
        return generator.generateKey().toString();
    }

    public Long generatorSnowflakeKeyLong(){
        return Long.parseLong(generatorSnowflakeKey());
    }

    public Long initWorkerId() {
        Long workerId = 0L;
        int ipv4Length = 4;
        int ipv6Length = 16;
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
        // 得到IP地址的byte[]形式值
        byte[] ipAddressByteArray = address.getAddress();

        //如果是IPV4，计算方式是遍历byte[]，然后把每个IP段数值相加得到的结果就是workerId
        if (ipAddressByteArray.length == ipv4Length) {
            for (byte byteNum : ipAddressByteArray) {
                workerId += byteNum & 0xFF;
            }
            //如果是IPV6，计算方式是遍历byte[]，然后把每个IP段后6位（& 0B111111 就是得到后6位）数值相加得到的结果就是workerId
        } else if (ipAddressByteArray.length == ipv6Length) {
            for (byte byteNum : ipAddressByteArray) {
                workerId += byteNum & 0B111111;
            }
        } else {
            throw new IllegalStateException("Bad LocalHost InetAddress, please check your network!");
        }
        return workerId;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties properties = generator.getProperties();
        if (properties == null) {
            properties = new Properties();
        }
        String ip = InetAddress.getLocalHost().getHostAddress();
        String workerId = initWorkerId().toString();
        log.info("ip:{},workerId:{}", ip, workerId);
        properties.setProperty(workerId, workerId);
    }
}
