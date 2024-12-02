package com.fashioncloset.organizer.controllers;

import com.fashioncloset.organizer.models.ClothingItem;
import com.fashioncloset.organizer.repositories.ClothingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/clothing")
public class ClothingController {

    @Autowired
    private ClothingItemRepository repository;

    // Add new item clothes
    @PostMapping("/add")
    public ClothingItem addItem(@RequestParam("category") String category,
                                @RequestParam("color") String color,
                                @RequestParam("season") String season,
                                @RequestParam("image") MultipartFile image) throws IOException {
        // Save the image in a folder and store the path.
        String imagePath = saveImage(image);

        //ClothingItem create an object
        ClothingItem item = new ClothingItem();
        item.setCategory(category);
        item.setColor(color);
        item.setSeason(season);
        item.setImagePath(imagePath);  // save photo path

        // Save the object in the database
        return repository.save(item);
    }

    //Retrieve all items
    @GetMapping("/")
    public List<ClothingItem> getAllItems() {
        return repository.findAll();
    }

    // Delete an item from the database
    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Save the image in the folder
    private String saveImage(MultipartFile image) throws IOException {
        String folderPath = "src/main/resources/static/images/";
        Path filePath = Paths.get(folderPath + image.getOriginalFilename());
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return filePath.toString();
    }
}
