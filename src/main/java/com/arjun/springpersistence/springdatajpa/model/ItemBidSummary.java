package com.arjun.springpersistence.springdatajpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect(
		value = "select i.ID as ITEMID, i.NAME as NAME," +
				"count(b.ID) as NUMBEROFBIDS" +
				"from ITEM i left outer join BID b on i.ID = b.ID" +
				"group by i.ID i.NAME"
			)
@org.hibernate.annotations.Synchronize({"ITEM", "BID"})
public class ItemBidSummary {
	
	@Id
    private Long itemId;

    private String name;

    private long numberOfBids;

    public Long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public long getNumberOfBids() {
        return numberOfBids;
    }


}
