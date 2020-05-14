package com.ueelab.extension.controller;

import com.ueelab.extension.common.Result;
import com.ueelab.extension.service.ApiCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yipeng.Liu
 */
@RestController
@RequestMapping("/")
public class ApiCallController {

	@Autowired
	private ApiCallService apiCallService;

	@GetMapping("callback")
	public Result<Void> callback(HttpServletRequest req) {
		return apiCallService.callback(req);
	}

}
