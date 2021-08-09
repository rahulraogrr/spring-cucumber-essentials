package com.spring.cucumber.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(
	value = Cucumber.class
)
@CucumberOptions(
		monochrome = true,
		features = {"src/test/resources/cucumber"},
		glue = {"com.spring.cucumber.cucumber"}
)
public class CucumberMainApp {
	
}
