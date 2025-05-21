package com.biblioteca.Biblioteca.Repository;

import com.biblioteca.Biblioteca.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategory(String category);
}
