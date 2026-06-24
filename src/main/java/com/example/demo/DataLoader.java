package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(EmployeeRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Employee(null, "John Doe", "john@example.com", "Engineering", 75000.0));
                repository.save(new Employee(null, "Jane Smith", "jane@example.com", "HR", 65000.0));
                repository.save(new Employee(null, "Bob Johnson", "bob@example.com", "Sales", 70000.0));
            }
        };
    }
}
