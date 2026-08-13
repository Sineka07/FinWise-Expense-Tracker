package com.finwise.controller;

import com.finwise.dao.GoalDAO;
import com.finwise.dao.UserDAO;
import com.finwise.model.Goal;
import com.finwise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "*")
public class GoalController {
    
    @Autowired
    private GoalDAO goalDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @PostMapping("/add")
    public Map<String, Object> addGoal(@RequestBody Goal goal, @RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userDAO.getUserById(userId);
            goal.setUser(user);
            goalDAO.saveGoal(goal);
            response.put("success", true);
            response.put("message", "Goal added");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        return response;
    }
    
    @GetMapping("/user/{userId}")
    public Map<String, Object> getGoals(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userDAO.getUserById(userId);
            List<Goal> goals = goalDAO.getGoalsByUser(user);
            response.put("success", true);
            response.put("goals", goals);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error fetching goals");
        }
        return response;
    }
}