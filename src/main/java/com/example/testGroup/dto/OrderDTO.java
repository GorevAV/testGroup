package com.example.testGroup.dto;

import com.example.testGroup.domain.FurnitureType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class OrderDTO {
    private String name;

    private FurnitureType typeFurniture;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime dateCompletion;

    private boolean status;

    private Long employeeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FurnitureType getTypeFurniture() {
        return typeFurniture;
    }

    public void setTypeFurniture(FurnitureType typeFurniture) {
        this.typeFurniture = typeFurniture;
    }

    public LocalDateTime getDateCompletion() {
        return dateCompletion;
    }

    public void setDateCompletion(LocalDateTime dateCompletion) {
        this.dateCompletion = dateCompletion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
