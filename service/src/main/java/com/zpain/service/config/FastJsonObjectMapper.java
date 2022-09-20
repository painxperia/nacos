package com.zpain.service.config;

import com.alibaba.fastjson.JSON;
import kong.unirest.GenericType;
import kong.unirest.ObjectMapper;

public class FastJsonObjectMapper implements ObjectMapper {

	@Override
	public <T> T readValue(String value, Class<T> valueType) {
		return JSON.parseObject(value, valueType);
	}

	@Override
	public <T> T readValue(String value, GenericType<T> genericType) {
		return JSON.parseObject(value, genericType.getType());
	}

	@Override
	public String writeValue(Object value) {
		return JSON.toJSONString(value);
	}
}
