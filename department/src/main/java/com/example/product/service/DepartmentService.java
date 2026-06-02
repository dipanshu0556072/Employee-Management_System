package com.example.product.service;

import com.example.product.dto.DepartmentDTO;
import com.example.product.dto.ResponseDTO;
import com.example.product.exception.DepartmentAlreadyExist;
import com.example.product.model.Department;
import com.example.product.repo.DepartmentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService
{
    private final DepartmentRepo departmentRepo;

    public DepartmentService(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public ResponseEntity<ResponseDTO> saveDepartment(DepartmentDTO departmentDTO) {
        if(departmentRepo.findByDeptName(departmentDTO.departmentName())!=null){
            throw new DepartmentAlreadyExist("Dept already exist with the given code:"+departmentDTO.departmentCode());
        }

        Department department=new Department();
        department.setDepartmentCode(departmentDTO.departmentCode());
        department.setDepartmentName(departmentDTO.departmentName());
        department.setDescription(departmentDTO.description());

        ResponseDTO responseDTO=new ResponseDTO(true,"dept saved successfully!",department);

        departmentRepo.save(department);
        return ResponseEntity.ok(responseDTO);
    }

    public Department getDepartment(Long id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    //fetch the department by deptName
    public Department fetchDept(String deptName){
        return departmentRepo.findByDeptName(deptName);
    }

}
