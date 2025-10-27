package com.biblioteca.Biblioteca.Controllers;

import com.biblioteca.Biblioteca.Entities.User;
import com.biblioteca.Biblioteca.ServiceInterfaces.IUserServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserServiceInterface iUserServiceInterface;

    public UserController(IUserServiceInterface iUserServiceInterface) {
        this.iUserServiceInterface = iUserServiceInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        return iUserServiceInterface.createUser(user);
    }
    @GetMapping("/{idCard}")
    public ResponseEntity<Object> getUserByidCard(@PathVariable Long idCard){
        return iUserServiceInterface.getUserByIdCard(idCard);
    }
    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers(){
        return iUserServiceInterface.getAllUsers();
    }
    @DeleteMapping("/delete/{idCard}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long idCard){
        return iUserServiceInterface.DeleteUserById(idCard);
    }

}
