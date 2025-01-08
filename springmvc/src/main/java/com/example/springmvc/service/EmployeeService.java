package com.example.springmvc.service;

import com.example.springmvc.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    void save(EmployeeDto employeeDto);

    List<EmployeeDto> displayAll();

    EmployeeDto findByID(String id);
}
