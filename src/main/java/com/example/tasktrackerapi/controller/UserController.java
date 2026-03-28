package com.example.tasktrackerapi.controller;

import com.example.tasktrackerapi.entity.User;
import com.example.tasktrackerapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Получение пользователя по username
     */
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);

        // скрываем пароль перед отдачей наружу
        user.setPassword(null);

        return ResponseEntity.ok(user);
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.createUser(user);

        // скрываем пароль
        created.setPassword(null);

        return ResponseEntity.status(201).body(created);
    }

}
