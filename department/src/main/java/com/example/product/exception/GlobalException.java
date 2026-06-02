package com.example.product.exception;

import com.example.product.dto.ErrorDTO;
import com.example.product.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException
{
    @ExceptionHandler(EmployeeAlreadyExist.class)
    public ResponseEntity<ErrorDTO>userAlreadyExistError(EmployeeAlreadyExist employeeAlreadyExist){
        ErrorDTO errorDTO=new ErrorDTO(409,employeeAlreadyExist.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmployeeNotExistError.class)
    public ResponseEntity<ErrorDTO>userNotExistError(EmployeeNotExistError employeeNotExistError){
        ErrorDTO errorDTO=new ErrorDTO(404,employeeNotExistError.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentAlreadyExist.class)
    public ResponseEntity<ErrorDTO>userNotExistError(DepartmentAlreadyExist departmentAlreadyExist){
        ErrorDTO errorDTO=new ErrorDTO(409,departmentAlreadyExist.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }


}
