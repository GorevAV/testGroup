package com.example.testGroup.util;

import com.example.testGroup.domain.Department;
import com.example.testGroup.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class RandomQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public Employee randomEmployee(Department department) {
        return entityManager.createQuery("SELECT e FROM Employee e where e.department = :department ORDER BY RANDOM() DESC LIMIT 1", Employee.class)
                .setParameter("department", department)
                .getSingleResult();
    }
}
