package com.example.itemsappbackend.controller;

import com.example.itemsappbackend.model.Item;
import com.example.itemsappbackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
//@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@PathVariable Long id) {
        Item item = itemService.findById(id);
        if(item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/items-paginated")
    public ResponseEntity<List<Item>> getAllItemsPaginatedAndSorted(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sort) {

        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        String sortMode = sortParams.length > 1 ? sortParams[1] : "asc"; // Default to "asc" if not specified.

        Page<Item> pageItem = itemService.getAllItemsPaginatedAndSorted(page, size, sortField, sortMode);
        return ResponseEntity.ok(pageItem.getContent());
    }

    @PostMapping
    public ResponseEntity<Void> createItem(@RequestBody Item newItem, UriComponentsBuilder ucb) {
        Item item = itemService.saveItem(newItem);
        URI locationOfNewItem = ucb
                .path("items/{id}")
                .buildAndExpand(item.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewItem).build();
    }


}
