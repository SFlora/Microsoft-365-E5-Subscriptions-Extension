package com.ueelab.extension.properties;

import org.apache.ibatis.session.Configuration;

/**
 * @author Yipeng.Liu
 */
public class MybatisProperties {

	/** Mybatis properties配置文件前缀 */
	public static final String MYBATIS_PREFIX = "mybatis";

	/** 默认的扫描xml的路径 */
	public static final String DEFAULT_RESOLVE_MAPPER_LOCATIONS = "classpath:mapper/*.xml";

	/** 扫描xml的路径 */
	protected String resolveMapperLocations = DEFAULT_RESOLVE_MAPPER_LOCATIONS;

	protected Configuration configuration = new Configuration();

	public MybatisProperties() {
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

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

}
