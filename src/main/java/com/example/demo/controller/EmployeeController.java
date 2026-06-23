package com.example.demo.controller;

import com.example.demo.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    private List<Employee> employees = new ArrayList<>();
    private Long nextId = 1L;

    public EmployeeController() {
        // Initialize with some sample data
        employees.add(new Employee(1L, "John Doe", "john@example.com", "Engineering", 75000.0));
        employees.add(new Employee(2L, "Jane Smith", "jane@example.com", "HR", 65000.0));
        employees.add(new Employee(3L, "Bob Johnson", "bob@example.com", "Sales", 70000.0));
        nextId = 4L;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employee.setId(nextId++);
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Optional<Employee> employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        
        if (employee.isPresent()) {
            Employee emp = employee.get();
            if (employeeDetails.getName() != null) {
                emp.setName(employeeDetails.getName());
            }
            if (employeeDetails.getEmail() != null) {
                emp.setEmail(employeeDetails.getEmail());
            }
            if (employeeDetails.getDepartment() != null) {
                emp.setDepartment(employeeDetails.getDepartment());
            }
            if (employeeDetails.getSalary() != null) {
                emp.setSalary(employeeDetails.getSalary());
            }
            return ResponseEntity.ok(emp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        boolean removed = employees.removeIf(e -> e.getId().equals(id));
        
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
