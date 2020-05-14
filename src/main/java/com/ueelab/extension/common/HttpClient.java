package com.ueelab.extension.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yipeng.Liu
 */

public class HttpClient {
	public static final Integer GET = 1;
	public static final Integer POST = 2;
	private HttpRequestBase request;
	private List<NameValuePair> param;
	private String uri;

	public HttpClient(String uri) {
		this.uri = uri;
	}

	public HttpClient setParam(String[][] params) {
		this.param = new ArrayList<>();
		for (String[] param : params) {
			this.param.add(new BasicNameValuePair(param[0], param[1]));
		}
		return this;
	}

	public HttpClient setRequest(Integer type) {
		try {
			URIBuilder uriBuilder = new URIBuilder(uri);
			switch (type) {
				case 1:
					request = new HttpGet(uriBuilder.build());
					break;
				case 2:
					request = new HttpPost(uriBuilder.build());
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + type);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return this;
	}

	public HttpClient setApplicationXWWWFormUrlencoded() {
		try {
			request.setHeader("Content-Type", "application/x-www-form-urlencoded");
			if (request instanceof HttpPost) {
				((HttpPost) request).setEntity(new UrlEncodedFormEntity(param, "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return this;
	}

	public HttpClient setAuthorization(String authorization) {
		request.setHeader("Authorization", authorization);
		return this;
	}

	public JSONObject call() {
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse execute;
		String json = null;
		try {
			execute = client.execute(request);
			HttpEntity entity = execute.getEntity();
			json = EntityUtils.toString(entity, Consts.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSONObject.parseObject(json);
	}
}
