package com.example.product.exception;

public class EmployeeNotExistError extends RuntimeException
{
    public EmployeeNotExistError(String msg){
        super(msg);
    }
}
