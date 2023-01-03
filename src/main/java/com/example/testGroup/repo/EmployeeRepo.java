package com.example.testGroup.repo;

import com.example.testGroup.domain.Employee;
import com.example.testGroup.domain.FurnitureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(FurnitureType department);
}
