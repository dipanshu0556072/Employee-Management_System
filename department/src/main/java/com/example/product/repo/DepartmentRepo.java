package com.example.product.repo;

import com.example.product.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepo extends JpaRepository<Department,Long>
{
    @Query("SELECT d from Department d where LOWER(d.departmentName)=LOWER(:deptName)")
    Department findByDeptName(@Param("deptName") String deptName);
}
