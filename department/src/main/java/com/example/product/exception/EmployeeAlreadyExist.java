package com.example.product.exception;

public class EmployeeAlreadyExist extends RuntimeException
{
   public EmployeeAlreadyExist(String msgError){
       super(msgError);
   }
}
