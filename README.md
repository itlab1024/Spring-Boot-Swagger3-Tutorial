> spring-boot 整合 swagger3

# 版本说明

Spring Boot： 2.7.1

JDK: 17

Swgger: 3

# 官网

http://springfox.github.io/

# 最小化使用

## 引入Swagger依赖

创建项目并引入Swagger的Spring Boot依赖，完成pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.1</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.itlab1024</groupId>
	<artifactId>spring-boot-swagger3-tutorial</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring-boot-swagger3-tutorial</name>
	<description>spring-boot-swagger3-tutorial</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-boot-starter</artifactId>
			<version>3.0.0</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

## 配置启动文件

swagger使用ant_pattern_parser解析路径，但是spring boot在2.6之后（好现实2.6），修改为了path_pattern_parser。所以为了能使swagger正常使用，需要配置如下。

application.yaml

```yaml
spring:
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
```

如果不修改会报如下错误

```shell
2022-07-21 12:33:37.658 ERROR 38950 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.context.ApplicationContextException: Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException: Cannot invoke "org.springframework.web.servlet.mvc.condition.PatternsRequestCondition.getPatterns()" because "this.condition" is null
	at org.springframework.context.support.DefaultLifecycleProcessor.doStart(DefaultLifecycleProcessor.java:181) ~[spring-context-5.3.13.jar:5.3.13]
	at org.springframework.context.support.DefaultLifecycleProcessor.access$200(DefaultLifecycleProcessor.java:54) ~[spring-context-5.3.13.jar:5.3.13]
	at org.springframework.context.support.DefaultLifecycleProcessor$LifecycleGroup.start(DefaultLifecycleProcessor.java:356) ~[spring-context-5.3.13.jar:5.3.13]
	at java.base/java.lang.Iterable.forEach(Iterable.java:75) ~[na:na]
	at org.springframework.context.support.DefaultLifecycleProcessor.startBeans(DefaultLifecycleProcessor.java:155) ~[spring-context-5.3.13.jar:5.3.13]
	at org.springframework.context.support.DefaultLifecycleProcessor.onRefresh(DefaultLifecycleProcessor.java:123) ~[spring-context-5.3.13.jar:5.3.13]
	at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:935) ~[spring-context-5.3.13.jar:5.3.13]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:586) ~[spring-context-5.3.13.jar:5.3.13]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:145) ~[spring-boot-2.6.1.jar:2.6.1]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:730) ~[spring-boot-2.6.1.jar:2.6.1]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:412) ~[spring-boot-2.6.1.jar:2.6.1]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:302) ~[spring-boot-2.6.1.jar:2.6.1]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1301) ~[spring-boot-2.6.1.jar:2.6.1]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1290) ~[spring-boot-2.6.1.jar:2.6.1]
	at com.itlab1024.swagger3.SpringBootSwagger3TutorialApplication.main(SpringBootSwagger3TutorialApplication.java:13) ~[classes/:na]
Caused by: java.lang.NullPointerException: Cannot invoke "org.springframework.web.servlet.mvc.condition.PatternsRequestCondition.getPatterns()" because "this.condition" is null
	at springfox.documentation.spring.web.WebMvcPatternsRequestConditionWrapper.getPatterns(WebMvcPatternsRequestConditionWrapper.java:56) ~[springfox-spring-webmvc-3.0.0.jar:3.0.0]
	at springfox.documentation.RequestHandler.sortedPaths(RequestHandler.java:113) ~[springfox-core-3.0.0.jar:3.0.0]
	at springfox.documentation.spi.service.contexts.Orderings.lambda$byPatternsCondition$3(Orderings.java:89) ~[springfox-spi-3.0.0.jar:3.0.0]
	at java.base/java.util.Comparator.lambda$comparing$77a9974f$1(Comparator.java:473) ~[na:na]
	at java.base/java.util.TimSort.countRunAndMakeAscending(TimSort.java:355) ~[na:na]
	at java.base/java.util.TimSort.sort(TimSort.java:220) ~[na:na]
	at java.base/java.util.Arrays.sort(Arrays.java:1307) ~[na:na]
	at java.base/java.util.ArrayList.sort(ArrayList.java:1721) ~[na:na]
	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:392) ~[na:na]
	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258) ~[na:na]
	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258) ~[na:na]
	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258) ~[na:na]
	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499) ~[na:na]
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234) ~[na:na]
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682) ~[na:na]
	at springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider.requestHandlers(WebMvcRequestHandlerProvider.java:81) ~[springfox-spring-webmvc-3.0.0.jar:3.0.0]
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197) ~[na:na]
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499) ~[na:na]
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921) ~[na:na]
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234) ~[na:na]
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682) ~[na:na]
	at springfox.documentation.spring.web.plugins.AbstractDocumentationPluginsBootstrapper.withDefaults(AbstractDocumentationPluginsBootstrapper.java:107) ~[springfox-spring-web-3.0.0.jar:3.0.0]
	at springfox.documentation.spring.web.plugins.AbstractDocumentationPluginsBootstrapper.buildContext(AbstractDocumentationPluginsBootstrapper.java:91) ~[springfox-spring-web-3.0.0.jar:3.0.0]
	at springfox.documentation.spring.web.plugins.AbstractDocumentationPluginsBootstrapper.bootstrapDocumentationPlugins(AbstractDocumentationPluginsBootstrapper.java:82) ~[springfox-spring-web-3.0.0.jar:3.0.0]
	at springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper.start(DocumentationPluginsBootstrapper.java:100) ~[springfox-spring-web-3.0.0.jar:3.0.0]
	at org.springframework.context.support.DefaultLifecycleProcessor.doStart(DefaultLifecycleProcessor.java:178) ~[spring-context-5.3.13.jar:5.3.13]
	... 14 common frames omitted
```

