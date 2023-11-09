package br.com.almada.people;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@SpringBootApplication
@EnableAsync
public class PeopleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PeopleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("-------APLICAÇÃO NO AR-------------");
	}
}
