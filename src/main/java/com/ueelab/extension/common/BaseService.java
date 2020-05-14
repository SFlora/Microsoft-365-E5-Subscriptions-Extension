package com.ueelab.extension.common;

import com.baomidou.mybatisplus.extension.api.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yipeng.Liu
 */
public class BaseService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected <T> Result<T> packResult() {
		return new Result<>();
	}

	protected <T> Result<T> packResult(T data) {
		Result<T> result = packResult();
		result.setData(data);
		return result;
	}

}
