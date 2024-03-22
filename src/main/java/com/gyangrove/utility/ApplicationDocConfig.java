package com.gyangrove.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDocConfig {
	
	Contact contact() {
		return new Contact().name("Umar")
				.email("umar@gmail.com")
				.url("umar.in");
	}
	
	Info info() {
		return new Info().title("Event Management System")
				.version("v1")
				.description("Simple RESTful API for Event Management")
				.contact(contact());
	}

	@Bean
	OpenAPI openApi() {
		return new OpenAPI().info(info());
	}
}
