package com.ueelab.extension.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;


@TableName("t_client")
public class ClientEntity {

	private String id;
	private String clientId;
	private String clientSecret;
	private String authorizationCode;
	private String accessToken;
	private String refreshToken;
	private String tokenType;
	private Timestamp accessTokenExpiredTime;
	private Timestamp lastCallTime;
	private String lastCallResult;
	private String errorMsg;
	private Timestamp createTime;
	private Integer isDelete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Timestamp getAccessTokenExpiredTime() {
		return accessTokenExpiredTime;
	}

	public void setAccessTokenExpiredTime(Timestamp accessTokenExpiredTime) {
		this.accessTokenExpiredTime = accessTokenExpiredTime;
	}

	public Timestamp getLastCallTime() {
		return lastCallTime;
	}

	public void setLastCallTime(Timestamp lastCallTime) {
		this.lastCallTime = lastCallTime;
	}

	public String getLastCallResult() {
		return lastCallResult;
	}

	public void setLastCallResult(String lastCallResult) {
		this.lastCallResult = lastCallResult;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}