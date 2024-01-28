package com.careridetransport.server.repository;

import com.careridetransport.server.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);
}
