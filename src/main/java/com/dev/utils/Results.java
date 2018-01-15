package com.dev.utils;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Results implements Serializable {

	private int status;
	private  String message;
	private Object data;
	
	public Results(){
		super();
	}
	
	public Results(int status,String message,Object data) {
		super();
		this.status=status;
		this.message=message;
		this.data=data;
	}
	
	public Results(String message,Object data) {
		super();
		this.message=message;
		this.data=data;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString()
	{
		return JacksonUtil.getAllJackson(this);
	}
}
