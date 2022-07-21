package com.itlab1024.swagger3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;

@Configuration
public class Swagger3Config {
    @Bean
    public Docket petApi() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("Spring Boot swagger3 整合")
                .contact(new Contact("IT实验室", "https://itlab1024.com", "itlab1024@163.com"))
                .description("Spring Boot 版本2.7、Swagger3 整合")
                .license("Apache 2.0")
                .licenseUrl("https://raw.githubusercontent.com/itlab1024/spring-boot-swagger3-tutorial/main/LICENSE")
                .version("1.0")
                .termsOfServiceUrl("服务条款URL")
                .build();
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(true);
    }
}
