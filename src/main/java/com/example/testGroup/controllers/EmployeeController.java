package com.example.testGroup.controllers;

import com.example.testGroup.domain.Employee;
import com.example.testGroup.domain.Order;
import com.example.testGroup.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping({"/{employeeId}"})
    public Employee getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.insert(employee);
    }

    @PutMapping({"/{employeeId}"})
    public void updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody Employee employee) {
        employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping({"/{deleteId}"})
    public ResponseEntity<Order> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
