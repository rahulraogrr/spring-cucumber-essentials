package com.spring.cucumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = 
@Info(
		title = "Users API", 
		version = "1.0", 
		description = "This API is used to maintain users data and user authentication for the system", 
		termsOfService = "https://github.com/rahulraogrr",
		contact = @Contact(name = "Rahul Rao Gonda", 
						   url = "https://github.com/rahulraogrr", 
						   email = "rahulrao.grr@gmail.com")
	  )
)
public class SpringCucumberEssentialsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCucumberEssentialsApplication.class, args);
	}

}