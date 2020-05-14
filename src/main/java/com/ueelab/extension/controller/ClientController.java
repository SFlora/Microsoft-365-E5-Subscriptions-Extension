package com.ueelab.extension.controller;

import com.ueelab.extension.common.Result;
import com.ueelab.extension.req.ClientOperationReq;
import com.ueelab.extension.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yipeng.Liu
 */
@RestController
@RequestMapping("/")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@PostMapping("createClient")
	public Result<Void> createClient(ClientOperationReq req) {
		return clientService.createClient(req);
	}

}
