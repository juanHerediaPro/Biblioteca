package com.biblioteca.Biblioteca.Services;

import com.biblioteca.Biblioteca.Entities.AuthorsNationality;
import com.biblioteca.Biblioteca.Repository.IAuthorNationalityRepository;
import com.biblioteca.Biblioteca.ServiceInterfaces.IAuthorNationalityInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthorNationalityService implements IAuthorNationalityInterface {

    Map<String,Object> response = new HashMap<>();

    private final IAuthorNationalityRepository iAuthorNationalityRepository;

    public AuthorNationalityService(IAuthorNationalityRepository iAuthorNationalityRepository) {
        this.iAuthorNationalityRepository = iAuthorNationalityRepository;
    }

    @Override
    public ResponseEntity<Object> createNationality(AuthorsNationality authorsNationality){
        if (iAuthorNationalityRepository.findByNationality(authorsNationality.getNationality()).isPresent()){
            response.put("The nationality already exist",".");
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }
        AuthorsNationality authorsNationality1 = iAuthorNationalityRepository.save(authorsNationality);
        response.put("saved", authorsNationality1);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public  ResponseEntity<Object> getAllNationalities(){
        List<AuthorsNationality> authorsNationalities = iAuthorNationalityRepository.findAll();

        if (authorsNationalities.isEmpty()){
            return new ResponseEntity<>("there are not nationalities registered", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(authorsNationalities, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getNationalityByName(String nationality){
        Optional<AuthorsNationality> authorsNationality = iAuthorNationalityRepository.findByNationality(nationality);

        if (authorsNationality.isEmpty()){
            return new ResponseEntity<>("there are not nationalities registered with this name", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(authorsNationality, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> deleteNationality(String nationality){
        Optional<AuthorsNationality> authorsNationality = iAuthorNationalityRepository.findByNationality(nationality);

        if (authorsNationality.isEmpty()){
            return new ResponseEntity<>("there are nott nationalities registered with this name", HttpStatus.NOT_FOUND);
        } else {
            iAuthorNationalityRepository.delete(authorsNationality.get());
            response.put("user delete successfully",authorsNationality);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
