package com.investmentmvcapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringInvestmentRestapiRestmvcclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringInvestmentRestapiRestmvcclientApplication.class, args);

	}

	// create a bean definition of RestTemplate
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
