package com.example.testGroup.repo;

import com.example.testGroup.domain.Employee;
import com.example.testGroup.domain.FurnitureType;
import com.example.testGroup.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByEmployee_Id(Long employeeId);
}
