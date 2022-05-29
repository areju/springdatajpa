package com.arjun.springpersistence.springdatajpa;

import java.time.LocalDate;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arjun.springpersistence.springdatajpa.model.User;
import com.arjun.springpersistence.springdatajpa.model.repositories.UserRepository;

@SpringBootApplication
public class SpringdatajpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);
	}
	
	public ApplicationRunner configure(UserRepository userRepo) {
		return env -> {
			User abc = new User("AbcDef", LocalDate.of(10, 03, 2022));
			User def = new User("qwerty", LocalDate.of(05,06,2022));
			
			userRepo.save(abc);
			userRepo.save(def);
			
			userRepo.findAll().forEach(System.out::println);
		};
	}

}
