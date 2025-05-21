package com.biblioteca.Biblioteca.Services;

import com.biblioteca.Biblioteca.Entities.Author;
import com.biblioteca.Biblioteca.Repository.IAuthorRepository;
import com.biblioteca.Biblioteca.ServiceInterfaces.IAuthorInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthorService implements IAuthorInterface {

    Map<String,Object> response = new HashMap<>();

    private final IAuthorRepository iAuthorRepository;

    public AuthorService(IAuthorRepository iAuthorRepository) {
        this.iAuthorRepository = iAuthorRepository;
    }

    @Override
    public ResponseEntity<Object> createAuthor(Author author){
        Author author1 = iAuthorRepository.save(author);
        return new ResponseEntity<>("Author saved successfully",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getAllAuthors(){
        List<Author> authors = iAuthorRepository.findAll();

        if (authors.isEmpty()){
            return new ResponseEntity<>("there are not authors registered", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(authors, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getAuthorByLastName(String lastName){
        Optional<Author> author = iAuthorRepository.findByLastName(lastName);

        if (author.isEmpty()){
            return new ResponseEntity<>("there are not authors registered with this last name", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(author, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> deleteAuthorByLastName(String lastName){
        Optional<Author> author = iAuthorRepository.findByLastName(lastName);

        if (author.isEmpty()){
            return new ResponseEntity<>("there are not authors registered with this last name", HttpStatus.NOT_FOUND);
        } else {
            iAuthorRepository.delete(author.get());
            response.put("delete successfully", null);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }
    }


}
