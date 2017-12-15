package util;

import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by nick on 2017/12/6.
 */
public class ExcelImportUtil {
    public static List<List<String>> parseExcel(InputStream fis) {
        List<List<String>> data = new ArrayList<>();
        try {
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFCellStyle cellStyle = book.createCellStyle();
            XSSFDataFormat format = book.createDataFormat();
            cellStyle.setDataFormat(format.getFormat("@"));
            XSSFSheet sheet = book.getSheetAt(0);
            int rowNum =sheet.getPhysicalNumberOfRows();
            for(int i=0;i<rowNum;i++){
                XSSFRow row= sheet.getRow(i);
                if (!isRowEmpty(row)) {
                    int cellNum = row.getLastCellNum();
                    List<String> rowContent = new ArrayList<>();
                    for(int j =0;j<cellNum;j++){
                        XSSFCell cell= row.getCell(j);
                        cell.setCellStyle(cellStyle);
                        rowContent.add(getCellValue(cell));
                    }
                    data.add(rowContent);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getCellValue(XSSFCell cell) {
        String value = "";
        // 以下是判断数据的类型
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_NUMERIC:
                value = cell.getNumericCellValue() + "";
                break;
            case XSSFCell.CELL_TYPE_STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                value = cell.getBooleanCellValue() + "";
                break;
            case XSSFCell.CELL_TYPE_FORMULA: // 公式
                value = cell.getCellFormula() + "";
                break;
            case XSSFCell.CELL_TYPE_BLANK: // 空值
                value = "";
                break;
            case XSSFCell.CELL_TYPE_ERROR: // 故障
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;
        }
        return value;
    }

    private static boolean isRowEmpty(XSSFRow row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            XSSFCell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)
                return false;
        }
        return true;
    }
}