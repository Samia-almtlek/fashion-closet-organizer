package com.fashioncloset.organizer.repositories;
import com.fashioncloset.organizer.models.ClothingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing ClothingItem entities in the database.
 * This interface extends JpaRepository to provide built-in CRUD and query methods.
 */
public interface ClothingItemRepository extends JpaRepository<ClothingItem, Long> {

}
