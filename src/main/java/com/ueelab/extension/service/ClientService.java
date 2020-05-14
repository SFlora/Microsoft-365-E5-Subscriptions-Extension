package com.ueelab.extension.service;

import com.ueelab.extension.common.Result;
import com.ueelab.extension.req.ClientOperationReq;

public interface ClientService {
	Result<Void> createClient(ClientOperationReq req);
}
