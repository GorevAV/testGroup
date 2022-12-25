package com.example.testGroup.service;

import com.example.testGroup.domain.Employee;
import com.example.testGroup.domain.Order;
import com.example.testGroup.dto.OrderDTO;
import com.example.testGroup.repo.EmployeeRepo;
import com.example.testGroup.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final EmployeeRepo employeeRepo;

    public OrderService(OrderRepo orderRepo, EmployeeRepo employeeRepo) {
        this.orderRepo = orderRepo;
        this.employeeRepo = employeeRepo;
    }

    public List<Order> getOrders() {
        return orderRepo.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepo.findById(id).get();
    }

    public Order insert(OrderDTO orderDTO) {
        Employee employee = employeeRepo.getReferenceById(orderDTO.getEmployeeId());
        Order order = new Order();
        order.setName(orderDTO.getName());
        order.setStatus(false);
        order.setDateCompletion(orderDTO.getDateCompletion());
        order.setTypeFurniture(orderDTO.getTypeFurniture());
        order.setEmployee(employee);
        return orderRepo.save(order);
    }

    public void updateOrder(Long orderId, Order order) {
        Order updateOrder = orderRepo.findById(orderId).get();
        updateOrder.setName(order.getName());
        updateOrder.setDateCompletion(order.getDateCompletion());
        updateOrder.setTypeFurniture(order.getTypeFurniture());
        updateOrder.setStatus(order.isStatus());
        orderRepo.save(updateOrder);
    }

    public void deleteOrder(Long orderId) {
        orderRepo.deleteById(orderId);
    }
}
