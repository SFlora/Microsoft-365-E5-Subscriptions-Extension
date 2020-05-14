package com.ueelab.extension.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yipeng.Liu
 */
public class BaseService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected <T> Result<T> packResult() {
		Result<T> result = new Result<>();
		result.setCode("200");
		result.setMsg("SUCCESS");
		return result;
	}

	protected <T> Result<T> packFiledResult() {
		Result<T> result = new Result<>();
		result.setCode("500");
		result.setMsg("FAILED");
		return result;
	}

	protected <T> Result<T> packResult(T data) {
		Result<T> result = packResult();
		result.setData(data);
		return result;
	}

}
