package com.zpain.service.config;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.ServletRequest;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author zhangjun
 * @date 2021/12/27  16:55
 */
public class ParamNameDataBinder extends ExtendedServletRequestDataBinder {

    public ParamNameDataBinder(Object target, String objectName) {
        super(target, objectName);
    }

    @Override
    public void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
        super.addBindValues(mpvs, request);
        Class<?> targetClass = Objects.requireNonNull(getTarget()).getClass();
        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            ParamName param = field.getAnnotation(ParamName.class);
            if (mpvs.contains(field.getName()) || param == null) {
                continue;
            }
            if (mpvs.contains(param.value())) {
                mpvs.add(field.getName(), mpvs.getPropertyValue(param.value()).getValue());
            }
        }
    }

}
