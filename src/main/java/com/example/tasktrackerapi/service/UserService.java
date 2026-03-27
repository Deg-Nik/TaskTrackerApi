package com.example.tasktrackerapi.service;


import com.example.tasktrackerapi.entity.User;
import com.example.tasktrackerapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь с именем " + username + " не найден."));
    }

}
