// package com.finwise.controller;

// import com.finwise.dao.*;
// import com.finwise.model.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/dashboard")
// @CrossOrigin(origins = "*")
// public class DashboardController {
    
//     @Autowired
//     private UserDAO userDAO;
    
//     @Autowired
//     private ExpenseDAO expenseDAO;
    
//     @Autowired
//     private BudgetDAO budgetDAO;
    
//     @GetMapping("/{userId}")
//     public Map<String, Object> getDashboard(@PathVariable Long userId) {
//         Map<String, Object> response = new HashMap<>();
//         try {
//             User user = userDAO.getUserById(userId);
//             if (user == null) {
//                 response.put("success", false);
//                 response.put("message", "User not found");
//                 return response;
//             }
            
//             List<Expense> expenses = expenseDAO.getExpensesByUser(user);
            
//             double totalIncome = 50000; // Sample
//             double totalExpenses = 0;
//             for (Expense e : expenses) {
//                 totalExpenses += e.getAmount();
//             }
//             double balance = totalIncome - totalExpenses;
            
//             response.put("success", true);
//             response.put("totalIncome", totalIncome);
//             response.put("totalExpenses", totalExpenses);
//             response.put("balance", balance);
//             response.put("budgetPercentage", 65);
//             response.put("transactions", expenses.subList(0, Math.min(5, expenses.size())));
            
//         } catch (Exception e) {
//             response.put("success", false);
//             response.put("message", "Error loading dashboard");
//         }
//         return response;
//     }
// }