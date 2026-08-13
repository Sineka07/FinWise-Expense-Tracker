// package com.finwise.controller;

// import com.finwise.dao.SplitDAO;
// import com.finwise.dao.UserDAO;
// import com.finwise.model.SplitTransaction;
// import com.finwise.model.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/split")
// @CrossOrigin(origins = "*")
// public class SplitController {
    
//     @Autowired
//     private SplitDAO splitDAO;
    
//     @Autowired
//     private UserDAO userDAO;
    
//     @PostMapping("/add")
//     public Map<String, Object> addSplit(@RequestBody SplitTransaction split, @RequestParam Long userId) {
//         Map<String, Object> response = new HashMap<>();
//         try {
//             User user = userDAO.getUserById(userId);
//             split.setPaidBy(user);
//             splitDAO.saveSplit(split);
//             response.put("success", true);
//             response.put("message", "Split added");
//         } catch (Exception e) {
//             response.put("success", false);
//             response.put("message", "Error: " + e.getMessage());
//         }
//         return response;
//     }
    
//     @GetMapping("/user/{userId}")
//     public Map<String, Object> getSplits(@PathVariable Long userId) {
//         Map<String, Object> response = new HashMap<>();
//         try {
//             User user = userDAO.getUserById(userId);
//             List<SplitTransaction> splits = splitDAO.getSplitsByUser(user);
//             response.put("success", true);
//             response.put("splits", splits);
//         } catch (Exception e) {
//             response.put("success", false);
//             response.put("message", "Error fetching splits");
//         }
//         return response;
//     }
// }