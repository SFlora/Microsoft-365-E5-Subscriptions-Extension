package com.ueelab.extension.config;

import com.ueelab.extension.properties.MybatisProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author Yipeng.Liu
 */
@Configuration
@ConditionalOnBean(DataSource.class)
public class MybatisConfig {

	/**
	 * Mybatis
	 */
	@Bean
	@ConditionalOnMissingBean
	public SqlSessionFactoryBean createSqlSessionFactory(MybatisProperties properties, DataSource dataSource) throws Exception {
		if (properties.getConfiguration().getLogImpl() == null) {
			properties.getConfiguration().setLogImpl(MybatisLogbackLog.class);
		}

		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setConfiguration(properties.getConfiguration());

		if (Objects.nonNull(properties.getResolveMapperLocations())) {
			bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(properties.getResolveMapperLocations()));
		}

		return bean;
	}

	/**
	 * Mybatis properties
	 */
	@Bean
	@ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
	@ConditionalOnMissingBean
	public MybatisProperties createMybatisProperties() {
		return new MybatisProperties();
	}

}
