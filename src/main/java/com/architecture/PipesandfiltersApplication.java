package com.architecture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.*;
@SpringBootApplication
public class PipesandfiltersApplication {


	
	@Value("${com.architecture.origins}")
	private  String[] ALLOWED_ORIGINS;

	public static void main(String[] args) {
		SpringApplication.run(PipesandfiltersApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {

		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				registry.addMapping("/api/**").allowedOrigins(ALLOWED_ORIGINS);
			}
		};
	}
}
