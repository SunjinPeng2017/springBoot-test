package com.sun.test.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <b><code>SwaggerConfig</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2018/3/16 11:48
 *
 * @author sunjinpeng
 * @version 0.1.0
 * @since demo 0.1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Create rest api docket.
     *
     * @return the docket
     * @since demo 0.1.0
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.host("192.168.6.23") 用于改变请求的url
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.richstone.cmcznm"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Api info api info.
     *
     * @return the api info
     * @since demo 0.1.0
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(" RESTful APIs")
                .description("RESTful APIs")
                .version("0.1.0")
                .license("Copyright 2018,  Company Limited,All rights reserved.")
                .build();
    }
}
