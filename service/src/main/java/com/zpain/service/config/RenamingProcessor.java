package com.zpain.service.config;

import com.zpain.service.controller.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.ServletRequest;

/**
 * @author zhangjun
 * @date 2021/12/27  17:00
 */
public class RenamingProcessor extends ServletModelAttributeMethodProcessor {

    @Autowired
    private ApplicationUtil applicationContextUtil;

    public RenamingProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        ParamNameDataBinder paramNameDataBinder = new ParamNameDataBinder(binder.getTarget(), binder.getObjectName());
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = applicationContextUtil.getBean(RequestMappingHandlerAdapter.class);
        requestMappingHandlerAdapter.getWebBindingInitializer().initBinder(paramNameDataBinder);
        paramNameDataBinder.bind(request.getNativeRequest(ServletRequest.class));
    }

}
