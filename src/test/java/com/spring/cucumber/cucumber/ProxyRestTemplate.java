package com.spring.cucumber.cucumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.cucumber.SpringCucumberEssentialsApplication;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Rest Template Abstraction For Cucumber
 */
@CucumberContextConfiguration
@SpringBootTest(
        classes = SpringCucumberEssentialsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class ProxyRestTemplate {

	private RestTemplate restTemplate;

	@Autowired
	public final void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public static final String BASE_URL = "";

	/**
	 *
	 * @return
	 */
	public String postRequest(){
		return null;
	}

	/**
	 *
	 * @param params
	 * @return
	 */
	public String getRequest(List<String> params){
		return null;
	}

	/**
	 *
	 * @param params
	 * @param requestBody
	 * @return
	 */
	public String put(List<String> params, String requestBody){
		return null;
	}

	/**
	 *
	 * @param url
	 * @param urlVariables
	 * @return
	 * @throws RestClientException
	 */
	public String delete(String url, Object... urlVariables) throws RestClientException {
		return null;
	}
}