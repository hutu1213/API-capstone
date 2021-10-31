package project.apicapstone.service.impl;

import com.google.common.eventbus.DeadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.department.CreateDepartmentDto;
import project.apicapstone.entity.Department;
import project.apicapstone.repository.DepartmentRepository;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department addNewDepartment(CreateDepartmentDto dto) {
        Department addDepartment = new Department();
        addDepartment.setDepartmentId(dto.getDepartmentId());
        addDepartment.setDepartmentName(dto.getDepartmentName());
        return departmentRepository.save(addDepartment);
    }

    @Override
    public boolean isExisted(String s) {
        return departmentRepository.existsById(s);
    }


}
