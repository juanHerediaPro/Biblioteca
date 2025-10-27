package com.biblioteca.Biblioteca.Controllers;

import com.biblioteca.Biblioteca.Entities.AuthorsNationality;
import com.biblioteca.Biblioteca.Entities.Category;
import com.biblioteca.Biblioteca.ServiceInterfaces.ICategoryInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final ICategoryInterface iCategoryInterface;

    public CategoryController(ICategoryInterface iCategoryInterface) {
        this.iCategoryInterface = iCategoryInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCategory(@RequestBody Category category){
        return iCategoryInterface.createCategory(category);
    }
    @GetMapping("/all")
    public ResponseEntity<Object> getAllCategories(){
        return iCategoryInterface.getAllCategories();
    }
    @GetMapping("/search")
    public ResponseEntity<Object> getCategoryByName(@RequestParam String category) {
        return iCategoryInterface.getCategoryByName(category);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCategoryByName(@RequestParam String category) {
        return iCategoryInterface.deleteCategory(category);
    }
}
