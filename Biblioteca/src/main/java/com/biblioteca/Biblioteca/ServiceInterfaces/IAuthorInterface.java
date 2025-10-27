package com.biblioteca.Biblioteca.ServiceInterfaces;

import com.biblioteca.Biblioteca.Entities.Author;
import org.springframework.http.ResponseEntity;

public interface IAuthorInterface {

    ResponseEntity<Object> createAuthor(Author author);
    public ResponseEntity<Object> getAllAuthors();
    ResponseEntity<Object> getAuthorByLastName(String lastName);
    public ResponseEntity<Object> deleteAuthorByLastName(String lastName);

}
