package com.biblioteca.Biblioteca.ServiceInterfaces;

import com.biblioteca.Biblioteca.Entities.AuthorsNationality;
import org.springframework.http.ResponseEntity;

public interface IAuthorNationalityInterface {

    ResponseEntity<Object> createNationality(AuthorsNationality authorsNationality);
    ResponseEntity<Object> getAllNationalities();
    ResponseEntity<Object> getNationalityByName(String nationality);
    ResponseEntity<Object> deleteNationality(String nationality);

}
