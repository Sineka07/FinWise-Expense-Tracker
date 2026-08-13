package com.finwise.controller;

import com.finwise.dao.SubscriptionDAO;
import com.finwise.dao.UserDAO;
import com.finwise.model.Subscription;
import com.finwise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin(origins = "*")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionDAO subscriptionDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @PostMapping("/add")
    public Map<String, Object> addSubscription(@RequestBody Subscription subscription, @RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userDAO.getUserById(userId);
            subscription.setUser(user);
            subscriptionDAO.saveSubscription(subscription);
            response.put("success", true);
            response.put("message", "Subscription added");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        return response;
    }
    
    @GetMapping("/user/{userId}")
    public Map<String, Object> getSubscriptions(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userDAO.getUserById(userId);
            List<Subscription> subscriptions = subscriptionDAO.getSubscriptionsByUser(user);
            response.put("success", true);
            response.put("subscriptions", subscriptions);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error fetching subscriptions");
        }
        return response;
    }
}