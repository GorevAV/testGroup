package com.example.testGroup.mapping;

import com.example.testGroup.domain.Order;
import com.example.testGroup.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public List<OrderDTO> asDTOs(List<Order> entities){
       return entities.stream()
                .map(this::asDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO asDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setDateCompletion(order.getDateCompletion());
        orderDTO.setName(order.getName());
        orderDTO.setStatus(order.isStatus());
        orderDTO.setTypeFurniture(order.getTypeFurniture());
        orderDTO.setEmployeeId(order.getEmployee().getId());
        orderDTO.setFirstname(order.getEmployee().getFirstName());
        orderDTO.setLastname(order.getEmployee().getLastName());
        orderDTO.setDepartment(order.getEmployee().getDepartment());
        return orderDTO;
    }
}
