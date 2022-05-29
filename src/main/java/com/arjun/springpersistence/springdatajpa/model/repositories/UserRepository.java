package com.arjun.springpersistence.springdatajpa.model.repositories;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.arjun.springpersistence.springdatajpa.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);

    List<User> findAllByOrderByUsernameAsc();

    List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);

    List<User> findByUsernameAndEmail(String username, String email);

    List<User> findByUsernameOrEmail(String username, String email);

    List<User> findByUsernameIgnoreCase(String username);

    List<User> findByLevelOrderByUsernameDesc(int level);

    List<User> findByLevelGreaterThanEqual(int level);

    List<User> findByUsernameContaining(String text);

    List<User> findByUsernameLike(String text);

    List<User> findByUsernameStartingWith(String start);

    List<User> findByUsernameEndingWith(String end);

    List<User> findByActive(boolean active);

    List<User> findByRegistrationDateIn(Collection<LocalDate> dates);

    List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);
    
    /*
     * Usage of first, top, pageable and sorting
     * in queries
     */
    
    User findFirstByOrderByUsernameAsc();
    User findTopByOrderByRegistrationDateDesc();
    Page<User> findAll(Pageable pageable);
    List<User> findFirst2ByLevel(int level, Sort sort);
    List<User> findByLevel(int level, Sort sort);
    List<User> findByActive(boolean active, Pageable pageable);




}
