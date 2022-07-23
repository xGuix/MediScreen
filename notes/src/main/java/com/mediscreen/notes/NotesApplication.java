package com.mediscreen.notes;

import groovy.transform.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Notes-app Main class
 */@SpringBootApplication
@EnableSwagger2
public class NotesApplication {
	/**
	 * Run app with main class
	 * @param args String args
	 */
	@Generated
	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}
}