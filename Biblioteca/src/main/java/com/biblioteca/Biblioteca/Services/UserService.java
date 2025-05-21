package com.biblioteca.Biblioteca.Services;

import com.biblioteca.Biblioteca.Entities.User;
import com.biblioteca.Biblioteca.Repository.IUserRepository;
import com.biblioteca.Biblioteca.ServiceInterfaces.IUserServiceInterface;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements IUserServiceInterface {

    Map<String,Object> response = new HashMap<>();

    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public ResponseEntity<Object> createUser(User user){
        if (iUserRepository.findByIdCard(user.getIdCard()).isPresent()){
            response.put("message","the user already exist");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        User saveUser = iUserRepository.save(user);
        response.put("message","user successfully created");
        response.put("user",saveUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getUserByIdCard(Long idCard){
        Optional<User> userOptional = iUserRepository.findByIdCard(idCard);

        if (userOptional.isPresent()){
            return new ResponseEntity<>(userOptional.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getAllUsers(){
        List<User> users = iUserRepository.findAll();

        if (users.isEmpty()){
            return new ResponseEntity<>("there are not users registered", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.FOUND);
        }
    }

    @Override
    public  ResponseEntity<Object> DeleteUserById(Long idCard){
        Optional<User> user = iUserRepository.findByIdCard(idCard);
        if (user.isPresent()){
            iUserRepository.delete(user.get());
            response.put("user delete successfully", user);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
