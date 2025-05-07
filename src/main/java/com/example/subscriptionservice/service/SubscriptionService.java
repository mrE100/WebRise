package com.example.subscriptionservice.service;

import com.example.subscriptionservice.model.Subscription;
import com.example.subscriptionservice.model.User;
import com.example.subscriptionservice.repository.SubscriptionRepository;
import com.example.subscriptionservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public Subscription addSubscription(Long userId, Subscription subscription) {
        log.info("Adding subscription for user id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        subscription.setUser(user);
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getUserSubscriptions(Long userId) {
        log.info("Fetching subscriptions for user id: {}", userId);
        return subscriptionRepository.findByUserId(userId);
    }

    public List<Object[]> getTop3PopularSubscriptions() {
        log.info("Fetching top 3 popular subscriptions");
        return subscriptionRepository.findTop3PopularSubscriptions();
    }
    // Другие методы
}