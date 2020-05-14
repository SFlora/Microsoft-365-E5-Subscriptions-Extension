package com.ueelab.extension.service;

import com.alibaba.fastjson.JSONObject;
import com.ueelab.extension.entity.ClientEntity;

import javax.servlet.http.HttpServletRequest;

public interface ApiCallService {

	boolean authorize(ClientEntity entity);

	JSONObject getAccessToken(ClientEntity entity);

	JSONObject refreshToken(ClientEntity entity);

	JSONObject callOneDriverApi(ClientEntity entity);

	JSONObject callOutLookApi(ClientEntity entity);

	void callback(HttpServletRequest req);
}
