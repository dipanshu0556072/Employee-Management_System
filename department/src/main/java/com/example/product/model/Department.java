package com.example.product.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Department")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Department
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "department_name",nullable = false,unique = true)
    private String departmentName;

    @Column(name = "department_code", nullable = false, unique = true)
    private String departmentCode;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "deptName",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employeeList=new ArrayList<>();

}
