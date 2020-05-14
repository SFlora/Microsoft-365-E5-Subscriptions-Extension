package com.ueelab.extension.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ueelab.extension.common.BaseService;
import com.ueelab.extension.common.HttpClient;
import com.ueelab.extension.common.Result;
import com.ueelab.extension.dao.ClientDao;
import com.ueelab.extension.entity.ClientEntity;
import com.ueelab.extension.service.ApiCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author Yipeng.Liu
 */
@Service
public class ApiCallServiceImpl extends BaseService implements ApiCallService {

	@Value("${apiCall.redirectUri}")
	private String redirectUri;

	@Autowired
	private ClientDao clientDao;

	@Override
	public boolean authorize(ClientEntity entity) {
		return false;
	}

	@Override
	public JSONObject getAccessToken(ClientEntity entity){
		return new HttpClient("https://login.microsoftonline.com/common/oauth2/v2.0/token")
				.setRequest(HttpClient.POST)
				.setParam(new String[][]{
						{"code", entity.getAuthorizationCode()},
						{"client_id", entity.getClientId()},
						{"client_secret", entity.getClientSecret()},
						{"redirect_uri", redirectUri},
						{"grant_type", "authorization_code"},
				})
				.setApplicationXWWWFormUrlencoded()
				.call();
	}

	@Override
	public JSONObject refreshToken(ClientEntity entity) {
		return new HttpClient("https://login.microsoftonline.com/common/oauth2/v2.0/token")
				.setRequest(HttpClient.POST)
				.setParam(new String[][]{
						{"client_id", entity.getClientId()},
						{"client_secret", entity.getClientSecret()},
						{"redirect_uri", redirectUri},
						{"refresh_token", entity.getRefreshToken()},
						{"grant_type", "refresh_token"},
				})
				.setApplicationXWWWFormUrlencoded()
				.call();

	}

	@Override
	public JSONObject callOneDriverApi(ClientEntity entity) {
		JSONObject result = new HttpClient("https://graph.microsoft.com/v1.0/me/drive/root")
				.setRequest(HttpClient.GET)
				.setAuthorization(entity.getTokenType() + " " + entity.getAccessToken())
				.call();
		result.put("apiType", "OneDriver");
		return result;
	}

	@Override
	public JSONObject callOutLookApi(ClientEntity entity) {
		JSONObject result = new HttpClient("https://graph.microsoft.com/v1.0/me/mailFolders")
				.setRequest(HttpClient.GET)
				.setAuthorization(entity.getTokenType() + " " + entity.getAccessToken())
				.call();
		result.put("apiType", "OutLook");
		return result;
	}

	@Override
	public Result<Void> callback(HttpServletRequest req) {
		String code = this.getSingleValue(req, "code");
		String state = this.getSingleValue(req, "state");
		Result<Void> result = packResult();
		if (Objects.isNull(state)) {
			logger.error(JSONObject.toJSONString(req.getParameterMap()));
			result.setMsg("授权失败");
			return result;
		}
		JSONObject jsonObject = JSONObject.parseObject(state);
		String clientId = jsonObject.getString("clientId");
		ClientEntity entity = clientDao.selectByClientId(clientId);
		entity.setAuthorizationCode(code);
		clientDao.updateById(entity);
		logger.info("授权成功 : " + state);
		result.setMsg("授权成功");
		return result;
	}

	private String getSingleValue(HttpServletRequest req, String key) {
		Map<String, String[]> map = req.getParameterMap();
		String[] values = map.get(key);
		if (Objects.isNull(values)) {
			return null;
		} else {
			return values[0];
		}
	}

}
