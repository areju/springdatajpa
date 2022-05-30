package com.arjun.springpersistence.springdatajpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.arjun.springpersistence.springdatajpa.configuration.SpringDataConfiguration;
import com.arjun.springpersistence.springdatajpa.helper.DateHelper;
import com.arjun.springpersistence.springdatajpa.model.Item;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ItemTest {
	
	@Test
	void storeLoadTest() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jp.generator");
		EntityManager em =emf.createEntityManager();
		try {
			em.getTransaction().begin();
			
            Item item = new Item();
            item.setName("Some Item1");
            item.setAuctionEnd(DateHelper.tomorrow());
            
            em.persist(item);
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            
            List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
            
            em.getTransaction().commit();
            
            assertAll(
            		() -> assertEquals(1, items.size()),
            		() -> assertEquals("Some Item1", items.get(0).getName())
            		);
            
            
            
		}finally {
			emf.close();
			em.close();
		}
		
	}

}
