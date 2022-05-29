package com.arjun.springpersistence.springdatajpa;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.arjun.springpersistence.springdatajpa.model.Projection;
import com.arjun.springpersistence.springdatajpa.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ProjectionTest extends SpringdatajpaApplicationTests {
	
	@Test
    void testProjectionUsername() {
 
        List<Projection.UsernameOnly> users =
            userRepo.findByEmail("abc1@abcdomain.com");
 
        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("abc1", users.get(0).getUsername())
        );
        
        
    }
	
    @Test
    void testProjectionUserSummary() {
        List<Projection.UserSummary> users =
            userRepo.findByRegistrationDateAfter(LocalDate.of(2021, Month.FEBRUARY, 1));
 
        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("abc4", users.get(0).getUsername()),
                () -> assertEquals("abc4 abc4@abcdomain.com",
                                   users.get(0).getInfo())
        );
    }
    
    @Test
    void testDynamicProjection() {
        List<Projection.UsernameOnly> usernames =
                userRepo.findByEmail("abc1@abcdomain.com",
                Projection.UsernameOnly.class);
        List<User> users = userRepo.findByEmail("abc1@abcdomain.com",
                User.class);
 
        assertAll(
                () -> assertEquals(1, usernames.size()),
                () -> assertEquals("abc1", usernames.get(0).getUsername()),
                () -> assertEquals(1, users.size()),
                () -> assertEquals("abc1", users.get(0).getUsername())
        );
    }



}
