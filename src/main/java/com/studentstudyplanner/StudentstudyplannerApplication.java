package com.studentstudyplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.studentstudyplanner.data.repository")
@EntityScan("com.studentstudyplanner.data.entity")
public class StudentstudyplannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentstudyplannerApplication.class, args);
	}

}
