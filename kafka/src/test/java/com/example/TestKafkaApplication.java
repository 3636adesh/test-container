package com.example;

import org.springframework.boot.SpringApplication;

public class TestKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.from(KafkaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
