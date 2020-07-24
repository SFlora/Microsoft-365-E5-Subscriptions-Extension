package com.ueelab.extension.common;

import java.io.Serializable;


public class Result<T> implements Serializable {

	protected String code;

	protected String msg;

	protected T data;

	public Result() {

	}

	public Result(T data) {
		this();
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
