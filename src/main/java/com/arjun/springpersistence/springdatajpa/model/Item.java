/**
 * 
 */
package com.arjun.springpersistence.springdatajpa.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Formula;
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
	
	@Access(AccessType.PROPERTY)
    @NotNull
    @Size(
            min = 2,
            max = 255,
            message = "Name is required, maximum 255 characters."
    )
	private String name;
	
	private Date auctionend;
	
	private String description;
	
	@Formula(
				"CONCAT(SUBSTR(DESCRIPTION, 1, 12), '...')" 
			)
	private String shortDescription;
	
	@Formula(
			"(SELECT AVG(B.AMOUNT) FROM BID B WHERE  B.ITEM_ID = ID)"
			)
	private BigDecimal avgbidamount;
	
	@Column(name = "IMPERIALWEIGHT")
	@ColumnTransformer(
			read = "IMPERIALWEIGHT / 2.20462",
			write = "? * IMPERIALWEIGHT")
	private Double metricweight;
	
    @Transient
    private Set<Bid> bids = new HashSet<>();
    
    public Long getId() { // Optional but useful
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getShortDescription() {
        return shortDescription;
    }

    public Double getMetricweight() {
		return metricweight;
	}

	public void setMetricweight(Double metricweight) {
		this.metricweight = metricweight;
	}

	public Date getAuctionend() {
        return auctionend;
    }

    public void setAuctionend(Date auctionEnd) {
        this.auctionend = auctionEnd;
    }
    
    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }
	

}
