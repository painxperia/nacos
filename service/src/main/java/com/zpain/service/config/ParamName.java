package com.zpain.service.config;

import java.lang.annotation.*;

/**
 * @author zhangjun
 * @date 2021/12/27  16:55
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamName {

	/**
	 * The name of the request parameter to bind to.
	 */
	String value();

}