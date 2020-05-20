package com.newLife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@SpringBootApplication
@Async
public class NewLifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewLifeApplication.class, args);
	}

}
