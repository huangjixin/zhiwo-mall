package com.fulan.application.common;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.fulan.api.security.domain.Account;
import com.google.common.collect.Table.Cell;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/3/22 16:12
 */
public class ExcleUtil {

    //将数据导出到Excel
    public static void excelOut(ArrayList<String> arrayList,File file) {
        WritableWorkbook bWorkbook = null;
        try {
            // 创建Excel对象
            bWorkbook = Workbook.createWorkbook(file);
            // 通过Excel对象创建一个选项卡对象
            WritableSheet sheet = bWorkbook.createSheet("sheet1", 0);
            //使用循环将数据读出
            for (int i = 0; i < arrayList.size(); i++) {
                String phone=arrayList.get(i);
                Label label=new Label(0,i,String.valueOf(phone));
                sheet.addCell(label);
            }

            // 创建一个单元格对象，第一个为列，第二个为行，第三个为值
            Label label = new Label(0, 2, "test");
            // 将创建好的单元格放入选项卡中
            //sheet.addCell(label);
            // 写如目标路径
            bWorkbook.write();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                bWorkbook.close();
            } catch (WriteException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    //将Excel中的数据导入
    public static ArrayList<String> excelIn(File file){
        ArrayList<String>arrayList=new ArrayList<>();
        Workbook bWorkbook=null;
        try {
            bWorkbook=Workbook.getWorkbook(file);
            Sheet sheet=bWorkbook.getSheet(0);
            for (int i = 0; i < sheet.getRows(); i++) {
                arrayList.add(sheet.getCell(0, i).getContents());
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            bWorkbook.close();
        }
        return arrayList;
    }
    
    
    
    /**
	 * 将 单元格的值按照相应的类型转换（导入）
	 * @param field
	 * @param cellValue
	 * @return
	 */
	private static Object getCellValueToBean(Field field,String cellValue){
		Object value = null;
		try {
			if (field.getType().equals(BigDecimal.class)) {
				BigDecimal valtmp =new BigDecimal(cellValue);
				value = valtmp;
			} else if (field.getType().equals(Date.class)) {
//				if(!StringUtil.nullOrSpace(cellValue)&&cellValue.indexOf("/")>0){
//					value = DateUtil.parse(cellValue, "yyyy/MM/dd");
//				}else{
//					value = DateUtil.parse(cellValue, "yyyy-MM-dd");
//				}				
			} else if(field.getType().equals(Long.class)){
				value = (long) Double.parseDouble(cellValue);
			} else if(field.getType().equals(Double.class)){
				value = Double.parseDouble(cellValue);
			}else{
				value = cellValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return value;
		}
		return value;
		
	}
    
    /**
	 * 读取单元格数据返回Map
	 * result 1:成功,0:失败
	 * resultMsg
	 * resultBody 读取sheet的数据结果list
	 * @param sheet
	 * @param dataList
	 * @param clz
	 * @return
	 */
	@SuppressWarnings("unused")
	public static Map getList(InputStream is,String[] dataList,Class<?> clz){
		Map resultMap = new HashMap();
		//默认成功
		resultMap.put("result", "1");		
		try {
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
			List<Object> list = new ArrayList<Object>();
			for(int i=1;i<=sheet.getLastRowNum();i++) {//有1行表头
				HSSFRow row = sheet.getRow(i);
				Object obj = clz.newInstance();
				for (int j = 0; j < dataList.length; j++) {
					HSSFCell cell = row.getCell(j);
					Field field = clz.getDeclaredField(dataList[j]);
					field.setAccessible(true);
					Object value = null;
					if(cell!=null){
						if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
							value = getCellValueToBean(field,String.valueOf(cell.getNumericCellValue()));
						}else{
							value = getCellValueToBean(field,cell.getStringCellValue());
						}
					}
					field.set(obj, value);
				}
				list.add(obj);
			}
			resultMap.put("resultBody", list);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", "0");
			resultMap.put("resultMsg", "未知错误，请检查文件格式是否有误。");
		}
		return resultMap;
	}

}