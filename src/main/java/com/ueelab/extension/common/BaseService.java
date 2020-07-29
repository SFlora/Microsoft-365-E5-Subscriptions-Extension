package com.ueelab.extension.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected <T> Result<T> packResult() {
		Result<T> result = new Result<>();
		result.setCode("SUCCESS");
		return result;
	}

	protected <T> Result<T> packFiledResult() {
		Result<T> result = new Result<>();
		result.setCode("FAILED");
		return result;
	}

	protected <T> Result<T> packResult(T data) {
		Result<T> result = packResult();
		result.setData(data);
		return result;
	}

}
