package com.zpain.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhangjun
 * @date 2021/8/24  16:57
 */
@Component
@Slf4j
public class TestBean {

    @PostConstruct
    public void init(){
        log.info("service start");
    }

    public String a(){
        return "a";
    }
}
