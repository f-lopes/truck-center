package com.ingesup.truck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lopes_f on 3/24/2015.
 * <florian.lopes@outlook.com>
 */
@Configuration
public class ActivitiConfig {

	@Bean
	public RestTemplate activitiRestTemplate() {
		ClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();

		RestTemplate restTemplate = new RestTemplate();
		return null;
	}

}
