package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.dto.employee.PagingFormatEmployeeDto;
import project.apicapstone.dto.employee.UpdateEmployeeDto;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Title;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.TitleRepository;
import project.apicapstone.service.EmployeeService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private TitleRepository titleRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TitleRepository titleRepository) {
        this.employeeRepository = employeeRepository;
        this.titleRepository = titleRepository;
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAllEmp(pageable);
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
        addEmployee.setEthnic(dto.getEthnic());
        addEmployee.setAcademicLevel(dto.getAcademicLevel());
        addEmployee.setMaritalStatus(dto.getMaritalStatus());
        addEmployee.setWorkingStatus(dto.getWorkingStatus());
        addEmployee.setAvatar(dto.getAvatar());
        addEmployee.setIdCardNo(dto.getIdCardNo());
        addEmployee.setPlaceIssue(dto.getPlaceIssue());
        addEmployee.setDateIssue(dto.getDateIssue());
        // addEmployee.setCreateDate(LocalDate.of(2021, 11, 20));
        addEmployee.setCreateDate(LocalDate.now());
//        LocalDate date = getEndDateFromWeek();
//        addEmployee.setEndDateOfWeek(date);
        Title title = titleRepository.getById(dto.getTitleId());
        addEmployee.setTitle(title);
        return employeeRepository.save(addEmployee);
    }


    @Override
    public List<Employee> findEmployeeByNameOrId(String paramSearch) {
        List<Employee> listSearch = employeeRepository.findEmployeesByNameOrId(paramSearch);
        return listSearch;
    }

    @Override
    public boolean isExisted(String id) {
        return employeeRepository.existsById(id);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findEmployeeById(String id) {
        return employeeRepository.getById(id);
    }

    @Override
    public void updateEmployee(UpdateEmployeeDto dto, String id) {
        Employee updateEmployee = employeeRepository.getById(id);
        updateEmployee.setEmployeeName(dto.getEmployeeName());
        updateEmployee.setDateBirth(dto.getDateBirth());
        updateEmployee.setPlaceBirth(dto.getPlaceBirth());
        updateEmployee.setPhone(dto.getPhone());
        updateEmployee.setFrontIdentityCard(dto.getFrontIdentityCard());
        updateEmployee.setBackIdentityCard(dto.getBackIdentityCard());
        updateEmployee.setGender(dto.getGender());
        updateEmployee.setAddress(dto.getAddress());
        updateEmployee.setEmail(dto.getEmail());
        updateEmployee.setNationality(dto.getNationality());
        updateEmployee.setReligion(dto.getReligion());
        updateEmployee.setEthnic(dto.getEthnic());
        updateEmployee.setAcademicLevel(dto.getAcademicLevel());
        updateEmployee.setMaritalStatus(dto.getMaritalStatus());
        updateEmployee.setWorkingStatus(dto.getWorkingStatus());
        updateEmployee.setAvatar(dto.getAvatar());
        updateEmployee.setIdCardNo(dto.getIdCardNo());
        updateEmployee.setPlaceIssue(dto.getPlaceIssue());
        updateEmployee.setDateIssue(dto.getDateIssue());
        //Title title = titleRepository.getById(dto.getTitleId());
        updateEmployee.setTitle(titleRepository.getById(dto.getTitleId()));
        employeeRepository.save(updateEmployee);
    }

    @Override
    public int countByWeek() {
        LocalDate lastDay = getEndDateOfWeek();
        LocalDate firstDay = getStartDateOfWeek();
        int count = employeeRepository.countByCreateDateBetween(firstDay, lastDay);
        return count;
    }

    //    public int get
    public LocalDate getEndDateOfWeek() {

        LocalDate date = LocalDate.now();
        LocalDate lastDay = date.with(next(DayOfWeek.SUNDAY));

        System.out.println("End date of week: " + lastDay);
        return lastDay;
    }

    public LocalDate getStartDateOfWeek() {
        LocalDate date = LocalDate.now();
        LocalDate firstDay = date.with(previousOrSame(DayOfWeek.MONDAY));
        //LocalDate date = LocalDate.of(2021, 11, 1);
        System.out.println("Start date of week: " + firstDay);
        return firstDay;
    }

    public LocalDate getEndDateOfMonth() {
        LocalDate date = LocalDate.now();
        //LocalDate date = LocalDate.of(2021, 11, 1);
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("End date of month:" + lastDay);
        return lastDay;
    }

    public LocalDate getStartDateOfMonth() {
        LocalDate date = LocalDate.now();
        //LocalDate date = LocalDate.of(2021, 11, 1);
        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("Start date of month:" + firstDay);
        return firstDay;
    }

    public LocalDate getEndDateOfYear() {
        LocalDate date = LocalDate.now();
        //LocalDate date = LocalDate.of(2021, 11, 1);
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last date of year: " + lastDay);
        return lastDay;
    }

    public LocalDate getStartDateOfYear() {
        LocalDate date = LocalDate.now();
        //LocalDate date = LocalDate.of(2021, 11, 1);
        LocalDate lastDay = date.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("First date of year: " + lastDay);
        return lastDay;
    }

    @Override
    public int countByMonth() {
        LocalDate lastDay = getEndDateOfMonth();
        LocalDate firstDay = getStartDateOfMonth();
        int count = employeeRepository.countByCreateDateBetween(firstDay, lastDay);
        return count;
    }

    @Override
    public int countByYear() {
        LocalDate lastDay = getEndDateOfYear();
        LocalDate firstDay = getStartDateOfYear();

        return employeeRepository.countByCreateDateBetween(firstDay, lastDay);
    }

    @Override
    public int countAll() {
        return employeeRepository.countAll();
    }

    @Override
    public int countByStatus(String status) {
        return employeeRepository.countEmployeeByWorkingStatus(status);
    }

    //    public Account addRole(AddRoleDto dto) {
//        Role role = roleRepository.getById(dto.getRoleId());
//        Account account = accountRepository.getById(dto.getAccountId());
//
//        account.addRole(role);
//
//        return accountRepository.save(account);
//    }
    @Override
    public PagingFormatEmployeeDto pagingFormat(Page<Employee> employeePage) {
        PagingFormatEmployeeDto dto = new PagingFormatEmployeeDto();
        dto.setPageSize(employeePage.getSize());
        dto.setTotalRecordCount(employeePage.getTotalElements());
        dto.setPageNumber(employeePage.getNumber());
        dto.setRecords(employeePage.toList());
        return dto;
    }

}
