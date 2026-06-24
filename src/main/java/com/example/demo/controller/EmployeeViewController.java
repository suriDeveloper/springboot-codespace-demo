package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeViewController {

    @GetMapping
    public String employeesPage() {
        return "redirect:/static/employees.html";
    }
}
