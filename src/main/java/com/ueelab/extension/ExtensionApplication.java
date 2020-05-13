package com.ueelab.extension;

import com.ueelab.extension.config.DataSourceConfig;
import com.ueelab.extension.config.MybatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import({DataSourceConfig.class, MybatisConfig.class})
public class ExtensionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtensionApplication.class, args);
	}

}
