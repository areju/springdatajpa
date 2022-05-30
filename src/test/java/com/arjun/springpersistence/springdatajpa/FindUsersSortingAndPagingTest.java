package com.arjun.springpersistence.springdatajpa;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.arjun.springpersistence.springdatajpa.model.User;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FindUsersSortingAndPagingTest extends SpringdatajpaApplicationTests {

	@Test
	void testOrder() {
		User user1 = userRepo.findFirstByOrderByUsernameAsc();
		User user2 = userRepo.findTopByOrderByRegistrationdateDesc();
		List<User> users = userRepo.findFirst2ByLevel(2, Sort.by("Registrationdate"));
		
		Page<User> userPage = userRepo.findAll(PageRequest.of(1, 4));
		
		assertAll(
				() -> assertEquals("abc1", user1.getUsername()),
				() -> assertEquals("qwerty", user2.getUsername()),
				() -> assertEquals(1, users.size()),
				() -> assertEquals(4, userPage.getSize()),
				() -> assertEquals("abc7", users.get(0).getUsername())
				);
		
	}
	
	@Test
	void testFindByLevel() {
		
		Sort.TypedSort<User> user = Sort.sort(User.class);
		List<User> users = userRepo.findByLevel(3, user.by(User::getRegistrationdate).descending());
		
        assertAll(                                                          
                () -> assertEquals(2, users.size()),                        
                () -> assertEquals("abc3", users.get(0).getUsername())     
        );                                                                  

		
	}
	
	
    @Test
    void testFindByActive() {
        List<User> users = userRepo.findByActive(true,
                   PageRequest.of(1, 4, Sort.by("registrationdate")));
        assertAll(                                                        
                () -> assertEquals(4, users.size()),                       
                () -> assertEquals("abc6", users.get(0).getUsername())     
        );
    }
		

}
