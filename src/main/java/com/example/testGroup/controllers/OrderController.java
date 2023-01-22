package com.example.testGroup.controllers;

import com.example.testGroup.domain.Department;
import com.example.testGroup.dto.OrderDTO;
import com.example.testGroup.dto.TimeDTO;
import com.example.testGroup.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping({"/{orderId}"})
    public OrderDTO getOrder(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO saveOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.insert(orderDTO);
    }

    @PutMapping({"/{orderId}"})
    public OrderDTO updateOrder(@PathVariable("orderId") Long orderId, @RequestBody OrderDTO orderDTO) {
       return orderService.updateOrder(orderId, orderDTO);
    }

    @DeleteMapping({"/{deleteId}"})
    public void deleteOrder(@PathVariable("deleteId") Long orderId) {
        orderService.deleteOrder(orderId);
    }
    @GetMapping({"/not-сomplete"})
    public List<OrderDTO> getOrdersNotComplete() {
        return orderService.getOrdersNotComplete();
    }

    @GetMapping("/department")
    public List<OrderDTO> getOrdersDepartment(@RequestParam Department department) {
        return orderService.getOrdersDepartment(department);

    }
    @GetMapping("/employee")
    public List<OrderDTO> getOrdersDepartment(@RequestParam String firstName,
                                              @RequestParam String lastName) {
        return orderService.getOrdersEmployee(firstName, lastName);
    }
    @GetMapping({"/time-order/{orderId}"})
    public TimeDTO getOrderTime(@PathVariable Long orderId) {
        return orderService.getOrderTime(orderId);
    }

}
