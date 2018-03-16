package com.sun.test.demo;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.apache.tomcat.jdbc.pool.DataSource;

// 配置springBoot 扫描bean 路径
@SpringBootApplication(scanBasePackages = {"com.sun.*"})
// 配置mybatis namespace路径
@MapperScan("com.sun.test.dao")
public class DemoApplication {

	/**
	 * DataSource配置
	 *
	 * @return the data source
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

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
