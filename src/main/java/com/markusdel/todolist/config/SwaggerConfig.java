package com.markusdel.todolist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.api.version}")
    private String versaoAplicacao;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.markusdel.todolist")).paths(PathSelectors.any()).build()
                .apiInfo(apiInfo()).useDefaultResponseMessages(false)
                .securitySchemes(Arrays.asList(apiKey()));

    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("ToDo List")
                .description("criar um ToDo list com autenticação")
                .version(versaoAplicacao).build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

}