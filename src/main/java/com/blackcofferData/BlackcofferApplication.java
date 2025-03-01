package com.blackcofferData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.blackcofferData")
public class BlackcofferApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackcofferApplication.class, args);
	}

}
