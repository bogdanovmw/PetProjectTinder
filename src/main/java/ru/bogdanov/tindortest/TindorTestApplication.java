package ru.bogdanov.tindortest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.bogdanov.tindortest")
public class TindorTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TindorTestApplication.class, args);
	}

}
