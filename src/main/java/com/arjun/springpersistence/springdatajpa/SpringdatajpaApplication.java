package com.arjun.springpersistence.springdatajpa;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arjun.springpersistence.springdatajpa.model.User;
import com.arjun.springpersistence.springdatajpa.model.repositories.UserRepository;

@SpringBootApplication
public class SpringdatajpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);
	}
	
	@Bean
	public ApplicationRunner configure(UserRepository userRepo) {
		return env -> {
			User abc = new User("AbcDef", LocalDate.of(2021,Month.JANUARY,14));
			User def = new User("qwerty", LocalDate.of(2021,Month.JUNE,	10));
			
			userRepo.save(abc);
			userRepo.save(def);
			
			userRepo.findAll().forEach(System.out::println);
		};
	}

}
