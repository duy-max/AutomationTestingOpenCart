package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private Workbook workbook;

    public ExcelUtils(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fileInputStream);
    }
    public String getCellData(String sheetName, int rowIndex, int columnIndex) {
        try {
            // Lấy sheet theo tên
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet " + sheetName + " not found.");
            }

            // Lấy dòng theo chỉ số
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                return ""; // Nếu dòng không tồn tại, trả về chuỗi rỗng
            }

            // Lấy ô theo chỉ số cột
            Cell cell = row.getCell(columnIndex);
            return getCellValueAsString(cell); // Chuyển dữ liệu ô sang String
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCellData(String sheetName, String columnName, int rowNumber) {
        try {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet " + sheetName + " not found.");
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new IllegalArgumentException("Header row is missing in sheet " + sheetName);
            }

            int columnIndex = -1;
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    columnIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (columnIndex == -1) {
                throw new IllegalArgumentException("Column " + columnName + " not found.");
            }

            Row row = sheet.getRow(rowNumber - 1);
            if (row == null) {
                return "";
            }

            Cell cell = row.getCell(columnIndex);
            return getCellValueAsString(cell);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet " + sheetName + " not found.");
        }
        return sheet.getLastRowNum() + 1; // +1 vì getLastRowNum() trả về index bắt đầu từ 0
    }

    public int getColumnCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet " + sheetName + " not found.");
        }
        Row row = sheet.getRow(0); // Dòng tiêu đề
        if (row == null) {
            return 0;
        }
        return row.getLastCellNum(); // Số cột
    }

    //chuyển các kiểu dữ liệu khác về string trong cell
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    double cellValue = cell.getNumericCellValue();
                    if (cellValue == (long) cellValue) {
                        // Trả về giá trị dưới dạng số nguyên
                        return String.valueOf((long) cellValue);
                    } else {
                        // Trả về giá trị dưới dạng số thập phân
                        return String.valueOf(cellValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    public void close() throws IOException {
        workbook.close();
    }


}