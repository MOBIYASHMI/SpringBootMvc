package com.example.springmvc.service.impl;

import com.example.springmvc.dto.EmployeeDto;
import com.example.springmvc.entity.Employee;
import com.example.springmvc.repository.EmployeeRepository;
import com.example.springmvc.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(EmployeeDto employeeDto) {
        Employee employee=modelMapper.map(employeeDto,Employee.class);
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDto> displayAll() {
        List<Employee> employees=employeeRepository.findAll();
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findByID(String id) {
        Employee employee=employeeRepository.findById(id).get();
        return modelMapper.map(employee,EmployeeDto.class);
    }
}
