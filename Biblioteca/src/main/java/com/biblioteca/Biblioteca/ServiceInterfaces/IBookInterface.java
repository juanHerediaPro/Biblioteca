package com.biblioteca.Biblioteca.ServiceInterfaces;

import com.biblioteca.Biblioteca.Dtos.BookDto;
import com.biblioteca.Biblioteca.Entities.Book;
import org.springframework.http.ResponseEntity;

public interface IBookInterface {

    ResponseEntity<Object> createBook(BookDto bookDto);
    public ResponseEntity<Object> getAllBooks();
    public ResponseEntity<Object> getBookByName(String name);
    public ResponseEntity<Object> deleteBookByName(String name);

}
