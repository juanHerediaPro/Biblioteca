package com.biblioteca.Biblioteca.ServiceInterfaces;

import com.biblioteca.Biblioteca.Entities.Category;
import org.springframework.http.ResponseEntity;

public interface ICategoryInterface {

    public ResponseEntity<Object> createCategory(Category category);
    public ResponseEntity<Object> getAllCategories();
    public ResponseEntity<Object> getCategoryByName(String category);
    public ResponseEntity<Object> deleteCategory(String category);

}
