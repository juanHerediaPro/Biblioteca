package com.biblioteca.Biblioteca.Controllers;

import com.biblioteca.Biblioteca.Dtos.BookDto;
import com.biblioteca.Biblioteca.Entities.Book;
import com.biblioteca.Biblioteca.Entities.Category;
import com.biblioteca.Biblioteca.ServiceInterfaces.IBookInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    private final IBookInterface iBookInterface;

    public BookController(IBookInterface iBookInterface) {
        this.iBookInterface = iBookInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createBook(@RequestBody BookDto bookDto){
        return iBookInterface.createBook(bookDto);
    }
    @GetMapping("/all")
    public ResponseEntity<Object> getAllBooks(){
        return iBookInterface.getAllBooks();
    }
    @GetMapping("/search")
    public ResponseEntity<Object> getBookByName(@RequestParam String name) {
        return iBookInterface.getBookByName(name);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteBookByName(@RequestParam String name) {
        return iBookInterface.deleteBookByName(name);
    }
}
