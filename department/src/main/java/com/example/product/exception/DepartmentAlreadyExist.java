package com.example.product.exception;

public class DepartmentAlreadyExist  extends  RuntimeException
{
      public DepartmentAlreadyExist(String msg){
          super(msg);
      }
}
