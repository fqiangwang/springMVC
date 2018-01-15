package com.dev.utils;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
	private ObjectMapper objectMapper = null;

	public static String getJackson(Object obj)
	{
		JacksonUtil jsonUtil=new JacksonUtil();

		return jsonUtil.getJson(obj);
	}

	public static Object getJacksonObj(String json, Class clazz)
	{
		JacksonUtil jsonUtil=new JacksonUtil();

		return jsonUtil.getObj(json, clazz);
	}

	public JacksonUtil() {
		objectMapper = new ObjectMapper();
	}

	public String getJson(Object obj) {
		try {
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getObj(String json, Class clazz){
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getAllJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * ��ȡ��������ֶ���Ϣ����Ԫ������Ҫ
	 */
	public static String getAllJackson(Object obj)
	{
		JacksonUtil jsonUtil=new JacksonUtil();

		return jsonUtil.getAllJson(obj);
	}
}
