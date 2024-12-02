package com.fashioncloset.organizer.controllers;
import com.fashioncloset.organizer.models.ClothingItem;
import com.fashioncloset.organizer.repositories.ClothingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController // Marks this class as a REST controller to handle HTTP requests and send responses in JSON format

@RequestMapping("/api/clothing") // Defines the base URL path for all endpoints in this controller
public class ClothingController {
    // Automatically injects an instance of ClothingItemRepository for database interactions
    @Autowired
    private ClothingItemRepository repository;

    @PostMapping
    public ClothingItem addItem(@RequestBody ClothingItem item) {
        return repository.save(item);
    }

    @GetMapping
    public List<ClothingItem> getAllItems() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}


