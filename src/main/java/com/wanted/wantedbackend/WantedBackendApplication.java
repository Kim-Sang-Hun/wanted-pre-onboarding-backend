package com.wanted.wantedbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WantedBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(WantedBackendApplication.class, args);
  }

}