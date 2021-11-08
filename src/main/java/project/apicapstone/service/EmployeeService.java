package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.dto.employee.PagingFormatEmployeeDto;
import project.apicapstone.entity.Employee;

import java.util.List;

@Service
public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);
    PagingFormatEmployeeDto pagingFormat(Page<Employee> employeePage);
    List<Employee> findEmployeeByName(String employeeName);

    Employee addNewEmployee(CreateEmployeeDto dto);


    List<Employee> findEmployeeByNameOrId(String paramSearch);

    boolean isExisted(String s);


    List<Employee> findAllEmployee();
}
