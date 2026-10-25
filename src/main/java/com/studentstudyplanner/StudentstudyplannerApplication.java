
package com.studentstudyplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main entry point for the Student Study Planner application.
 *
 * This class starts the Spring Boot application and configures
 * Spring to recognize the application's database repositories
 * and entity classes.
 *
 * The application uses Spring Boot to manage its components,
 * database connections, and web application functionality.
 */
@SpringBootApplication
@EnableJpaRepositories("com.studentstudyplanner.data.repository")
@EntityScan("com.studentstudyplanner.data.entity")
public class StudentstudyplannerApplication {

    /**
     * Starts the Student Study Planner Spring Boot application.
     *
     * This method uses SpringApplication.run() to initialize
     * the application and start the Spring Boot environment.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(StudentstudyplannerApplication.class, args);
    }

}

