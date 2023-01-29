package com.example.testGroup.repo;

import com.example.testGroup.domain.Department;
import com.example.testGroup.domain.Employee;
import com.example.testGroup.domain.FurnitureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT e FROM employee e where e.department = :department ORDER BY RANDOM() DESC LIMIT 1", nativeQuery = true)
    Employee findByDepartment(@Param("department") Department department);

}
