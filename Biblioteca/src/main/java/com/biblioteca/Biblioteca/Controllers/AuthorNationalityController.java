package com.biblioteca.Biblioteca.Controllers;

import com.biblioteca.Biblioteca.Entities.AuthorsNationality;
import com.biblioteca.Biblioteca.ServiceInterfaces.IAuthorNationalityInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/authorNationality")
public class AuthorNationalityController {

    private final IAuthorNationalityInterface iAuthorNationalityInterface;

    public AuthorNationalityController(IAuthorNationalityInterface iAuthorNationalityInterface) {
        this.iAuthorNationalityInterface = iAuthorNationalityInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createNationality(@RequestBody AuthorsNationality authorsNationality){
        return iAuthorNationalityInterface.createNationality(authorsNationality);
    }
    @GetMapping("/all")
    public ResponseEntity<Object> getAllNationalities(){
        return iAuthorNationalityInterface.getAllNationalities();
    }
    @GetMapping("/search")
    public ResponseEntity<Object> getByCountry(@RequestParam String nationality) {
        return iAuthorNationalityInterface.getNationalityByName(nationality);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteNationality(@RequestParam String nationality) {
        return iAuthorNationalityInterface.deleteNationality(nationality);
    }

}
