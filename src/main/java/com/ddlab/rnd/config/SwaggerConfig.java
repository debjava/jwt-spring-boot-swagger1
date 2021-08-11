package com.ddlab.rnd.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.select()//
				.apis(RequestHandlerSelectors.any())//
				.paths(Predicates.not(PathSelectors.regex("/error")))//
				.build()//
				.apiInfo(metadata())//
				.useDefaultResponseMessages(false)//
				.securitySchemes(Collections.singletonList(apiKey()))
				.securityContexts(Collections.singletonList(securityContext()));
//        .tags(new Tag("users", "Operations about users"))//
//        .genericModelSubstitutes(Optional.class);

	}

	private ApiInfo metadata() {
		String description = "This is a sample JWT authentication service."
				+ "For this sample, you can use the `admin/admin` or `user/user` to sighup "
				+ "first to test the authorization filters. After signup, login with the same credentials and get the token,"
				+ "click on the right top button `Authorize` and introduce it with the prefix `Bearer `."
				+ "Make sure to enter a blank space after Bearer"
				+ "You can also login with `deba` as username and `Deba@1234` as password";
		return new ApiInfoBuilder()//
				.title("JSON Web Token Authentication API")//
				.description(description)//
				.version("1.0.0")//
				.license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")//
				.contact(new Contact(null, null, "deba.java@gmail.com"))//
				.build();
	}

	private ApiKey apiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
	}

}
