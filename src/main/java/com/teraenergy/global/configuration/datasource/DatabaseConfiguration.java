package com.teraenergy.global.configuration.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Slf4j
@Configuration
@PropertySource("classpath:/application.yml")
public class DatabaseConfiguration {

//	@Bean
//	@Primary
//	@Qualifier("biHikariConfig")
//	@ConfigurationProperties(prefix="spring.datasource.bi")
//	public HikariConfig biHikariConfig() {
//		return new HikariConfig();
//	}
//
//	@Bean
//	@Primary
//	@Qualifier("biDataSource")
//	public DataSource biDataSource() throws Exception {
//		return new HikariDataSource(biHikariConfig());
//	}

	@Primary
	@Bean(name = "biDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.bi")
	public DataSource biDataSource() {
		DataSource dataSource = DataSourceBuilder.create().build();
		log.info("Datasource : {}", dataSource);
		return dataSource;
	}

//	@Bean
//	@Qualifier("groupwareHikariConfig")
//	@ConfigurationProperties(prefix="spring.datasource.groupware")
//	public HikariConfig groupwareHikariConfig() {
//		return new HikariConfig();
//	}
//
//	@Bean
//	@Qualifier("groupwareDataSource")
//	public DataSource groupwareDataSource() throws Exception {
//		return new HikariDataSource(groupwareHikariConfig());
//	}

	@Bean(name = "groupwareDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.groupware")
	public DataSource groupwareDataSource() {
		DataSource dataSource = DataSourceBuilder.create().build();
		log.info("Datasource : {}", dataSource);
		return dataSource;
	}

}