package com.example.testGroup.service;

import com.example.testGroup.domain.Employee;
import com.example.testGroup.domain.Order;
import com.example.testGroup.dto.OrderDTO;
import com.example.testGroup.mapping.OrderMapper;
import com.example.testGroup.repo.EmployeeRepo;
import com.example.testGroup.repo.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final OrderRepo orderRepo;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public EmployeeService(EmployeeRepo employeeRepo, OrderRepo orderRepo, OrderService orderService, OrderMapper orderMapper) {
        this.employeeRepo = employeeRepo;
        this.orderRepo = orderRepo;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepo.findById(employeeId).orElseThrow();
    }

    public Employee insert(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void updateEmployee(Long employeeId, Employee employee) {
        Employee updateEmployee = employeeRepo.findById(employeeId).orElseThrow();
        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setLastName(employee.getLastName());
        updateEmployee.setDepartment(employee.getDepartment());
        employeeRepo.save(updateEmployee);
    }

    public void deleteEmployee(Long employeeId) {
        List<Order> orders = orderRepo.findByEmployee_Id(employeeId);
        employeeRepo.deleteById(employeeId);
        for (Order order : orders){
            orderService.updateEmployeeOrder(order.getId(), orderMapper.asDTO(order));
        }
    }

}
