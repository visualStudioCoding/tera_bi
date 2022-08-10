package com.teraenergy.global.configuration.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.yml")
public class GroupwareMyBatisConfiguration {

	@Value("${mybatis.mapper-locations}")
	private String mapperLocations;

	@Value("${mybatis.config-location}")
	private String configPath;

	@Bean(name = "groupwareSqlSessionFactory")
	public SqlSessionFactory groupwareSqlSessionFactory(
			@Qualifier("groupwareDataSource") DataSource groupwareDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(groupwareDataSource);
		//MyBatis config파일 위치
		Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource(configPath);
		sqlSessionFactoryBean.setConfigLocation(myBatisConfig);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperLocations));

		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "groupwareSqlSessionTemplate")
	public SqlSessionTemplate groupwareSqlSessionTemplate(
			@Qualifier("groupwareSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name = "groupwareDataSourceTransactionManager")
	public DataSourceTransactionManager groupwareTransactionManager (@Qualifier("groupwareDataSource") DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}
}
