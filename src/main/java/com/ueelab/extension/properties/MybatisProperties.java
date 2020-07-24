package com.ueelab.extension.properties;

import org.apache.ibatis.session.Configuration;


public class MybatisProperties {

	public static final String MYBATIS_PREFIX = "mybatis";

	public static final String DEFAULT_RESOLVE_MAPPER_LOCATIONS = "classpath:mapper/*.xml";

	protected String resolveMapperLocations = DEFAULT_RESOLVE_MAPPER_LOCATIONS;

	protected Configuration configuration = new Configuration();

	public MybatisProperties() {
		configuration.setMapUnderscoreToCamelCase(true);
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
