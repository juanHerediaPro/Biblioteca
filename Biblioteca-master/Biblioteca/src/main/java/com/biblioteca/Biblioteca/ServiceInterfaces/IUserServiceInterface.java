package com.biblioteca.Biblioteca.ServiceInterfaces;

import com.biblioteca.Biblioteca.Entities.User;
import org.springframework.http.ResponseEntity;

public interface IUserServiceInterface {
    ResponseEntity<Object> createUser(User user);
    ResponseEntity<Object> getUserByIdCard(Long idCard);
    ResponseEntity<Object> getAllUsers();
    ResponseEntity<Object> DeleteUserById(Long idCard);
}
