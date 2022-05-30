package com.arjun.springpersistence.springdatajpa;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.arjun.springpersistence.springdatajpa.model.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QueryByExampleTest extends SpringdatajpaApplicationTests {
	
    @Test
    void testEmailWithQueryByExample() {
        User user = new User();
        user.setEmail("@abcdomain.com");
 
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level", "active")
                .withMatcher("email", match -> match.endsWith());
 
        Example<User> example = Example.of(user, matcher);
 
        List<User> users = userRepo.findAll(example);
 
        assertEquals(6, users.size());
 
    }
    
    @Test
    void testUsernameWithQueryByExample() {
        User user = new User();
        user.setUsername("abc");
 
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level", "active")
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
                .withIgnoreCase();
 
        Example<User> example = Example.of(user, matcher);
 
        List<User> users = userRepo.findAll(example);
 
        assertEquals(10, users.size());
    }



}
