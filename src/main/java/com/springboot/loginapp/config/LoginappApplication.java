package com.springboot.loginapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * <P>
 * This class is used for keeping all the configuration for the application like
 * configuring Data Source, JDBC Template, Component Scan & read Application
 * Property
 * </P>
 * 
 * @author munirathinavel
 *
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.springboot.loginapp")
@PropertySource(value = { "classpath:application.properties" })
public class LoginappApplication {

	private static final String DATASOURCE_PASSWORD = "datasource.password";
	private static final String DATASOURCE_USERNAME = "datasource.username";
	private static final String DATASOURCE_URL = "datasource.url";
	private static final String DATASOURCE_DRIVER_CLASS_NAME = "datasource.driver-class-name";

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(LoginappApplication.class, args);
	}

	/*
	 * Postgresql database configuration goes here.
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(DATASOURCE_DRIVER_CLASS_NAME));
		dataSource.setUrl(env.getRequiredProperty(DATASOURCE_URL));
		dataSource.setUsername(env.getRequiredProperty(DATASOURCE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(DATASOURCE_PASSWORD));
		return dataSource;
	}

	/*
	 * Configure JdbcTemplate by setting datasource.
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}
}
