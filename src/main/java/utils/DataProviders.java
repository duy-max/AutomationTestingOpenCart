package utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    @DataProvider(name = "LoginData")
    public static Object[][] getLoginData() throws IOException {
        ExcelUtils excelUtils = new ExcelUtils(System.getProperty("user.dir") + "\\testData\\OpenCartData.xlsx");
        String sheetName = "LoginData";

        int totalRows = excelUtils.getRowCount(sheetName);
        int totalColumns = excelUtils.getColumnCount(sheetName);

        Object[][] data = new Object[totalRows - 1][totalColumns]; // Bỏ qua dòng tiêu đề

        for (int i = 1; i < totalRows; i++) { // Bắt đầu từ dòng thứ 2 (index 1)
            for (int j = 0; j < totalColumns; j++) {
                String columnName = excelUtils.getCellData(sheetName, 0, j);
                data[i - 1][j] = excelUtils.getCellData(sheetName, columnName, i + 1);
                System.out.print("---"+data[i - 1][j] + "---");
            }
        }

        excelUtils.close();
        return data;
    }

    @DataProvider(name = "Passwords")
    public static Object[][] getPasswordData() throws IOException {
        ExcelUtils excelUtils = new ExcelUtils(System.getProperty("user.dir") + "\\testData\\OpenCartData.xlsx");
        String sheetName = "PasswordsData";

        int totalRows = excelUtils.getRowCount(sheetName);
        int totalColumns = excelUtils.getColumnCount(sheetName);

        Object[][] data = new Object[totalRows - 1][totalColumns]; // Bỏ qua dòng tiêu đề

        for (int i = 1; i < totalRows; i++) { // Bắt đầu từ dòng thứ 2 (index 1)
            for (int j = 0; j < totalColumns; j++) {
               // String columnName = excelUtils.getCellData(sheetName, 0, j);
                data[i - 1][j] = excelUtils.getCellData(sheetName, i,j);

            }
        }

        excelUtils.close();
        return data;
    }


}
