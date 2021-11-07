package project.apicapstone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.apicapstone.common.util.ResourceNotFoundException;
import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.entity.Employee;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findEmployeeByName(String employeeName) {

        List<Employee> employeeList = employeeRepository.findEmployeesByEmployeeNameContains(employeeName);
        if (employeeList.size() == 0) {
            //throw new ResourceNotFoundException("");
            throw new IllegalStateException("Name not exists!");
        }
        return employeeList;
    }

    @Override
    public Employee addNewEmployee(CreateEmployeeDto dto) {
        Employee addEmployee = new Employee();
        addEmployee.setEmployeeId(dto.getEmployeeId());
        addEmployee.setEmployeeName(dto.getEmployeeName());
        addEmployee.setDateBirth(dto.getDateBirth());
        addEmployee.setPlaceBirth(dto.getPlaceBirth());
        addEmployee.setPhone(dto.getPhone());
        addEmployee.setFrontIdentityCard(dto.getFrontIdentityCard());
        addEmployee.setBackIdentityCard(dto.getBackIdentityCard());
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


    @Override
    public List<Employee> findEmployeeByNameOrId(String paramSearch) {
//        List<Employee> employeeList = employeeRepository.findEmployeesByEmployeeNameContains(paramSearch);
//        if (employeeList.size() == 0) {
//            //throw new ResourceNotFoundException("");
//            throw new IllegalStateException("Name not exists!");
//        }
//        List<Employee> employee = employeeRepository.findEmployeeByIdEmployeeContaining(paramSearch);
//        if (employee==null) {
//            //throw new ResourceNotFoundException("");
//            throw new IllegalStateException("Id not exists!");
//
//        }
        List<Employee> listSearch = employeeRepository.findEmployeesByNameOrId(paramSearch);
        if (listSearch.size() == 0) {
            throw new IllegalStateException("Not found !");
        }
        return listSearch;
    }

    @Override
    public boolean isExisted(String id) {
        return employeeRepository.existsById(id);
    }


//    delete(Long id){
//        boolean exists=employeeRepository.existsById(id);
//        if(!exists){
//            throw new IllegalStateException("Id not exists!");
//        }
//        return employeeRepository.deleteById(id);
//    }
}
