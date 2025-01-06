package com.pupilmed.pupilmedapi.controller;

import com.pupilmed.pupilmedapi.model.User;
import com.pupilmed.pupilmedapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);
    }
    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }
    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }
    @GetMapping("/find")
    public User find(@RequestParam int id){
        return userService.findById(id);
    }
    @PutMapping("/update")
    public User update(@RequestBody User user){
        return userService.update(user);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id){
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();  // Status 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Status 404 Not Found, jeśli użytkownik nie został znaleziony
        }
    }
    @PutMapping("/deactivate")
    public ResponseEntity<Void> deactivate(@RequestParam int id){
        try {
            userService.deactivate(id);
            return ResponseEntity.ok().build();  // Status 200 Ok
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Status 404 Not Found, jeśli użytkownik nie został znaleziony
        }
    }
}
