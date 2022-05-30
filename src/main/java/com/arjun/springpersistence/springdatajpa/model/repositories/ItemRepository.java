package com.arjun.springpersistence.springdatajpa.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.arjun.springpersistence.springdatajpa.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long>
 {

}
