package com.zpain.service;

import com.zpain.service.controller.User;
import com.zpain.service.util.KeyGenerator;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;


@SpringBootTest
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
            System.out.println("address:"+address);
            System.out.println(address.length);
            System.out.println(inetAddress.getHostAddress());
        }catch (Exception e){

        }
    }

    @Autowired
    private KeyGenerator keyGenerator;

    @Test
    public void getId(){
        String stringId = keyGenerator.generatorSnowflakeKey();
        System.out.println(stringId);
    }
}
