package com.fulan.application.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.fulan.application.util.str.StringUtil;




public class ReadExcel {
	
/**
 * index为需要读取到的列数，从0开始
 */
public static ArrayList<String> excelIn(InputStream inputStream,Integer index) throws EncryptedDocumentException, InvalidFormatException, IOException {
        
    	ArrayList<String> arrayList=new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();
        for (Row row : sheet) {
            for (Cell cell : row) {
            	if(index!=null){
            		if(cell.getColumnIndex()>index){
                		continue;
                	}
            	}
        		
                //通过获取单元格值并应用任何数据格式（Date，0.00，1.23e9，$ 1.23等），获取单元格中显示的文本
                String text = formatter.formatCellValue(cell);
                //if(!StringUtil.isEmpty(text)){
                	 arrayList.add(text);
               // }
                 //获取值并自己格式化
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:// 字符串型
                        System.out.println(cell.getRichStringCellValue().getString());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:// 数值型
                        if (DateUtil.isCellDateFormatted(cell)) { // 如果是date类型则 ，获取该cell的date值
                            System.out.println(cell.getDateCellValue());
                        } else {// 纯数字
                        	//double numericCellValue = cell.getNumericCellValue();
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:// 布尔
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:// 公式型
                        System.out.println(cell.getCellFormula());
                        break;
                    case Cell.CELL_TYPE_BLANK:// 空值
                        System.out.println();
                        break;
                    case Cell.CELL_TYPE_ERROR: // 故障
                        System.out.println();
                        break;
                    default:
                        System.out.println();
                }
            }
        }
		return arrayList;
        
    }
    
}