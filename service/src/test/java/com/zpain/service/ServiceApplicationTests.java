package com.zpain.service;

import com.zpain.service.controller.User;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ServiceApplicationTests {
    public static final GenericType<User> user = new GenericType<User>() {
    };

    @Test
    void contextLoads() {

        Unirest.post("").asObject(user);
    }

}
