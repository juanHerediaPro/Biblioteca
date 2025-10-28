package com.biblioteca.Biblioteca.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.Biblioteca.Entities.User;
import com.biblioteca.Biblioteca.ServiceInterfaces.IUserServiceInterface;

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

    @GetMapping({"", "/"}) // <--- AÑADE ESTA LÍNEA AQUÍ
    public ResponseEntity<Object> getAllUsers(){
        return iUserServiceInterface.getAllUsers();
    }
    
    // Dejar /all si quieres mantenerlo, o eliminar el mapeo si solo quieres el nuevo
    @GetMapping("/all") 
    public ResponseEntity<Object> getAllUsersDeprecated(){
        return iUserServiceInterface.getAllUsers();
    }
    
    @DeleteMapping("/delete/{idCard}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long idCard){
        return iUserServiceInterface.DeleteUserById(idCard);
    }

}
