package com.longpc.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class HibernateConfig {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=QuizWeb");
		dataSource.setUsername("sa");
		dataSource.setPassword("Goboi123");
		return dataSource;
	}
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean bean=new LocalSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.longpc.entity");
		Properties properties= new Properties();
		properties.put("hibernate.dialect","org.hibernate.dialect.SQLServer2012Dialect");
		properties.put("hibernate.show_sql",true);
		properties.put("hibernate.format_sql", true);
		bean.setHibernateProperties(properties);
		return bean;
	}
}
