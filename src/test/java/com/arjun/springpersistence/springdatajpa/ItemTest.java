package com.arjun.springpersistence.springdatajpa;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.arjun.springpersistence.springdatajpa.configuration.SpringDataConfiguration;
import com.arjun.springpersistence.springdatajpa.helper.DateHelper;
import com.arjun.springpersistence.springdatajpa.model.Bid;
import com.arjun.springpersistence.springdatajpa.model.Item;
import com.arjun.springpersistence.springdatajpa.model.ItemBidSummary;

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
            item.setAuctionend(DateHelper.tomorrow());
            
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
	
	@Test
	void storeLoadBidItemTest() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jp.generator");
		EntityManager em =emf.createEntityManager();
		try {
			em.getTransaction().begin();
			
            Item item = new Item();
            item.setName("Some Item1");
            item.setAuctionend(DateHelper.tomorrow());
            
            em.persist(item);
            Bid bid1 = new Bid(new BigDecimal(1000.0), item);
            Bid bid2 = new Bid(new BigDecimal(1100.0), item);
            
            em.persist(bid1);
            em.persist(bid2);
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            
            List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
            
            em.getTransaction().commit();
            
            assertAll(
            		() -> assertEquals(1, items.size()),
            		() -> assertEquals("Some Item1", items.get(0).getName()),
            		() -> assertEquals(2,items.get(0).getBids().size())
            		);
            
            em.getTransaction().begin();

            TypedQuery<ItemBidSummary> query =
                    em.createQuery("select ibs from ItemBidSummary ibs where ibs.itemid = :id", ItemBidSummary.class);
            
            ItemBidSummary itemBidSummary =
                    query.setParameter("id", 1000L).getSingleResult();

            assertAll(
                    () -> assertEquals(1000, itemBidSummary.getItemid()),
                    () -> assertEquals("Some Item1", itemBidSummary.getName()),
                    () -> assertEquals(2, itemBidSummary.getNumberofbids())
            );

            em.getTransaction().commit();
            
            
            
		}finally {
			emf.close();
			em.close();
		}
		
	}

}
