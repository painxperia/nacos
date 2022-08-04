package com.zpain.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjun
 * @date 2022-08-04  13:51
 */
@Getter
@AllArgsConstructor
public enum TestEnum {
    /**
     *
     */
    ONE("1", "1"),
    TWO("2", "2");
    private final String a;
    private final String b;

    public static final Map<String, String> MAP = new HashMap<>(10);

    static {
        Arrays.stream(TestEnum.values()).forEach(t -> {
            MAP.put(t.getA(), t.getB());
        });
    }
}
