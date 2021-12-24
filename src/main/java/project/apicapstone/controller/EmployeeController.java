package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.dto.employee.UpdateEmployeeDto;
import project.apicapstone.entity.Employee;
import project.apicapstone.service.EmployeeService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Object findAllEmployee(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeService.findAll(pageable);
        return ResponseHandler.getResponse(employeeService.pagingFormat(employeePage), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<Employee> employees = employeeService.findAllEmployee();
        return ResponseHandler.getResponse(employees, HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public Object findEmployeeById(@PathVariable("id") String id) {
        Employee employee = employeeService.findEmployeeById(id);
        return ResponseHandler.getResponse(employee, HttpStatus.OK);
    }

    //    @GetMapping("")
//    public Object findEmployee(@RequestParam String employeeName) {
//        List<Employee> employeeList = employeeService.findEmployeeByName(employeeName);
//        //return ResponseEntity.ok(employeeList);
//        return ResponseHandler.getResponse(employeeList,HttpStatus.OK);
//    }
    @GetMapping("/search/{paramSearch}")
    public Object findEmployeeByNameOrId(@PathVariable String paramSearch) {
        List<Employee> employeeList = employeeService.findEmployeeByNameOrId(paramSearch);
        if (employeeList.isEmpty()) {
            return ResponseHandler.getErrors("Not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseHandler.getResponse(employeeList, HttpStatus.OK);
    }

    @PostMapping
    public Object createEmployee(@Valid @RequestBody CreateEmployeeDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        Employee createEmployee = employeeService.addNewEmployee(dto);
        return ResponseHandler.getResponse(createEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public Object deleteEmployee(@RequestParam(name = "id") String id) {
        employeeService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @PutMapping()
    public Object updateEmployee(@Valid @RequestBody UpdateEmployeeDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        employeeService.updateEmployee(dto, dto.getEmployeeId());
        return ResponseHandler.getResponse(HttpStatus.OK);
    }


}
