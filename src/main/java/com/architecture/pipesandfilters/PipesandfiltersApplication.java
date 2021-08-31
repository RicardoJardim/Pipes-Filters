package com.architecture.pipesandfilters;

import org.springframework.boot.SpringApplication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PipesandfiltersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PipesandfiltersApplication.class, args);
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}
