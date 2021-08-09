package com.spring.cucumber.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsersStepDefs extends SpringBootCucumberTest {
	
	@When("getAllUsers api is called with no records in DB")
    public void whenUserClicksOnSubmitButton() {
		log.info("Inside whenUserClicksOnSubmitButton"); 
    }

	@Then("api should return 404 error")
    public void theResponseShouldBeAbleToLoginAndResponseIs() {
		log.info("Inside theResponseShouldBeAbleToLoginAndResponseIs");
		throw new RuntimeException("Test Rahul Defs");
    }
}