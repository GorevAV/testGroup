package com.example.testGroup.dto;

public class TimeDTO {

    private String orderTime;

    public TimeDTO(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
