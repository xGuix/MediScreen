package com.mediscreen.notes;

import groovy.transform.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class NotesApplication {
	@Generated
	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}
}