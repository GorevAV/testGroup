package com.example.testGroup.service;

import com.example.testGroup.domain.Employee;
import com.example.testGroup.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepo.findById(employeeId).get();
    }

    public Employee insert(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void updateEmployee(Long employeeId, Employee employee) {
        Employee updateEmployee = employeeRepo.findById(employeeId).get();
        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setLastName(employee.getLastName());
        updateEmployee.setDepartment(employee.getDepartment());
        employeeRepo.save(updateEmployee);
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepo.deleteById(employeeId);
    }
}
