package com.zpain.service.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhangjun
 * @date 2021/12/3  9:54
 */
@Component
public class ApplicationUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    public <T> T getBean(String beanName){
        return  (T)applicationContext.getBean(beanName);
    }

    public <T> T getBean(String beanName,Class<T> clazz){
        return  (T)applicationContext.getBean(beanName, clazz);
    }

    public <T> T getBean(Class<T> clazz){
        return  (T)applicationContext.getBean(clazz);
    }
}
