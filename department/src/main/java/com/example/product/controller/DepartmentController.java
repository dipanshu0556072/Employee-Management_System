package com.example.product.controller;
import com.example.product.dto.DepartmentDTO;
import com.example.product.dto.ResponseDTO;
import com.example.product.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/department")
public class DepartmentController
{

  private final DepartmentService departmentService;

  public DepartmentController(DepartmentService departmentService){
      this.departmentService=departmentService;
  }

  //store the dept in DB
    @PostMapping("/storeDept")
    public ResponseEntity<ResponseDTO>storeDept(@RequestBody(required = true)DepartmentDTO departmentDTO)
    {
        return departmentService.saveDepartment(departmentDTO);
    }
}
