package com.finwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinWiseApplication {
    public static void main(String[] args) {
        // Allow public key retrieval for MySQL 8
        System.setProperty("mysql.allowPublicKeyRetrieval", "true");
        
        SpringApplication.run(FinWiseApplication.class, args);
        System.out.println("=========================================");
        System.out.println("  🚀 FINWISE - Smart Personal Finance Manager");
        System.out.println("  ✅ Server Started Successfully!");
        System.out.println("  📍 http://localhost:8080");
        System.out.println("=========================================");
    }
}