package com.example.product.repo;

import com.example.product.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee,Long>
{
  Page<Employee>findAll(Pageable pageable);

  @Query("Select e from Employee e where LOWER(e.email)=LOWER(:emailId)")
  Optional<Employee> findByEmail(@Param("emailId") String emailId);
}
