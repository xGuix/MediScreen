package com.mediscreen.assessment;

import groovy.transform.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Assessment-app Main class
 */
@EnableSwagger2
@SpringBootApplication
@EnableFeignClients
public class AssessmentApplication {
	/**
	 * Run app with main class
	 * @param args String args
	 */
	@Generated
	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}
}