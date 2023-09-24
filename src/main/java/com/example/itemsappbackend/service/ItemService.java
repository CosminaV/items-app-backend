package com.example.itemsappbackend.service;

import com.example.itemsappbackend.exception.ItemNotFoundException;
import com.example.itemsappbackend.model.Item;
import com.example.itemsappbackend.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService{

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Page<Item> getAllItemsPaginatedAndSorted(int offset, int pageSize, String field, String sortingMode) {
        Page<Item> page;
        if(sortingMode.equalsIgnoreCase("asc")) {
            page = itemRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));
        } else {
            page = itemRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC, field)));
        }
        return page;
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    public void deleteItemById(Long id) {
        itemRepository.findById(id)
                .ifPresentOrElse(itemRepository::delete,
                        () -> {throw new ItemNotFoundException(id);});
    }
}
