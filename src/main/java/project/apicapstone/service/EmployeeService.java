package project.apicapstone.service;

import org.springframework.stereotype.Service;
import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.entity.Employee;

import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> findAll();

    List<Employee> findEmployeeByName(String employeeName);

    Employee addNewEmployee(CreateEmployeeDto dto);


    List<Employee> findEmployeeByNameOrId(String paramSearch);
}
