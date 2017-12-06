package Util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
            XSSFSheet sheet = book.getSheetAt(0);
            int rowNum =sheet.getLastRowNum();
            System.out.println(rowNum+"rowNum是这么多");
            for(int i=0;i<=rowNum;i++){
                XSSFRow row= sheet.getRow(i);
                int cellNum = row.getLastCellNum();
                List<String> rowContent = new ArrayList<>();
                for(int j =0;j<cellNum;j++){
                    XSSFCell cell= row.getCell(j);
                    rowContent.add(getCellValue(cell));
                    System.out.println(getCellValue(cell));
                }
                data.add(rowContent);
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
}