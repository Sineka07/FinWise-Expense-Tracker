package com.finwise.controller;

import com.finwise.dao.IncomeDAO;
import com.finwise.dao.UserDAO;
import com.finwise.model.Income;
import com.finwise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/income")
@CrossOrigin(origins = "*")
public class IncomeController {
    
    @Autowired
    private IncomeDAO incomeDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @PostMapping("/add")
    public Map<String, Object> addIncome(@RequestBody Income income, @RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userDAO.getUserById(userId);
            income.setUser(user);
            incomeDAO.saveIncome(income);
            response.put("success", true);
            response.put("message", "Income added");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        return response;
    }
    
    @GetMapping("/user/{userId}")
    public Map<String, Object> getIncomes(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userDAO.getUserById(userId);
            List<Income> incomes = incomeDAO.getIncomesByUser(user);
            response.put("success", true);
            response.put("incomes", incomes);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error fetching incomes");
        }
        return response;
    }
}