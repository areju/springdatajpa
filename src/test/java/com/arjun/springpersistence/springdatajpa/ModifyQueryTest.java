package com.arjun.springpersistence.springdatajpa;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import com.arjun.springpersistence.springdatajpa.model.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;


public class ModifyQueryTest extends SpringdatajpaApplicationTests {
	
	@Test
	void testModifyLevel() {
	    int updated = userRepo.updateLevel(5, 4);
	    List<User> users = userRepo.findByLevel(4, Sort.by("username"));
	 
	    assertAll(
	            () -> assertEquals(1, updated),
	            () -> assertEquals(1, users.size()),
	            () -> assertEquals("abc4", users.get(0).getUsername())
	    );
	}
	
	@Test
	void testDeleteByLevel() {
	    int deleted = userRepo.deleteByLevel(2);
	    List<User> users = userRepo.findByLevel(2, Sort.by("username"));
	    assertEquals(0, users.size());
	}
	
	@Test
	void testDeleteBulkByLevel() {
	    int deleted = userRepo.deleteBulkByLevel(1);
	    List<User> users = userRepo.findByLevel(1, Sort.by("username"));
	    assertEquals(0, users.size());
	}



}
