package com.ueelab.extension.service;

import com.alibaba.fastjson.JSONObject;
import com.ueelab.extension.common.BaseService;
import com.ueelab.extension.dao.ClientDao;
import com.ueelab.extension.entity.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@EnableScheduling
public class TaskService extends BaseService implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private ApiCallService apiCallService;

	@Autowired
	private ClientDao clientDao;

	private final ExecutorService executor = Executors.newCachedThreadPool();

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		List<ClientEntity> entityList = clientDao.selectAll();
		if (Objects.isNull(entityList) || entityList.size() == 0) {
			return;
		}
		logger.info("Registered clients count: " + entityList.size());
		entityList.forEach((item) -> logger.info(item.getClientId()));
		logger.info("---------------------------------------");
	}

	//@Scheduled(fixedDelay = 3600000,initialDelay = 3000)
	@Scheduled(cron = "0 0 0,6,12,18 * * ?") // 6 HOURS
	public void task() {
		List<ClientEntity> entityList = clientDao.selectAll();
		if (Objects.isNull(entityList) || entityList.size() == 0) {
			return;
		}
		entityList.forEach(entity -> executor.execute(() -> this.task(entity)));
	}

	private void task(ClientEntity entity) {
		this.authorize(entity);
		this.getToken(entity);
		this.refreshToken(entity);
		this.oneDriver(entity);
		this.outLook(entity);
		this.sendMail(entity);
	}

	private void authorize(ClientEntity entity) {
		if (Objects.nonNull(entity.getAuthorizationCode())) {
			return;
		}
		if (!apiCallService.authorize(entity)) {
			throw new RuntimeException("AUTHORIZATION CODE IS NULL");
		}
	}

	private void getToken(ClientEntity entity) {
		if (Objects.nonNull(entity.getAccessToken())) {
			return;
		}
		JSONObject result = apiCallService.getAccessToken(entity);
		tokenOperatingAfter(entity, result);
	}

	private void refreshToken(ClientEntity entity) {
		if (entity.getAccessTokenExpiredTime().getTime() > System.currentTimeMillis() - 60 * 1000) {
			return;
		}
		JSONObject result = apiCallService.refreshToken(entity);
		tokenOperatingAfter(entity, result);
	}

	private void tokenOperatingAfter(ClientEntity entity, JSONObject result) {
		try {
			String error = result.getString("error");
			if (Objects.nonNull(error)) {
				entity.setErrorMsg(result.getString("error_description"));
				entity.setLastCallResult("FAILED");
				throw new RuntimeException(result.getString("error_description"));
			} else {
				entity.setTokenType(result.getString("token_type"));
				entity.setAccessToken(result.getString("access_token"));
				entity.setRefreshToken(result.getString("refresh_token"));
				entity.setAccessTokenExpiredTime(new Timestamp(System.currentTimeMillis() + result.getInteger("expires_in") * 1000));
				entity.setLastCallResult("SUCCEED");
			}
		} finally {
			clientDao.updateById(entity);
		}

	}

	private void oneDriver(ClientEntity entity) {
		JSONObject result = apiCallService.callOneDriverApi(entity);
		this.checkResult(entity, result);
	}

	private void outLook(ClientEntity entity) {
		JSONObject result = apiCallService.callOutLookApi(entity);
		this.checkResult(entity, result);
	}

	private void sendMail(ClientEntity entity) {
		JSONObject result = apiCallService.sendMail(entity);
		this.checkResult(entity, result);
	}

	private void checkResult(ClientEntity entity, JSONObject result) {
		try {
			String resultString = result.toJSONString();
			logger.info(entity.getId() + ":  " + resultString);
			JSONObject error = result.getJSONObject("error");
			if (Objects.isNull(error)) {
				logger.info("clientId: " + entity.getClientId() + " " + result.get("apiType") + " API call succeeded");
				entity.setLastCallResult("SUCCEED");
			} else if ("InvalidAuthenticationToken".equals(error.getString("code"))) {
				logger.error("Invalid authentication token");
				logger.info("Retrying...");
				Thread.sleep(1000);
				executor.submit(() -> this.task(entity));
			} else {
				entity.setErrorMsg(error.toJSONString());
				entity.setLastCallResult("FAILED");
				throw new RuntimeException(error.toJSONString());
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			entity.setLastCallTime(new Timestamp(System.currentTimeMillis()));
			clientDao.updateById(entity);
		}
	}

}
