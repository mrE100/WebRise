package com.example.subscriptionservice.service;

import com.example.subscriptionservice.model.User;
import com.example.subscriptionservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        log.info("Creating new user: {}", user.getEmail());
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Другие методы
}