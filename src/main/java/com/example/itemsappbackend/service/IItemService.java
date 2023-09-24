package com.example.itemsappbackend.service;

import com.example.itemsappbackend.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IItemService {
    public List<Item> getAllItems();

    public Page<Item> getAllItemsPaginatedAndSorted(int offset, int size, String field, String sortingMode);

    public Item saveItem(Item item);
    public Item findById(Long id);
}
