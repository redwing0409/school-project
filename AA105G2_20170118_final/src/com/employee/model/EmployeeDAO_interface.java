package com.employee.model;

import java.util.*;

public interface EmployeeDAO_interface {
          public int insert(EmployeeVO employeeVO);
          public int update(EmployeeVO employeeVO);
          public int delete(String empno);
          public EmployeeVO findByPrimaryKey(String empno);
          public List<EmployeeVO> getAll();
}
