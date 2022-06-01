package com.arjun.springpersistence.springdatajpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect(
		value = "select i.ID as itemid, i.NAME as name,  count(b.ID) as numberofbids from ITEM i left outer join BID b on i.ID = b.ITEM_ID group by i.ID, i.NAME"

			)
@org.hibernate.annotations.Synchronize({"ITEM", "BID"}) 
public class ItemBidSummary {
	
	@Id
    private Long itemid;

    private String name;

    private long numberofbids;

    public Long getItemid() {
        return itemid;
    }

    public String getName() {
        return name;
    }

    public long getNumberofbids() {
        return numberofbids;
    }

}
