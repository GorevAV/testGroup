package com.example.testGroup.repo;

import com.example.testGroup.domain.Department;
import com.example.testGroup.domain.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query("SELECT e from Employee e join fetch e.orders")
    List<Employee> findAllWithOrders();

}
