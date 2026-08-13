package com.finwise.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finwise.dao.UserDAO;
import com.finwise.model.User;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private UserDAO userDAO;
    
    // ========== REGISTER ==========
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("=== REGISTER REQUEST ===");
        System.out.println("Email: " + user.getEmail());
        
        try {
            User existingUser = userDAO.getUserByEmail(user.getEmail());
            if (existingUser != null) {
                response.put("success", false);
                response.put("message", "Email already registered");
                return response;
            }
            
            userDAO.saveUser(user);
            System.out.println("✅ User registered: " + user.getEmail());
            
            response.put("success", true);
            response.put("message", "Registration successful");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        return response;
    }
    
    // ========== LOGIN ==========
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("=== LOGIN REQUEST ===");
        
        try {
            String email = loginData.get("email");
            String password = loginData.get("password");
            
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
            
            User user = userDAO.getUserByEmail(email);
            
            if (user == null) {
                System.out.println("❌ User not found");
                response.put("success", false);
                response.put("message", "User not found");
                return response;
            }
            
            if (!user.getPassword().equals(password)) {
                System.out.println("❌ Invalid password");
                response.put("success", false);
                response.put("message", "Invalid password");
                return response;
            }
            
            System.out.println("✅ Login successful: " + user.getFullName());
            
            response.put("success", true);
            response.put("userId", user.getId());
            response.put("fullName", user.getFullName());
            response.put("message", "Login successful");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        return response;
    }
}