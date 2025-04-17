package com.project.projectapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ProjectapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectapiApplication.class, args);
	}

}
