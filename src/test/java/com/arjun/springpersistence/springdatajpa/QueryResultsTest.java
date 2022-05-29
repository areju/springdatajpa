package com.arjun.springpersistence.springdatajpa;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import com.arjun.springpersistence.springdatajpa.model.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;



public class QueryResultsTest extends SpringdatajpaApplicationTests {
	@Test
	void testStreamables() {
		try( Stream<User> result = userRepo.findByEmailContaining("cde")
					.and(userRepo.findByLevel(5))
					.stream().distinct()) {
			assertEquals(1, result.count());
		};
	}
	
    @Test
    void testFindByAsArrayAndSort() {
        List<Object[]> usersList1 =
           userRepo.findByAsArrayAndSort("bc1", Sort.by("username"));
        List<Object[]> usersList2 =
           userRepo.findByAsArrayAndSort("bc1",
               Sort.by("email_length").descending());
        List<Object[]> usersList3 = userRepo.findByAsArrayAndSort(
               "bc10", JpaSort.unsafe("LENGTH(u.email)"));
        assertAll(
                () -> assertEquals(2, usersList1.size()),
                () -> assertEquals("abc1", usersList1.get(0)[0]),
                () -> assertEquals(18, usersList1.get(0)[1]),
                () -> assertEquals(2, usersList2.size()),
                () -> assertEquals("abc10", usersList2.get(0)[0]),
                () -> assertEquals(20, usersList2.get(0)[1]),
                () -> assertEquals(1, usersList3.size()),
                () -> assertEquals("abc10", usersList3.get(0)[0]),
                () -> assertEquals(20, usersList3.get(0)[1])
        );
    }


}
