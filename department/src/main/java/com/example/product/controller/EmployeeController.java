package com.example.product.controller;

import com.example.product.dto.EmployeeDTO;
import com.example.product.dto.ResponseDTO;
import com.example.product.model.Employee;
import com.example.product.service.EmployeeService;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController
{
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //store the user
    @PostMapping("/storeUser")
    public ResponseEntity<ResponseDTO> storeUserInDB(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.saveEmployee(employeeDTO);
    }

    //emp by id
    @GetMapping("/fetchById/{empId}")
    public ResponseEntity<ResponseDTO>fetchEmployeeById(@NonNull @PathVariable Long empId){
        return employeeService.fetchEmployee(empId);
    }

    //findAll emp using pagination
    @GetMapping("/fetchEmployees")
    public ResponseEntity<ResponseDTO>fetchEmployees(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "2") int size){
        Page<Employee> employees = employeeService.getEmployees(page, size);
        ResponseDTO responseDTO=new ResponseDTO(true,"",employees);
        return ResponseEntity.ok(responseDTO);
    }

    //delete emp by Id
    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<ResponseDTO>deleteEmployee(@RequestParam(required = true) Long empId){
        return employeeService.removeEmployeeById(empId);
    }

}
