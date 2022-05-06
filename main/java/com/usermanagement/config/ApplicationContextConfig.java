package com.usermanagement.config;

import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.usermanagement.DAO.UserDAO;
import com.usermanagement.DAO.UserDAOImpl;
import com.usermanagement.service.UserService;
import com.usermanagement.service.UserServiceImpl;

@Configuration
@ComponentScan("com.usermanagement.*")
public class ApplicationContextConfig {

	@Autowired
	Environment env;

	@Bean(name = "viewResolver")
	public FreeMarkerViewResolver getViewResolver() {
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setPrefix("");
		viewResolver.setSuffix(".ftl");
		return viewResolver;
	}

	@Bean(name = "freemarkerConfig")
	public FreeMarkerConfigurer getFreeMarkerConfig() {
		FreeMarkerConfigurer configure = new FreeMarkerConfigurer();
		configure.setTemplateLoaderPath("/WEB-INF/templates/");
		return configure;

	}

//	@Bean(name = "dataSource")
//	public DataSource getDataSource() {
//		BasicDataSource dataSource = new BasicDataSource();
//
//		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
//		dataSource.setUrl(env.getProperty("ds.url"));
//		dataSource.setUsername(env.getProperty("ds.username"));
//		dataSource.setPassword(env.getProperty("ds.password"));
//
//		return dataSource;
//		
//	}
//
//	@Bean(name = "sessionFactory")
//	public LocalSessionFactoryBean sessionFactory() throws IOException {
//		Properties properties = new Properties();
//
//		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//		properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
//		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setPackagesToScan(new String[] { "com.usermanagement.model" });
//		sessionFactory.setDataSource(getDataSource());
//		sessionFactory.setHibernateProperties(properties);
//		
//		return sessionFactory;
//
//	}

}
