
package com.sun.test.demo;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * <b><code>DemoApplication</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2018/3/16 14:48
 *
 * @author sunjinpeng
 * @version 0.1.0
 * @since demo 0.1.0
 */
// 配置springBoot 扫描bean 路径
@SpringBootApplication(scanBasePackages = {"com.sun.*"})
// 配置mybatis namespace路径
@MapperScan("com.sun.test.dao")
public class DemoApplication  extends SpringBootServletInitializer {


	/**
	 *
	 * @param application application
	 * @return SpringApplicationBuilder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	/**
	 * DataSource配置
	 *
	 * @return the data source
	 * @since demo 0.1.0
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return new DataSource();
	}

	/**
	 * 提供SqlSession
	 *
	 * @return the sql session factory
	 * @throws Exception the exception
	 * @since demo 0.1.0
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		/* DateSource */
		sqlSessionFactoryBean.setDataSource(dataSource());
		/* Mapper 路径 */
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	/**
	 * main 函数，程序入口
	 * @param args string[]
	 * @since demo 0.1.0
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
