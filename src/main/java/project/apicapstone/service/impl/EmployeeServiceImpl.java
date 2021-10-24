package project.apicapstone.service.impl;

import org.springframework.stereotype.Service;
import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.entity.Employee;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findEmployeeByName(String employeeName) {
        return employeeRepository.findEmployeesByEmployeeNameContains(employeeName);
    }

    @Override
    public Employee addNewEmployee(CreateEmployeeDto dto) {
        Employee addEmployee = new Employee();
        addEmployee.setEmployeeName(dto.getEmployeeName());
        addEmployee.setDateBirth(dto.getDateBirth());
        addEmployee.setPlaceBirth(dto.getPlaceBirth());
        addEmployee.setPhone(dto.getPhone());
        addEmployee.setIdentityCardNum(dto.getIdentityCardNum());
        addEmployee.setPlaceIdentityCard(dto.getPlaceIdentityCard());
        addEmployee.setGender(dto.getGender());
        addEmployee.setAddress(dto.getAddress());
        addEmployee.setEmail(dto.getEmail());
        addEmployee.setNationality(dto.getNationality());
        addEmployee.setReligion(dto.getReligion());
        addEmployee.setCountryOfCitizenship(dto.getCountryOfCitizenship());
        addEmployee.setAcademicLevel(dto.getAcademicLevel());
        addEmployee.setMaritalStatus(dto.getMaritalStatus());

        return employeeRepository.save(addEmployee);
    }
}
