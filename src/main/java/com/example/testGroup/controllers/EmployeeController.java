package com.example.testGroup.controllers;

import com.example.testGroup.domain.Employee;
import com.example.testGroup.domain.Order;
import com.example.testGroup.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<List<Employee>> getAllEmployees() {

        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping({"/{employeeId}"})
    public ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee employee1 = employeeService.insert(employee);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("employee", "/api/v1/employee/" + employee1.getId().toString());
        return new ResponseEntity<>(employee1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{employeeId}"})
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody Employee employee) {
        employeeService.updateEmployee(employeeId, employee);
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @DeleteMapping({"/{deleteId}"})
    public ResponseEntity<Order> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
