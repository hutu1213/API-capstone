package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.entity.Employee;
import project.apicapstone.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employees")

public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Object findAllEmployee(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeService.findAll(pageable);
        return ResponseHandler.getResponse(employeeService.pagingFormat(employees), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<Employee> employees = employeeService.findAllEmployee();
        return ResponseHandler.getResponse(employees, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Employee> findById(@PathVariable("id") Long id) {
//        Employee employee = employeeService.findEmployeeById(id);
//        //return ResponseHandler.getResponse(employee,HttpStatus.OK);
//        return ResponseEntity.ok(employee);
//    }
//
//    @GetMapping("")
//    public Object findEmployee(@RequestParam String employeeName) {
//        List<Employee> employeeList = employeeService.findEmployeeByName(employeeName);
//        //return ResponseEntity.ok(employeeList);
//        return ResponseHandler.getResponse(employeeList,HttpStatus.OK);
//    }

    @GetMapping("/{paramSearch}")
    public Object findEmployeeByNameOrId(@PathVariable String paramSearch) {
        List<Employee> employeeList = employeeService.findEmployeeByNameOrId(paramSearch);
        return ResponseHandler.getResponse(employeeList, HttpStatus.OK);
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
