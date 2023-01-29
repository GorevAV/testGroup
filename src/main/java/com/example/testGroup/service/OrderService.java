package com.example.testGroup.service;

import com.example.testGroup.domain.Department;
import com.example.testGroup.domain.Employee;
import com.example.testGroup.domain.FurnitureType;
import com.example.testGroup.domain.Order;
import com.example.testGroup.dto.OrderDTO;
import com.example.testGroup.dto.TimeDTO;
import com.example.testGroup.mapping.OrderMapper;
import com.example.testGroup.repo.EmployeeRepo;
import com.example.testGroup.repo.OrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        Employee employee = autoAssignment(orderDTO);
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

    @Transactional
    public void updateEmployeeOrder(Long orderId, OrderDTO orderDTO) {
        Order updateOrder = orderRepo.findById(orderId).orElseThrow();
        Employee employee = autoAssignment(orderDTO);
        updateOrder.setEmployee(employee);
        orderMapper.asDTO(orderRepo.save(updateOrder));
    }


    public void deleteOrder(Long orderId) {
        orderRepo.deleteById(orderId);
    }

    public Employee autoAssignment(OrderDTO orderDTO) {
        Employee employee = null;
        if (orderDTO.getTypeFurniture().equals(FurnitureType.OFFICE)) {
            employee = employeeRepo.findByDepartment(Department.OFFICE);
        }
        if (orderDTO.getTypeFurniture().equals(FurnitureType.STORAGE)) {
            employee = employeeRepo.findByDepartment(Department.STORAGE);
        }
        if (orderDTO.getTypeFurniture().equals(FurnitureType.CUSHION)) {
            employee = employeeRepo.findByDepartment(Department.CUSHION);
        }
        return employee;
    }

    public String timeComplete(LocalDateTime dateCompletion) {
        LocalDateTime today = LocalDateTime.now();
        return "days - " + ChronoUnit.DAYS.between(today, dateCompletion)
                + ", hours - " + ChronoUnit.HOURS.between(today.toLocalTime(), dateCompletion.toLocalTime());
    }

    public List<OrderDTO> getOrdersNotComplete() {
        return orderMapper.asDTOs(orderRepo.findByStatus(false));
    }

    public List<OrderDTO> getOrdersDepartment(Department department) {
        return orderMapper.asDTOs(orderRepo.findByDepartment(department));
    }

    public List<OrderDTO> getOrdersEmployee(String firstName, String lastName) {
        return orderMapper.asDTOs(orderRepo.findByEmployee(firstName, lastName));
    }

    public TimeDTO getOrderTime(Long orderId) {
        Optional<Order> order = orderRepo.findById(orderId);
        String time = timeComplete(order.get().getDateCompletion());
        return new TimeDTO(time);
    }
}


