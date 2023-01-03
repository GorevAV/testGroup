package com.example.testGroup.controllers;

import com.example.testGroup.domain.Order;
import com.example.testGroup.dto.OrderDTO;
import com.example.testGroup.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

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
}
