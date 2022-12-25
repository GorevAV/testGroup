package com.example.testGroup.service;

import com.example.testGroup.domain.Employee;
import com.example.testGroup.domain.Order;
import com.example.testGroup.dto.OrderDTO;
import com.example.testGroup.mapping.OrderMapper;
import com.example.testGroup.repo.EmployeeRepo;
import com.example.testGroup.repo.OrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepo orderRepo;
    private final EmployeeRepo employeeRepo;

    public OrderService(OrderMapper orderMapper, OrderRepo orderRepo, EmployeeRepo employeeRepo) {
        this.orderMapper = orderMapper;
        this.orderRepo = orderRepo;
        this.employeeRepo = employeeRepo;
    }

    public List<OrderDTO> getOrders() {
        return orderMapper.asDTOs(orderRepo.findAll());
    }

    public OrderDTO getOrderById(Long id) {
        return orderMapper.asDTO(orderRepo.findById(id).orElseThrow());
    }

    public OrderDTO insert(OrderDTO orderDTO) {
        Employee employee = employeeRepo.getReferenceById(orderDTO.getEmployeeId());
        Order order = new Order();
        order.setName(orderDTO.getName());
        order.setStatus(false);
        order.setDateCompletion(orderDTO.getDateCompletion());
        order.setTypeFurniture(orderDTO.getTypeFurniture());
        order.setEmployee(employee);
        return orderMapper.asDTO(orderRepo.save(order));
    }

    @Transactional
    public OrderDTO updateOrder(Long orderId, OrderDTO order) {
        Order updateOrder = orderRepo.findById(orderId).orElseThrow();
        updateOrder.setName(order.getName());
        updateOrder.setDateCompletion(order.getDateCompletion());
        updateOrder.setTypeFurniture(order.getTypeFurniture());
        updateOrder.setStatus(order.isStatus());
        return orderMapper.asDTO(orderRepo.save(updateOrder));
    }

    public void deleteOrder(Long orderId) {
        orderRepo.deleteById(orderId);
    }
}
