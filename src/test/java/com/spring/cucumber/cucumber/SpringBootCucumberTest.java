package com.spring.cucumber.cucumber;

import org.springframework.boot.test.context.SpringBootTest;

import com.spring.cucumber.SpringCucumberEssentialsApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(
        classes = SpringCucumberEssentialsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class SpringBootCucumberTest {
	private static final String BASE_URL = "";
	
	
}
