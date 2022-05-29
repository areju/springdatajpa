package com.arjun.springpersistence.springdatajpa;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.arjun.springpersistence.springdatajpa.model.User;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FindUsersUsingQueriesTest extends SpringdatajpaApplicationTests {
    @Test
    void testFindAll() {
        List<User> users = userRepo.findAll();
        assertEquals(10, users.size());
    }
 
    @Test
    void testFindUser() {
        User abc1 = userRepo.findByUsername("abc1");
        assertEquals("abc1", abc1.getUsername());
    }
 
    @Test
    void testFindAllByOrderByUsernameAsc() {
        List<User> users = userRepo.findAllByOrderByUsernameAsc();
        assertAll(() -> assertEquals(10, users.size()),
                () -> assertEquals("abc1", users.get(0).getUsername()),
                () -> assertEquals("abc9", 
                       users.get(users.size() - 1).getUsername()));
    }
 
    @Test
    void testFindByRegistrationDateBetween() {
        List<User> users = userRepo.findByRegistrationDateBetween(
                LocalDate.of(2020, Month.JULY, 1),
                LocalDate.of(2020, Month.DECEMBER, 31));
        assertEquals(4, users.size());
    }


}
