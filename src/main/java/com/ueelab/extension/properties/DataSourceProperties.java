package com.ueelab.extension.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

/**
 * @author Yipeng.Liu
 */
@Configuration
@ConfigurationProperties(DataSourceProperties.DATA_SOURCE_PREFIX)
public class DataSourceProperties {

	/** DataSource properties配置文件前缀 */
	public static final String DATA_SOURCE_PREFIX = "jdbc.datasource";

	/** 地址 */
	protected String url;

	/** 用户名 */
	protected String username;

	/** 密码 */
	protected String password;

	/** 驱动类 */
	protected String driverClassName;

	protected Integer initialSize;

	protected Integer minIdle;

	protected Integer maxActive;

	protected Long maxWait;

	protected Long timeBetweenEvictionRunsMillis;

	protected Long minEvictableIdleTimeMillis;

	protected String validationQuery;

	protected Boolean testWhileIdle;

	protected Boolean testOnBorrow;

	protected Boolean testOnReturn;

	protected Boolean removeAbandoned;

	protected Long removeAbandonedTimeoutMillis;

	protected String filters;

	protected Boolean logAbandoned;

	protected Properties connectProperties;

	protected String connectionProperties;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public Integer getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Integer getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	public Long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(Long maxWait) {
		this.maxWait = maxWait;
	}

	public Long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public Long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public Boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public Boolean getTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(Boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public Boolean getRemoveAbandoned() {
		return removeAbandoned;
	}

	public void setRemoveAbandoned(Boolean removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	public Long getRemoveAbandonedTimeoutMillis() {
		return removeAbandonedTimeoutMillis;
	}

	public void setRemoveAbandonedTimeoutMillis(Long removeAbandonedTimeoutMillis) {
		this.removeAbandonedTimeoutMillis = removeAbandonedTimeoutMillis;
	}

	public Boolean getLogAbandoned() {
		return logAbandoned;
	}

	public void setLogAbandoned(Boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getFilters() {
		return filters;
	}

	public void setConnectProperties(Properties connectProperties) {
		this.connectProperties = connectProperties;
	}

	public Properties getConnectProperties() {
		return connectProperties;
	}

	public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	public String getConnectionProperties() {
		return connectionProperties;
	}
}
