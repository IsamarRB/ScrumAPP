package com.scrumapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.scrumapp")
public class ScrumAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrumAppApplication.class, args);
	}

}
