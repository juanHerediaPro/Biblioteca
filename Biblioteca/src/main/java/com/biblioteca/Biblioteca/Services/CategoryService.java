package com.biblioteca.Biblioteca.Services;

import com.biblioteca.Biblioteca.Entities.Category;
import com.biblioteca.Biblioteca.Repository.ICategoryRepository;
import com.biblioteca.Biblioteca.ServiceInterfaces.ICategoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryInterface {

    HashMap<String,Object> response = new HashMap<>();

    public final ICategoryRepository iCategoryRepository;

    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    @Override
    public ResponseEntity<Object> createCategory(Category category){
        response.clear();
        if (iCategoryRepository.findByCategory(category.getCategory()).isPresent()){
            response.put("The category already exists", null);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }
        Category category1 = iCategoryRepository.save(category);
        response.put("Saved",category1);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getAllCategories(){
        List<Category> categories = iCategoryRepository.findAll();

        if (categories.isEmpty()) {
            return new ResponseEntity<>("There are no categories registered", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categories, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getCategoryByName(String category){
        Optional<Category> category1 = iCategoryRepository.findByCategory(category);

        if (category1.isEmpty()) {
            return new ResponseEntity<>("There is no category registered with this name", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(category1, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> deleteCategory(String category){
        Optional<Category> category1 = iCategoryRepository.findByCategory(category);

        if (category1.isEmpty()) {
            return new ResponseEntity<>("There is no category registered with this name", HttpStatus.NOT_FOUND);
        } else {
            iCategoryRepository.delete(category1.get());
            response.put("Category deleted successfully", category1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


}
