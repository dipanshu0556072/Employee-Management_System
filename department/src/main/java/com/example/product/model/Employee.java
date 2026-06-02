package com.example.product.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @Column(name = "salary")
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    @JsonBackReference
    private Department deptName;


}