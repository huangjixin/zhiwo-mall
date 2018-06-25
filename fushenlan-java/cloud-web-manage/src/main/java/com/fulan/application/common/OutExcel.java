package com.fulan.application.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fulan.api.information.domain.ApplyDetail;
import com.fulan.application.util.domain.Response;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class OutExcel {


	public static void excelOut(List<ApplyDetail> list,ServletOutputStream out) throws IOException {
		try {
			 Workbook[] wbs = new Workbook[] { new XSSFWorkbook() };
			 Workbook workbook = wbs[0];
		        for (int i = 0; i < wbs.length; i++) {
		            
		            // 得到一个POI的工具类
		            CreationHelper createHelper = workbook.getCreationHelper();

		            // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
		            Sheet sheet = workbook.createSheet();
		            // Sheet sheet = workbook.createSheet("SheetName");

		            // 用于格式化单元格的数据
		            DataFormat format = workbook.createDataFormat();

		            // 设置字体
		            Font font = workbook.createFont();
		            font.setFontHeightInPoints((short) 20); // 字体高度
		            font.setColor(Font.COLOR_RED); // 字体颜色
		            font.setFontName("黑体"); // 字体
		            font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 宽度
		            font.setItalic(true); // 是否使用斜体
		            // font.setStrikeout(true); //是否使用划线

		            // 设置单元格类型
		            CellStyle cellStyle = workbook.createCellStyle();
		            cellStyle.setFont(font);
		            cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平布局：居中
		            cellStyle.setWrapText(true);

		            CellStyle cellStyle2 = workbook.createCellStyle();
		            cellStyle2.setDataFormat(format.getFormat("＃, ## 0.0"));

		            CellStyle cellStyle3 = workbook.createCellStyle();
		            cellStyle3.setDataFormat(format.getFormat("yyyy-MM-dd "));

		            // 添加单元格注释
		            // 创建Drawing对象,Drawing是所有注释的容器.
		            Drawing drawing = sheet.createDrawingPatriarch();
		            // ClientAnchor是附属在WorkSheet上的一个对象， 其固定在一个单元格的左上角和右下角.
		            ClientAnchor anchor = createHelper.createClientAnchor();
		            Row row = sheet.createRow(0);
		            Cell cell = row.createCell((short) 0);
		            cell.setCellValue(createHelper.createRichTextString("用户名"));
		            cell = row.createCell((short) 1);
		            cell.setCellValue(createHelper.createRichTextString("工号"));
		            cell = row.createCell((short) 2);
		            cell.setCellValue(createHelper.createRichTextString("职级"));
		            cell = row.createCell((short) 3);
		            cell.setCellValue(createHelper.createRichTextString("所属总公司"));
		            cell = row.createCell((short) 4);
		            cell.setCellValue(createHelper.createRichTextString("所属分公司"));
		            cell = row.createCell((short) 5);
		            cell.setCellValue(createHelper.createRichTextString("所属部门"));
		            cell = row.createCell((short) 6);
		            cell.setCellValue(createHelper.createRichTextString("报名状态"));
		            cell = row.createCell((short) 7);
		            cell.setCellValue(createHelper.createRichTextString("报名时间"));
		            // 定义几行
		            
		            for (int rownum = 0; rownum < list.size(); rownum++) {
		                // 创建行
		                 row = sheet.createRow(rownum+1);
		                
		                // 创建单元格
		                cell = row.createCell((short) 0);
		                cell.setCellValue(createHelper.createRichTextString(list.get(rownum).getAcountName()));// 设置单元格内容
		                cell = row.createCell((short) 1);
		                cell.setCellValue(createHelper.createRichTextString(list.get(rownum).getAgentCode()));// 设置单元格内容
		                cell = row.createCell((short) 2);
		                cell.setCellValue(createHelper.createRichTextString(list.get(rownum).getLevelName()));// 设置单元格内容
		                cell = row.createCell((short) 3);
		                cell.setCellValue(createHelper.createRichTextString(list.get(rownum).getCompanyName()));// 设置单元格内容
		                cell = row.createCell((short) 4);
		                cell.setCellValue(createHelper.createRichTextString(list.get(rownum).getBranchCompanyName()));// 设置单元格内容
		                cell = row.createCell((short) 5);
		                cell.setCellValue(createHelper.createRichTextString(list.get(rownum).getOrgName()));// 设置单元格内容
		                cell = row.createCell((short) 6);
		                if("0".equals(list.get(rownum).getStatus())){
		                cell.setCellValue(createHelper.createRichTextString("未报名"));// 设置单元格内容
		                }
		                if("1".equals(list.get(rownum).getStatus())){
		                    cell.setCellValue(createHelper.createRichTextString("已报名"));// 设置单元格内容
		                    }
		                cell = row.createCell((short) 7);
		                cell.setCellStyle(cellStyle3);// 设置单元格样式
		                if(null !=list.get(rownum).getApplyDate()){
		                	cell.setCellValue(list.get(rownum).getApplyDate());// 设置单元格内容
		                }
		            }
		            workbook.write(out);
		        }
		       
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(out != null){
				out.close();
			}
		}
	}
}