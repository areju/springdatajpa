package com.arjun.springpersistence.springdatajpa.model.repositories;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import com.arjun.springpersistence.springdatajpa.model.User;
import com.arjun.springpersistence.springdatajpa.model.Projection;

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
    
    /*
     * streamables
     */
    
    Streamable<User> findByEmailContaining(String text);
    Streamable<User> findByLevel(int level);
    
    /*
     * Custom Queries
     */
    
    @Query("select count(u) from User u where u.active = ?1")
    int findNumberOfUsersByActivity(boolean active);
     
    @Query("select u from User u where u.level = :level and u.active = :active")
    List<User> findByLevelAndActive(@Param("level") int level,
                                   @Param("active") boolean active);
     
    @Query(value = "SELECT COUNT(*) FROM USERS WHERE ACTIVE = ?1",
                    nativeQuery = true)
    int findNumberOfUsersByActivityNative(boolean active);
     
    @Query("select u.username, LENGTH(u.email) as email_length from #{#entityName} u where u.username like %?1%")
    List<Object[]> findByAsArrayAndSort(String text, Sort sort);
    
    
    /*
     * Projection interface based and class based, and also based on spel
     */
    
    List<Projection.UserSummary> findByRegistrationDateAfter(LocalDate date);

    List<Projection.UsernameOnly> findByEmail(String username);

    <T> List<T> findByEmail(String username, Class<T> type);




}
