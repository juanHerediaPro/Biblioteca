package com.biblioteca.Biblioteca.Controllers;

import com.biblioteca.Biblioteca.Entities.Author;
import com.biblioteca.Biblioteca.ServiceInterfaces.IAuthorInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final IAuthorInterface iAuthorInterface;

    public AuthorController(IAuthorInterface iAuthorInterface) {
        this.iAuthorInterface = iAuthorInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createAhutor(@RequestBody Author author){
        return iAuthorInterface.createAuthor(author);
    }
    @GetMapping("/all")
    public  ResponseEntity<Object> getAllAuthors(){
        return iAuthorInterface.getAllAuthors();
    }
    @GetMapping("/search")
    public ResponseEntity<Object> getAuthorByLastName(@RequestParam String lastName){
        return iAuthorInterface.getAuthorByLastName(lastName);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteAuthorByLastName(@RequestParam String lastName){
        return iAuthorInterface.deleteAuthorByLastName(lastName);
    }
}
