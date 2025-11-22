package com.Mofit.MofitBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MofitBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MofitBackendApplication.class, args);
	}

}
