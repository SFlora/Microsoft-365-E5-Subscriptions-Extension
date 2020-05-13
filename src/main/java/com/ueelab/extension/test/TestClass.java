package com.ueelab.extension.test;

import com.ueelab.extension.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Yipeng.Liu
 */
@Component
public class TestClass implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private TestDao testDao;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		Map<String, Object> test = testDao.test();
		test.forEach((k,v) -> System.out.println(k + " " + v));
	}

}
