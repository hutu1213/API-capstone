package project.apicapstone.service;

import project.apicapstone.dto.department.CreateDepartmentDto;
import project.apicapstone.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    Department addNewDepartment(CreateDepartmentDto dto);

    boolean isExisted(String s);
}
