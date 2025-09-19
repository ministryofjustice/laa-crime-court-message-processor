package uk.gov.justice.laa.crime.courtmessageprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Boot microservice application.
 */
@SpringBootApplication
public class CourtMessageProcessorApplication {

  /**
   * The application main method.
   *
   * @param args the application arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(CourtMessageProcessorApplication.class, args);
  }
}
