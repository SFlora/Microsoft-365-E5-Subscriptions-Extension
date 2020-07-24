package com.ueelab.extension.service.impl;

import com.ueelab.extension.common.BaseService;
import com.ueelab.extension.common.Result;
import com.ueelab.extension.dao.ClientDao;
import com.ueelab.extension.entity.ClientEntity;
import com.ueelab.extension.req.ClientOperationReq;
import com.ueelab.extension.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;


@Service
public class ClientServiceImpl extends BaseService implements ClientService {

	@Autowired
	private ClientDao clientDao;

	@Override
	public Result<Void> createClient(ClientOperationReq req) {
		ClientEntity entity = new ClientEntity();
		entity.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		entity.setClientId(req.getClientId());
		entity.setClientSecret(req.getClientSecret());
		entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
		entity.setIsDelete(0);
		clientDao.insert(entity);
		return packResult();
	}
}
