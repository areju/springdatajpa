package com.arjun.springpersistence.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arjun.springpersistence.springdatajpa.helper.DateHelper;
import com.arjun.springpersistence.springdatajpa.model.Bid;
import com.arjun.springpersistence.springdatajpa.model.Item;
import com.arjun.springpersistence.springdatajpa.model.ItemBidSummary;
import com.arjun.springpersistence.springdatajpa.model.User;
import com.arjun.springpersistence.springdatajpa.model.repositories.BidRepository;
import com.arjun.springpersistence.springdatajpa.model.repositories.ItemBidSummaryRepository;
import com.arjun.springpersistence.springdatajpa.model.repositories.ItemRepository;
import com.arjun.springpersistence.springdatajpa.model.repositories.UserRepository;


@SpringBootTest
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
class SpringdatajpaApplicationTests {
	@Autowired
	UserRepository userRepo;
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	BidRepository bidRepo;
	@Autowired
	ItemBidSummaryRepository itemBidSummaryRepository;
	
	@BeforeAll
	void beforeAll() {
		userRepo.saveAll(generateUsers());
	}

	@Test
	void contextLoads() {
		
	}
	
    private List<User> generateUsers() {
        List<User> users = new ArrayList<>();

        User abc1 = new User("abc1", LocalDate.of(2020, Month.JANUARY, 13));
        abc1.setEmail("abc1@abcdomain.com");
        abc1.setLevel(1);
        abc1.setActive(true);

        User abc2 = new User("abc2", LocalDate.of(2020, Month.FEBRUARY, 13));
        abc2.setEmail("abc2@abcdomain.com");
        abc2.setLevel(1);
        abc2.setActive(true);

        User abc3 = new User("abc3", LocalDate.of(2020, Month.MARCH, 13));
        abc3.setEmail("abc3@abcdomain.com");
        abc3.setLevel(3);
        abc3.setActive(true);
        
        User abc4 = new User("abc4", LocalDate.of(2021, Month.APRIL, 5));
        abc4.setEmail("abc4@abcdomain.com");
        abc4.setLevel(5);
        abc4.setActive(true);

        User abc5 = new User("abc5", LocalDate.of(2020, Month.MARCH, 13));
        abc5.setEmail("abc5@abcdomain.com");
        abc5.setLevel(3);
        abc5.setActive(true);


        User abc6 = new User("abc6", LocalDate.of(2020, Month.MARCH, 13));
        abc6.setEmail("abc6@abcdomain.com");
        abc6.setLevel(1);
        abc6.setActive(true);

        
        User abc7 = new User("abc7", LocalDate.of(2020, Month.SEPTEMBER, 23));
        abc7.setEmail("abc7@someotherdomain.com");
        abc7.setLevel(2);
        abc7.setActive(false);

        
        User abc8 = new User("abc8", LocalDate.of(2020, Month.NOVEMBER, 28));
        abc8.setEmail("abc8@somedomain.com");
        abc8.setLevel(1);
        abc8.setActive(true);
        
        User abc9 = new User("abc9", LocalDate.of(2020, Month.NOVEMBER, 28));
        abc9.setEmail("abc9@somedomain.com");
        abc9.setLevel(1);
        abc9.setActive(true);
        
        User abc10 = new User("abc10", LocalDate.of(2020, Month.NOVEMBER, 28));
        abc10.setEmail("abc10@somedomain.com");
        abc10.setLevel(1);
        abc10.setActive(true);


        users.add(abc1);
        users.add(abc2);
        users.add(abc3);
        users.add(abc4);
        users.add(abc5);
        users.add(abc6);
        users.add(abc7);
        users.add(abc8);
        users.add(abc9);
        users.add(abc10);

        return users;
    }
    /*
    @Test
    void storeLoadItem() {

        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionend(DateHelper.tomorrow());

        itemRepo.save(item);

        List<Item> items = (List<Item>) itemRepo.findAll();

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals("Some Item", items.get(0).getName())
        );

    }*/
    
    
    @Test
    public void itemBidSummaryTest() {

        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionend(DateHelper.tomorrow());

        Bid bid1 = new Bid(new BigDecimal(1000.0), item);
        Bid bid2 = new Bid(new BigDecimal(1100.0), item);

        Item t_item = itemRepo.save(item);
        bidRepo.save(bid1);
        bidRepo.save(bid2);
        Long t_id = t_item.getId();
        Optional<ItemBidSummary> itemBidSummary = itemBidSummaryRepository.findById(t_id);

        assertAll(
        		
                () -> assertEquals(t_id, t_item.getId()),
                () -> assertEquals(t_item.getName(), itemBidSummary.get().getName()),
                () -> assertEquals(t_item.getBids().size(), itemBidSummary.get().getNumberofbids())
        );

    }

    
    @AfterAll
    void afterAll() {
    	
        userRepo.deleteAll();
        
    }


}
