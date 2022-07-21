package com.itlab1024.swagger3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootSwagger3TutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSwagger3TutorialApplication.class, args);
	}

}
