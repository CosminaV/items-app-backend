package com.example.itemsappbackend.repository;

import com.example.itemsappbackend.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
