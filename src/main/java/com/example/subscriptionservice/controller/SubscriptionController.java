package com.example.subscriptionservice.controller;

import com.example.subscriptionservice.model.Subscription;
import com.example.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Subscription> addSubscription(
            @PathVariable Long userId,
            @RequestBody Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.addSubscription(userId, subscription));
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userId));
    }

    @GetMapping("/top")
    public ResponseEntity<List<Map<String, Object>>> getTopSubscriptions() {
        List<Object[]> results = subscriptionService.getTop3PopularSubscriptions();
        List<Map<String, Object>> response = results.stream()
                .map(r -> Map.of("serviceName", r[0], "count", r[1]))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Другие endpoint'ы
}