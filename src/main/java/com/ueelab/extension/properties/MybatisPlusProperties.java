package com.ueelab.extension.properties;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;

import java.util.Properties;

/**
 * @author Yipeng.Liu
 */
public class MybatisPlusProperties {

	/** Mybatis plus properties配置文件前缀 */
	public static final String MYBATIS_PLUS_PREFIX = MybatisProperties.MYBATIS_PREFIX;

	/** 默认的扫描xml的路径 */
	public static final String DEFAULT_RESOLVE_MAPPER_LOCATIONS = "classpath:mapper/*.xml";

	/** 扫描xml的路径 */
	protected String resolveMapperLocations = DEFAULT_RESOLVE_MAPPER_LOCATIONS;

	/** mybatis plus全局配置类 */
	protected GlobalConfig globalConfig = GlobalConfigUtils.defaults();

	protected MybatisConfiguration configuration = new MybatisConfiguration();

	/** 配置属性 */
	protected Properties configurationProperties;

	public MybatisPlusProperties() {
		//默认驼峰命名转换字段
		configuration.setMapUnderscoreToCamelCase(true);
//		//默认关闭缓存
//		configuration.setCacheEnabled(false);
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
