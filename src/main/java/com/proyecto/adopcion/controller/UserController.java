package com.proyecto.adopcion.controller;

import com.proyecto.adopcion.model.User;
import com.proyecto.adopcion.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        User createUser = userService.createUser(user);

        return ResponseEntity.ok(createUser);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Valid User user) {

        try {
            User userUpdate = userService.updateUser(id, user);
            return ResponseEntity.ok(userUpdate);

        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> parcialUpdateUser(@PathVariable Long id, @RequestBody User user) {

        try {
            User userUpdate = userService.parcialUpdateUser(id, user);
            return ResponseEntity.ok(userUpdate);

        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
