/**
 * 
 */
package com.arjun.springpersistence.springdatajpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author arjun
 *
 */
@Entity
@Table(name = "ITEM")
public class Item {
	
	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;
	
    @NotNull
    @Size(
            min = 2,
            max = 255,
            message = "Name is required, maximum 255 characters."
    )
	private String name;
	
	private Date auctionend;
	
	
    public Long getId() { // Optional but useful
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAuctionEnd() {
        return auctionend;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionend = auctionEnd;
    }
	

}
