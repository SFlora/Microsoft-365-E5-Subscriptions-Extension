package com.ueelab.extension.service;

import com.alibaba.fastjson.JSONObject;
import com.ueelab.extension.common.Result;
import com.ueelab.extension.entity.ClientEntity;

import javax.servlet.http.HttpServletRequest;

public interface ApiCallService {

	boolean authorize(ClientEntity entity);

	Result<Void> callback(HttpServletRequest req);

	JSONObject getAccessToken(ClientEntity entity);

	JSONObject refreshToken(ClientEntity entity);

	JSONObject callOneDriverApi(ClientEntity entity);

	JSONObject callOutLookApi(ClientEntity entity);

	JSONObject sendMail(ClientEntity entity);
}
