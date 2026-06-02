package com.example.product.dto;

import java.math.BigDecimal;

public record EmployeeDTO(String employeeName, String email, BigDecimal salary, String deptName,String deptCode) {
}
