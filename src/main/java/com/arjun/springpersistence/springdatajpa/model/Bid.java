package com.arjun.springpersistence.springdatajpa.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;


@Entity
public class Bid {
	
	@Id
    @GeneratedValue(generator = "ID_GENERATOR")
	private Long id;
	
	@NonNull
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	
    public Bid() {
    }

    public Bid(BigDecimal amount, Item item) {
        this.amount = amount;
        this.item = item;
    }

    public Bid(Item item) {
        this.item = item;
        item.addBid(this); // Bidirectional
    }

    public Long getId() { // Optional but useful
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
	

}
