package project.apicapstone.controller;


import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import project.apicapstone.common.util.DateUtils;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Title;
import project.apicapstone.entity.Workplace;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.TitleRepository;
import project.apicapstone.repository.WorkplaceRepository;
import project.apicapstone.service.EmployeeService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    private EmployeeService employeeService;
    private TitleRepository titleRepository;
    private WorkplaceRepository workplaceRepository;
    private EmployeeRepository employeeRepository;

    public ExcelController(EmployeeService employeeService, TitleRepository titleRepository, WorkplaceRepository workplaceRepository, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.titleRepository = titleRepository;
        this.workplaceRepository = workplaceRepository;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public Object importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {

        List<Employee> employeeList = new ArrayList<>();
        List<Employee> saveEmployee = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Employee employee = new Employee();

                XSSFRow row = worksheet.getRow(index);

                Integer id = (int) row.getCell(0).getNumericCellValue();
                System.out.println("id: " + id);
                //String id = row.getCell(0).getStringCellValue();
                if (employeeService.isExistId(id.toString())) {
                    return ResponseHandler.getErrors("Mã nhân viên " + id.toString() + " bị trùng.", HttpStatus.BAD_REQUEST);
                } else {
                    employee.setEmployeeId(id.toString());
                }
                employee.setEmployeeName(row.getCell(1).getStringCellValue());

                //Date date = row.getCell(2).getDateCellValue();
                //LocalDate birthDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtils.DATE_FORMAT);

                LocalDate birthDate = LocalDate.parse((String) row.getCell(2).getStringCellValue(), formatter);

                employee.setDateBirth(birthDate);

                employee.setDayOfBirth(birthDate.getDayOfMonth());
                System.out.println("day of month: " + birthDate.getDayOfMonth());
                employee.setMonthOfBirth(birthDate.getMonthValue());
                System.out.println("month : " + birthDate.getMonthValue());
                employee.setPlaceBirth(row.getCell(3).getStringCellValue());
//
                Integer phone = (int) row.getCell(4).getNumericCellValue();
                System.out.println("phone: " + phone);
                //if (phone.toString().matches("[0-9]{1,12}")) {
                employee.setPhone(phone.toString());
//                } else {
//                    return ResponseHandler.getErrors("Số điện thoại từ 1 đến 12 chữ số", HttpStatus.BAD_REQUEST);
//                }
                employee.setGender(row.getCell(5).getStringCellValue());

                employee.setAddress(row.getCell(6).getStringCellValue());

                employee.setEmail(row.getCell(7).getStringCellValue());

                employee.setNationality(row.getCell(8).getStringCellValue());

                employee.setReligion(row.getCell(9).getStringCellValue());

                employee.setEthnic(row.getCell(10).getStringCellValue());

                employee.setAcademicLevel(row.getCell(11).getStringCellValue());

                Integer bankAccountNo = (int) row.getCell(12).getNumericCellValue();
                System.out.println("bankAccount no: " + bankAccountNo);
                employee.setBankAccountNo(bankAccountNo.toString());

                employee.setBank(row.getCell(13).getStringCellValue());

                Integer taxIdentificationNo = (int) row.getCell(14).getNumericCellValue();
                System.out.println("taxIdentificationNo : " + taxIdentificationNo);
                employee.setTaxIdentificationNo(taxIdentificationNo.toString());

                employee.setMaritalStatus(row.getCell(15).getStringCellValue());

                employee.setWorkingStatus(row.getCell(16).getStringCellValue());

                employee.setAvatar(row.getCell(17).getStringCellValue());

                employee.setCreateDate(LocalDate.now());

                employee.setUpdateDate(LocalDate.now());

                employee.setBackIdentityCard(row.getCell(18).getStringCellValue());

                employee.setFrontIdentityCard(row.getCell(19).getStringCellValue());

                if (titleRepository.existsById(row.getCell(20).getStringCellValue())) {
                    employee.setTitle(titleRepository.getById(row.getCell(20).getStringCellValue()));
                } else {
                    return ResponseHandler.getErrors("Không tìm thấy mã chức vụ", HttpStatus.BAD_REQUEST);
                }
                if (workplaceRepository.existsById(row.getCell(21).getStringCellValue())) {
                    employee.setWorkplace(workplaceRepository.getById(row.getCell(21).getStringCellValue()));
                } else {
                    return ResponseHandler.getErrors("Không tìm thấy mã nơi làm việc", HttpStatus.BAD_REQUEST);
                }

                employeeList.add(employee);
            }
        }
        for (Employee data : employeeList) {
            Employee employee = employeeRepository.save(data);
            saveEmployee.add(employee);
        }
        return ResponseHandler.getResponse(saveEmployee, HttpStatus.OK);
    }
}
