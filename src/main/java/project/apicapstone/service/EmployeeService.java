package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.dto.employee.PagingFormatEmployeeDto;
import project.apicapstone.dto.employee.UpdateEmployeeDto;
import project.apicapstone.entity.Employee;


import java.util.List;


public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);

    PagingFormatEmployeeDto pagingFormat(Page<Employee> employeePage);

    List<Employee> findEmployeeByName(String employeeName);

    Employee addNewEmployee(CreateEmployeeDto dto);

    List<Employee> findEmployeeByNameOrId(String paramSearch);

    void initEmployeeAdmin();

    boolean isExisted(String s);

    List<Employee> findAllEmployee();

    void deleteById(String id);

    Employee findEmployeeById(String id);

    void updateEmployee(UpdateEmployeeDto dto, String id);

    int countByWeek();

    //int countByMonth();

    int countByYear();

    int countAll();

    int countByStatus(String status);

    int[] countByMonth();

    int[] countByMonthWithStatus(String status);

    int countByArea(String area);

    boolean isExistId(String toString);

    Page<Employee> search(String paramSearch, Pageable pageable);

    List<Employee> getByCourseId(String id);

    boolean findByCourseIdAndEmployeeId(String courseId, String employeeId);

    boolean findByTaskIdAndEmployeeId(Long taskId, String employeeId);

    List<Employee> getByTaskId(Long id);

    List<Employee> checkBirthDate(int dayOfMonth, int monthValue);

    boolean getEmployeeByRequestAndStatus(String s, String status);
}
