package com.finwise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finwise.dao.ExpenseDAO;
import com.finwise.dao.UserDAO;
import com.finwise.model.Expense;
import com.finwise.model.User;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {
    
    @Autowired
    private ExpenseDAO expenseDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @PostMapping("/add")
    public Map<String, Object> addExpense(@RequestBody Expense expense, @RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userDAO.getUserById(userId);
            if (user == null) {
                response.put("success", false);
                response.put("message", "User not found");
                return response;
            }
            expense.setUser(user);
            expenseDAO.saveExpense(expense);
            response.put("success", true);
            response.put("message", "Expense added successfully");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error adding expense: " + e.getMessage());
        }
        return response;
    }
    
    @GetMapping("/user/{userId}")
    public Map<String, Object> getExpenses(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userDAO.getUserById(userId);
            if (user == null) {
                response.put("success", false);
                response.put("message", "User not found");
                return response;
            }
            List<Expense> expenses = expenseDAO.getExpensesByUser(user);
            response.put("success", true);
            response.put("expenses", expenses);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error fetching expenses");
        }
        return response;
    }
    
    @DeleteMapping("/delete/{expenseId}")
    public Map<String, Object> deleteExpense(@PathVariable Long expenseId) {
        Map<String, Object> response = new HashMap<>();
        try {
            expenseDAO.deleteExpense(expenseId);
            response.put("success", true);
            response.put("message", "Expense deleted successfully");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error deleting expense");
        }
        return response;
    }
}