package com.preety.rest.webservices.springbootwebservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	// Bean - Docket
	// Swagger 2
	// All Paths
	// All Apis

	private static final Contact contact = new Contact("Preety", "Edusky.com", "preetykumari983@gmail.com");
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("New Api Documentation", "1.0",
			"A New Api Documentation", "urn:tos", contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	private static Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<String>(Arrays.asList("application/json", "application/xml"));
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_CONSUMES)
				.consumes(DEFAULT_PRODUCES_CONSUMES);
	}

}
