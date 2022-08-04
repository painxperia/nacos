package com.zpain.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author zhangjun
 * @date 2021/12/23  14:34
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    protected RenamingProcessor renamingProcessor() {
        return new RenamingProcessor(true);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(renamingProcessor());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }
}
