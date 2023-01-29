package com.example.testGroup.repo;

import com.example.testGroup.domain.Department;
import com.example.testGroup.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByEmployee_Id(Long employeeId);

    List<Order> findByStatus(boolean status);

    @Query("select o from Employee e join e.orders o where e.department = :department")
    List<Order> findByDepartment(@Param("department") Department department);

    @Query("select o from Order o where o.employee.firstName = :firstName and o.employee.lastName = :lastName")
    List<Order> findByEmployee(@Param("firstName") String firstName,@Param("lastName") String lastName);
}
