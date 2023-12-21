package com.dudgns.backendauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class BackendAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAuthApplication.class, args);
	}

}
