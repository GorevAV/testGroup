package com.example.testGroup.controllers;

import com.example.testGroup.domain.Order;
import com.example.testGroup.dto.OrderDTO;
import com.example.testGroup.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<List<Order>> getAllOrders() {

        List<Order> orders = orderService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping({"/{orderId}"})
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody OrderDTO orderDTO) {
        Order order1 = orderService.insert(orderDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("order", "/api/v1/order/" + order1.getId().toString());
        return new ResponseEntity<>(order1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{orderId}"})
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") Long orderId, @RequestBody Order order) {
        orderService.updateOrder(orderId, order);
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @DeleteMapping({"/{deleteId}"})
    public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
