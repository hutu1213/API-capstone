package project.apicapstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.entity.Employee;
import project.apicapstone.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    @GetMapping
    public Object findAllEmployee() {
        List<Employee> employees = employeeService.findAll();
        return ResponseHandler.getResponse(employees, HttpStatus.OK);
    }

    @GetMapping("/{employee-name}")
    public Object searchEmployeeName(@PathVariable("employee-name") String employeeName) {
        List<Employee> employeesToFind =  employeeService.findEmployeeByName(employeeName);

        return ResponseHandler.getResponse(employeesToFind,HttpStatus.OK);
    }

    @PostMapping
    public Object addEmployee(@Valid @RequestBody CreateEmployeeDto dto, BindingResult errors) {

        if (errors.hasErrors()) {

            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }

        Employee addedEmployee = employeeService.addNewEmployee(dto);


        return ResponseHandler.getResponse(addedEmployee, HttpStatus.CREATED);
    }
}
