/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.controllers;

import com.example.FinalProject.services.EmployeeService;
import com.example.FinalProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */

@Controller
@RequestMapping("/user")
public class UserController {
    private EmployeeService employeeService;
    private UserService userService;
    
    @Autowired
    public UserController(EmployeeService employeeService, UserService userService) {
        this.employeeService = employeeService;
        this.userService = userService;
    }
    
    //GET ALL
    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", userService.getAll());
        return "employee/index";
    }

}