## 创建接口相关类

```java
package com.itlab1024.swagger3.controller;

import com.itlab1024.swagger3.vo.TestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/api")
public class SwaggerTestController {

    @ApiOperation(value = "通过ID获取")
    @GetMapping("/{id}")
    public TestVo get(@PathVariable String id){
        return new TestVo("name1");
    }
}

```

参数返回类

```java
package com.itlab1024.swagger3.vo;

public class TestVo {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestVo() {
    }

    public TestVo(String name) {
        this.name = name;
    }
}
```

## 访问Swagger页面

访问http://localhost:8080/wagger-ui/index.html

![image-20220721123706210](https://raw.githubusercontent.com/itlab1024/picgo-images/main/202207211237426.png)

上面知识最小化使用，接下来我尝试配置下swagger。

# 配置

## base_url

```yaml
springfox:
  documentation:
    swagger-ui:
      enabled: true
      base-url: /documentation
```

base_url用于配置swagger-ui的访问路径，比如上面的配置，会将请求路径变为http://localhost:8080/documentation/wagger-ui/index.html

![image-20220721124634073](https://raw.githubusercontent.com/itlab1024/picgo-images/main/202207211246153.png)

## 其他配置

其他配置（比如标题，描述的等内容）目前无法在yaml配置文件中配置。需要使用JavaConfig的方式配置。

```java
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

```

重新访问swagger，http://localhost:8080/documentation/swagger-ui/index.html

![image-20220721131214403](https://raw.githubusercontent.com/itlab1024/picgo-images/main/202207211312483.png)

可以看到已经修改完成。

# 使用swagger

在页面中可以看到如下接口

![image-20220721131534139](https://raw.githubusercontent.com/itlab1024/picgo-images/main/202207211315250.png)

展开后，点击`Try it out`,输入ID

![image-20220721131638652](https://raw.githubusercontent.com/itlab1024/picgo-images/main/202207211316732.png)

点击执行，正常返回如下接口

![image-20220721131708348](https://raw.githubusercontent.com/itlab1024/picgo-images/main/202207211317409.png)

---

> 个人主页：https://itlab1024.com
