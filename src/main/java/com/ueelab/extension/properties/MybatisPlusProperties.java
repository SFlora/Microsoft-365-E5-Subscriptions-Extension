package com.ueelab.extension.properties;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;

import java.util.Properties;


public class MybatisPlusProperties {

	public static final String MYBATIS_PLUS_PREFIX = MybatisProperties.MYBATIS_PREFIX;

	public static final String DEFAULT_RESOLVE_MAPPER_LOCATIONS = "classpath:mapper/*.xml";

	protected String resolveMapperLocations = DEFAULT_RESOLVE_MAPPER_LOCATIONS;

	protected GlobalConfig globalConfig = GlobalConfigUtils.defaults();

	protected MybatisConfiguration configuration = new MybatisConfiguration();

	protected Properties configurationProperties;

	public MybatisPlusProperties() {
		configuration.setMapUnderscoreToCamelCase(true);
	}

	public void setResolveMapperLocations(String resolveMapperLocations) {
		this.resolveMapperLocations = resolveMapperLocations;
	}

	public String getResolveMapperLocations() {
		return resolveMapperLocations;
	}

	public void setConfiguration(MybatisConfiguration configuration) {
		this.configuration = configuration;
	}

	public MybatisConfiguration getConfiguration() {
		return configuration;
	}

	public void setGlobalConfig(GlobalConfig globalConfig) {
		this.globalConfig = globalConfig;
	}

	public GlobalConfig getGlobalConfig() {
		return globalConfig;
	}

	public void setConfigurationProperties(Properties configurationProperties) {
		this.configurationProperties = configurationProperties;
	}

	public Properties getConfigurationProperties() {
		return configurationProperties;
	}
}
