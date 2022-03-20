package project.apicapstone.common.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import project.apicapstone.entity.Employee;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExporterExcelEmployee {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Employee> listEmployee;
    private Employee employee;
    private int i = 1;
    public ExporterExcelEmployee(Employee employee) {
        this.employee = employee;
    }

    public ExporterExcelEmployee(List<Employee> listEmployee) {
        this.listEmployee = listEmployee;
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeHeaderRow() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Hồ Sơ NV");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        createCell(row, 0, "Số TT", style);
        createCell(row, 1, "Số HĐ", style);
        createCell(row, 2, "HỌ VÀ TÊN", style);
        createCell(row, 3, "ĐIỆN THOẠI", style);
        createCell(row, 4, "EMAIL", style);
        createCell(row, 5, "NGÀY NHẬN VIỆC", style);
        createCell(row, 6, "NGÀY SINH", style);
        createCell(row, 7, "NƠI SINH", style);
        createCell(row, 8, "GIỚI TÍNH", style);
        createCell(row, 9, "DÂN TỘC", style);
        createCell(row, 10, "TÔN GIÁO", style);
        createCell(row, 11, "TRÌNH ĐỘ HỌC VẤN", style);
        createCell(row, 12, "CMND SỐ", style);
        createCell(row, 13, "NGÀY CẤP", style);
        createCell(row, 14, "NƠI CẤP", style);
        createCell(row, 15, "ĐỊA CHỈ THƯỜNG TRÚ ", style);
        createCell(row, 16, "CHỨC DANH", style);
        createCell(row, 17, "LOẠI HỢP ĐỒNG", style);
        createCell(row, 18, "NGÀY KÝ ", style);
        createCell(row, 19, "THÁNG KÝ", style);
        createCell(row, 20, "NĂM KÝ", style);
        createCell(row, 21, "NGÀY LÀM", style);
        createCell(row, 22, "THÁNG LÀM", style);
        createCell(row, 23, "NĂM LÀM", style);
        createCell(row, 24, "NGÀY KẾT THÚC", style);
        createCell(row, 25, "THÁNG KẾT THÚC", style);
        createCell(row, 26, "NĂM KẾT THÚC", style);
        createCell(row, 27, "TÌNH TRẠNG HÔN NHÂN ", style);
        createCell(row, 28, "LIÊN HỆ ", style);
    }

    private void writeDataRow() {
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setFontHeight(12);
//        style.setFont(font);
//
//        Row row = sheet.createRow(1);
//
//        createCell(row, 0, i++, style);
//        createCell(row, 1, employee.getContract().getContractId(), style); //số HĐ
//
//        createCell(row, 2, employee.getEmployeeName(), style);
//        createCell(row, 3, employee.getPhone(), style);
//        createCell(row, 4, employee.getEmail(), style);
//        createCell(row, 5, employee.getContract().getStartDate().toString(), style); //Ngày nhận việc
//        createCell(row, 6, employee.getDateBirth().toString(), style);
//        createCell(row, 7, employee.getPlaceBirth(), style);
//        createCell(row, 8, employee.getGender(), style);
//        createCell(row, 9, employee.getEthnic(), style);
//        createCell(row, 10, employee.getReligion(), style);
//        createCell(row, 11, employee.getAcademicLevel(), style);
//        createCell(row, 12, employee.getEmployeeId(), style); //Số CMND
//        createCell(row, 13, employee.getDateIssue().toString(), style); //Ngày cấp (CMND)
//        createCell(row, 14, employee.getPlaceIssue(), style); //Nơi cấp (CMND)
//        createCell(row, 15, employee.getAddress(), style);
//        createCell(row, 16, employee.getTitle().getJobTitle(), style); //Chức Danh
//        createCell(row, 17, employee.getContract().getType(), style); //Loại hợp đồng
//        createCell(row, 18, "", style); //Ngày ký
//        createCell(row, 19, "", style); //Tháng ký
//        createCell(row, 20, "", style); //Năm ký
//        createCell(row, 21, "", style); //Ngày làm
//        createCell(row, 22, "", style); //Tháng làm
//        createCell(row, 23, "", style); //Năm làm
//        createCell(row, 24, "", style); //Ngày kết thúc
//        createCell(row, 25, "", style); //Tháng kết thúc
//        createCell(row, 26, "", style); //Năm kết thúc
//        createCell(row, 27, employee.getMaritalStatus(), style);
//        createCell(row, 28, "", style); //Liên hệ
    }

    private void writeDataRowList() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        for (Employee employee : listEmployee) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
//            createCell(row, columnCount++, i++, style);
//            createCell(row, columnCount++, employee.getContract().getContractId(), style); //số HĐ
//            createCell(row, columnCount++, employee.getEmployeeName(), style);
//            createCell(row, columnCount++, employee.getPhone(), style);
//            createCell(row, columnCount++, employee.getEmail(), style);
//            createCell(row, columnCount++, employee.getContract().getStartDate().toString(), style); //Ngày nhận việc
//            createCell(row, columnCount++, employee.getDateBirth().toString(), style);
//            createCell(row, columnCount++, employee.getPlaceBirth(), style);
//            createCell(row, columnCount++, employee.getGender(), style);
//            createCell(row, columnCount++, employee.getEthnic(), style);
//            createCell(row, columnCount++, employee.getReligion(), style);
//            createCell(row, columnCount++, employee.getAcademicLevel(), style);
//            createCell(row, columnCount++, employee.getEmployeeId(), style); //Số CMND
//            createCell(row, columnCount++, employee.getDateIssue().toString(), style); //Ngày cấp (CMND)
//            createCell(row, columnCount++, employee.getPlaceIssue(), style); //Nơi cấp (CMND)
//            createCell(row, columnCount++, employee.getAddress(), style);
//            createCell(row, columnCount++, employee.getTitle().getJobTitle(), style); //Chức Danh
//            createCell(row, columnCount++, employee.getContract().getType(), style); //Loại hợp đồng
//            createCell(row, columnCount++, employee.getContract().getSignDate().getDayOfMonth(), style); //Ngày ký
//            createCell(row, columnCount++, employee.getContract().getSignDate().getMonthValue(), style); //Tháng ký
//            createCell(row, columnCount++, employee.getContract().getSignDate().getYear(), style); //Năm ký
//            createCell(row, columnCount++, employee.getContract().getStartDate().getDayOfMonth(), style); //Ngày làm
//            createCell(row, columnCount++, employee.getContract().getStartDate().getMonthValue(), style); //Tháng làm
//            createCell(row, columnCount++, employee.getContract().getStartDate().getYear(), style); //Năm làm
//            createCell(row, columnCount++, employee.getContract().getEndDate().getDayOfMonth(), style); //Ngày kết thúc
//            createCell(row, columnCount++, employee.getContract().getEndDate().getMonthValue(), style); //Tháng kết thúc
//            createCell(row, columnCount++, employee.getContract().getEndDate().getYear(), style); //Năm kết thúc
//            createCell(row, columnCount++, employee.getMaritalStatus(), style);
//            createCell(row, columnCount++, employee.getPhone(), style); //Liên hệ
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRow();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    public void exportList(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRowList();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
