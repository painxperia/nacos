package com.zpain.config.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhangjun
 * @date 2021/7/12  15:54
 */
@Component
@Slf4j
public class Constant {

    public static String URL = "aaa";

    @PostConstruct
    public void init(){
        log.info("config start");
    }
}
