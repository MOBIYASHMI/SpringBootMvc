package com.example.springmvc.controller;

import com.example.springmvc.dto.EmployeeDto;
import com.example.springmvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/index")
    public String viewForm(Model model){
        EmployeeDto employee=new EmployeeDto();
        model.addAttribute("employee",employee);
        return "index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") EmployeeDto employeeDto){
        employeeService.save(employeeDto);
        return "redirect:/index";
    }

    @GetMapping("/displayAll")
    public String displayAll(Model model){
        model.addAttribute("emplist",employeeService.displayAll());
        return "employee-list";
    }

    @GetMapping("/searchbyid")
    public String search(Model model){
        model.addAttribute("employeebyid",new EmployeeDto());
        return "search";
    }

    // display by ID from frontend
    @GetMapping("/displayById")
    public String employeeById(@ModelAttribute("employeebyid") EmployeeDto employeeDto,Model model){
        String id=employeeDto.getId();
        model.addAttribute("employee", employeeService.findByID(id));
        return "employee";
    }

    // display by Id with Restful url, used in PostMan
    @GetMapping("/displayById/{id}")
    public ResponseEntity<EmployeeDto> employeeById(@PathVariable("id") String id){
        EmployeeDto employeeDto=employeeService.findByID(id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
