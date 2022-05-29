package com.arjun.springpersistence.springdatajpa;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.arjun.springpersistence.springdatajpa.model.User;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class QueryResultsTest extends SpringdatajpaApplicationTests {
	@Test
	void testStreamables() {
		try( Stream<User> result = userRepo.findByEmailContaining("cde")
					.and(userRepo.findByLevel(5))
					.stream().distinct()) {
			assertEquals(1, result.count());
		};
	}

}
