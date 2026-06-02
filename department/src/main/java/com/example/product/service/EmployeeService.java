package com.example.product.service;

import com.example.product.dto.EmployeeDTO;
import com.example.product.dto.ResponseDTO;
import com.example.product.exception.DepartmentNotExistException;
import com.example.product.exception.EmployeeAlreadyExist;
import com.example.product.exception.EmployeeNotExistError;
import com.example.product.model.Department;
import com.example.product.model.Employee;
import com.example.product.repo.DepartmentRepo;
import com.example.product.repo.EmployeeRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService
{
    private EmployeeRepo employeeRepo;
    private DepartmentRepo departmentRepo;

    public EmployeeService(EmployeeRepo employeeRepo,DepartmentRepo departmentRepo){
        this.employeeRepo=employeeRepo;
        this.departmentRepo=departmentRepo;
    }
    // Save Employee
    public ResponseEntity<ResponseDTO>saveEmployee(EmployeeDTO employeeDTO){
        if(employeeDTO!=null){
            employeeRepo.findByEmail(employeeDTO.email()).ifPresent(x->{
                throw new EmployeeAlreadyExist("User already exist with:"+x.getId());
            });
        }

        Department department=departmentRepo.findByDeptName(employeeDTO.deptName());
        if(department==null){
            throw new DepartmentNotExistException("Department not exist with deptCode:"+employeeDTO.deptCode());
        }

        Employee employee=new Employee();
        employee.setEmployeeName(employeeDTO.employeeName());
        employee.setEmail(employeeDTO.email());
        employee.setSalary(employeeDTO.salary());
        employee.setDeptName(department);

        employeeRepo.save(employee);
        ResponseDTO responseDTO=new ResponseDTO(true,"User details stored successfully",employee);
        return ResponseEntity.ok(responseDTO);
    }

    //fetch employee by empId
    public ResponseEntity<ResponseDTO>fetchEmployee(Long empId){
         Employee employee=employeeRepo.findById(empId).orElseThrow(()-> new EmployeeNotExistError("User not exists in DB:"+empId));
         ResponseDTO responseDTO=new ResponseDTO(true,"",employee);
         return ResponseEntity.ok(responseDTO);
    }

    //find all by employees
    public ResponseEntity<ResponseDTO>findAllEmployee(){
        List<Employee> employeeList=employeeRepo.findAll();
        ResponseDTO responseDTO=new ResponseDTO(true,"",employeeList);
        return ResponseEntity.ok(responseDTO);
    }

    //do the pagination
    public Page<Employee> getEmployees(int page,int size){
        Pageable pageable= PageRequest.of(page,size);
        return employeeRepo.findAll(pageable);
    }

    public ResponseEntity<ResponseDTO>removeEmployeeById(Long empId){
        Employee employee=employeeRepo.findById(empId).orElseThrow(()-> new EmployeeNotExistError("User not exists in DB:"+empId));
        employeeRepo.delete(employee);
        ResponseDTO responseDTO=new ResponseDTO(true,"",employee);
        return ResponseEntity.ok(responseDTO);
    }



}
