package com.careridetransport.server.service;

import com.careridetransport.server.model.Employee;
import com.careridetransport.server.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticateEmployee(String username, String password) {
        try {
            Employee employee = employeeRepository.findByUsername(username);
            if (employee == null) {
                logger.info("Authentication failed: Employee not found with username: {}", username);
                return false;
            }
            return passwordEncoder.matches(password, employee.getPassword());
        } catch (Exception e) {
            logger.error("Error authenticating employee: ", e);
            throw e; // or handle it according to your application's policy
        }
    }
}
