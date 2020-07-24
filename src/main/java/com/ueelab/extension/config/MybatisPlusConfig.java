package com.ueelab.extension.config;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.ueelab.extension.properties.MybatisPlusProperties;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Configuration
@ConditionalOnBean(DataSource.class)
public class MybatisPlusConfig {

	@Bean
	@ConfigurationProperties(prefix = MybatisPlusProperties.MYBATIS_PLUS_PREFIX)
	@ConditionalOnMissingBean
	public MybatisPlusProperties createMybatisPlusProperties() {
		return new MybatisPlusProperties();
	}

	/**
	 * Mybatis
	 */
	@Bean
	@ConditionalOnMissingBean
	@Primary
	public MybatisSqlSessionFactoryBean createMybatisSqlSessionFactory(MybatisPlusProperties properties, DataSource dataSource
			, ISqlInjector sqlInjector)
			throws Exception {
		if (properties.getConfiguration().getLogImpl() == null) {
			properties.getConfiguration().setLogImpl(MybatisLogbackLog.class);
		}

		properties.getGlobalConfig().setSqlInjector(sqlInjector);

		MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setGlobalConfig(properties.getGlobalConfig());
		bean.setConfiguration(properties.getConfiguration());
		bean.setConfigurationProperties(properties.getConfigurationProperties());

		if (StringUtils.isNotBlank(properties.getResolveMapperLocations())) {
			bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(properties.getResolveMapperLocations()));
		}

		bean.setPlugins(createPaginationInterceptor());

		return bean;
	}

	/**
	 * 分页插件
	 */
	@Bean
	@ConditionalOnMissingBean
	public PaginationInterceptor createPaginationInterceptor () {
		return new PaginationInterceptor();
	}

	/**
	 * 创建sql解析注册器
	 */
	@Bean
	@ConditionalOnMissingBean
	public ISqlInjector createSqlInjector() {
		return new AbstractSqlInjector() {
			@Override
			public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
				return Stream.of(
						new Insert(),
						new Delete(),
						new DeleteByMap(),
						new DeleteById(),
						new DeleteBatchByIds(),
						new SelectById(),
						new SelectBatchByIds(),
						new SelectByMap(),
						new SelectOne(),
						new SelectCount(),
						new SelectMaps(),
						new SelectMapsPage(),
						new SelectObjs(),
						new SelectList(),
						new SelectPage(),
						new AbstractMethod() {
							@Override
							public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
								SqlMethod sqlMethod = SqlMethod.UPDATE_BY_ID;
								String additional = this.optlockVersion(tableInfo) + tableInfo.getLogicDeleteSql(true, false);
								String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), this.sqlSet(false, false, tableInfo, false, "et", "et."), tableInfo.getKeyColumn(), "et." + tableInfo.getKeyProperty(), additional);
								SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
								return this.addUpdateMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource);
							}
						}
				).collect(Collectors.toList());
			}
		};
	}
}
