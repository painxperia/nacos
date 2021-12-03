package com.zpain.service.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author zhangjun
 * @date 2021/12/3  10:12
 */
@Component
public class BeanNameAwareUtil implements BeanNameAware , BeanFactoryAware {

    private String beanName;
    private BeanFactory beanFactory;

    @Override
    public void setBeanName(String s) {

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
