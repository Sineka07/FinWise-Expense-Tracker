package com.finwise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finwise.dao.SubscriptionDAO;
import com.finwise.dao.UserDAO;
import com.finwise.model.User;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin(origins = "*")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionDAO subscriptionDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @PostMapping("/add")
    public Map<String, Object> addSubscription(@RequestBody Object subscription, @RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userDAO.getUserById(userId);
            subscription.getClass().getMethod("setUser", User.class).invoke(subscription, user);
            subscriptionDAO.getClass().getMethod("saveSubscription", subscription.getClass())
                    .invoke(subscriptionDAO, subscription);
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
            List<?> subscriptions = subscriptionDAO.getSubscriptionsByUser(user);
            response.put("success", true);
            response.put("subscriptions", subscriptions);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error fetching subscriptions");
        }
        return response;
    }
}