package overtime.zhanglw.www.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

import overtime.zhanglw.www.bean.Employee;
import overtime.zhanglw.www.bean.OverTimeBean;

public class ExcelUtil {

	private static String excelName = "";

	private static String excelApplyName = "";

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static String createExcel(String json) {

		String url = "http://localhost:8080/";
		ObjectMapper mapper = new ObjectMapper();
		try {
			Employee employee = mapper.readValue(json, Employee.class);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
			excelName = "加班确认" + sdf.format(new Date()) + employee.getName() + ".xlsx";
			excelApplyName = "加班申请" + sdf.format(new Date()) + employee.getName() + ".xlsx";
			createApplyExcel("excel/apply.xlsx", employee);
			writeExcel("excel/model.xlsx", employee);
			url += excelName;
			url = url + "$" + "http://localhost:8080/" + excelApplyName;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return url;
	}

	public static void writeExcel(String model_url, Employee employee) {

		try {
			ClassPathResource file = new ClassPathResource(model_url);
			Workbook wb = null;
			String fileType = model_url.substring(model_url.length() - 4).replace(".", "");
			if (fileType.equals("xls")) {
				wb = new HSSFWorkbook(file.getInputStream());
			} else if (fileType.equals("xlsx")) {
				wb = new XSSFWorkbook(file.getInputStream());
			}

			Font font = wb.createFont();
			font.setFontName("微软雅黑");
			font.setFontHeightInPoints((short) 10);

			CellStyle dateStyle = wb.createCellStyle();
			DataFormat dateFormat = wb.createDataFormat();
			dateStyle.setDataFormat(dateFormat.getFormat("yyyy-MM-dd"));
			dateStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
			dateStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
			dateStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
			dateStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
			dateStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			dateStyle.setFont(font);

			CellStyle commonStyle = wb.createCellStyle();
			commonStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
			commonStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
			commonStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
			commonStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
			commonStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			commonStyle.setFont(font);

			CellStyle numStyle = wb.createCellStyle();
			DataFormat numformat = wb.createDataFormat();
			numStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
			numStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
			numStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
			numStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
			numStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			numStyle.setFont(font);
			numStyle.setDataFormat(numformat.getFormat("0.0"));

			Sheet sheet1 = wb.getSheetAt(0);
			for (int i = 0; i < employee.getOvetTimeList().size(); i++) {
				sheet1.shiftRows(4 + i, sheet1.getLastRowNum(), 1);
				sheet1.createRow(4 + i);
				OverTimeBean bean = employee.getOvetTimeList().get(i);

				Row row = sheet1.getRow(4 + i);
				row.createCell(0);
				row.getCell(0).setCellValue(employee.getEmptyId());
				row.getCell(0).setCellStyle(commonStyle);

				row.createCell(1);
				row.getCell(1).setCellValue(employee.getName());
				row.getCell(1).setCellStyle(commonStyle);

				row.createCell(2);
				row.getCell(2).setCellValue(employee.getPosition());
				row.getCell(2).setCellStyle(commonStyle);

				row.createCell(3);
				row.getCell(3).setCellStyle(dateStyle);
				row.getCell(3).setCellValue(sdf.parse(bean.getDate()));

				row.createCell(4);
				row.getCell(4).setCellValue(bean.getFlag() == 0 ? Constant.WORKDAY_TYPE : Constant.OFF_DAY);
				row.getCell(4).setCellStyle(commonStyle);

				row.createCell(5);
				row.getCell(5).setCellValue(bean.getStart());
				row.getCell(5).setCellStyle(commonStyle);

				row.createCell(6);
				row.getCell(6).setCellValue(bean.getEnd());
				row.getCell(6).setCellStyle(commonStyle);

				row.createCell(7);
				row.getCell(7).setCellValue(bean.getDuration());
				row.getCell(7).setCellStyle(numStyle);

				row.createCell(8);
				row.getCell(8).setCellValue(bean.getRemark());
				row.getCell(8).setCellStyle(commonStyle);

				row.createCell(9);
				row.getCell(9).setCellValue(Constant.TREATMENT);
				row.getCell(9).setCellStyle(commonStyle);
			}
			sheet1.autoSizeColumn((short) 1);
			sheet1.autoSizeColumn((short) 2);
			int num = 4 + employee.getOvetTimeList().size();
			Row sumrow = sheet1.getRow(num);
			sumrow.createCell(7);
			sumrow.getCell(7).setCellFormula("SUM(H5:" + "H" + num + ")");
			sumrow.getCell(7).setCellStyle(numStyle);

			ClassPathResource file1 = new ClassPathResource("static/overtime.js");
			FileOutputStream fOut = new FileOutputStream(file1.getFile().getParent() + File.separator + excelName);
			wb.write(fOut);
			wb.close();
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createApplyExcel(String apply_url, Employee employee) {
		try {
			ClassPathResource applyfile = new ClassPathResource(apply_url);
			Workbook wb = null;
			String ftype = apply_url.substring(apply_url.length() - 4).replace(".", "");
			if (ftype.equals("xls")) {
				wb = new HSSFWorkbook(applyfile.getInputStream());
			} else if (ftype.equals("xlsx")) {
				wb = new XSSFWorkbook(applyfile.getInputStream());
			}

			Font font = wb.createFont();
			font.setFontName("微软雅黑");
			font.setFontHeightInPoints((short) 10);

			CellStyle dateStyle = wb.createCellStyle();
			DataFormat dateFormat = wb.createDataFormat();
			dateStyle.setDataFormat(dateFormat.getFormat("yyyy-MM-dd"));
			dateStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
			dateStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
			dateStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
			dateStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
			dateStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			dateStyle.setFont(font);

			CellStyle commonStyle = wb.createCellStyle();
			commonStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
			commonStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
			commonStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
			commonStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
			commonStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			commonStyle.setFont(font);

			CellStyle numStyle = wb.createCellStyle();
			DataFormat numformat = wb.createDataFormat();
			numStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
			numStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
			numStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
			numStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
			numStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			numStyle.setFont(font);
			numStyle.setDataFormat(numformat.getFormat("0.0"));

			Sheet sheet1 = wb.getSheetAt(0);
			for (int i = 0; i < employee.getOvetTimeList().size(); i++) {
				sheet1.shiftRows(4 + i, sheet1.getLastRowNum(), 1);
				sheet1.createRow(4 + i);
				OverTimeBean bean = employee.getOvetTimeList().get(i);

				Row applyRow = sheet1.getRow(4 + i);
				applyRow.createCell(0);
				applyRow.getCell(0).setCellValue(employee.getEmptyId());
				applyRow.getCell(0).setCellStyle(commonStyle);

				applyRow.createCell(1);
				applyRow.getCell(1).setCellValue(employee.getName());
				applyRow.getCell(1).setCellStyle(commonStyle);

				applyRow.createCell(2);
				applyRow.getCell(2).setCellValue(employee.getPosition());
				applyRow.getCell(2).setCellStyle(commonStyle);

				applyRow.createCell(3);
				applyRow.getCell(3).setCellValue(sdf.parse(bean.getDate()));
				applyRow.getCell(3).setCellStyle(dateStyle);

				applyRow.createCell(4);
				applyRow.getCell(4).setCellValue(bean.getFlag() == 0 ? Constant.WORKDAY_TYPE : Constant.OFF_DAY);
				applyRow.getCell(4).setCellStyle(commonStyle);

				applyRow.createCell(5);
				applyRow.getCell(5).setCellValue(bean.getPeriod());
				applyRow.getCell(5).setCellStyle(commonStyle);

				applyRow.createCell(6);
				applyRow.getCell(6).setCellValue(bean.getRemark());
				applyRow.getCell(6).setCellStyle(commonStyle);
			}
			sheet1.autoSizeColumn((short) 1);
			sheet1.autoSizeColumn((short) 2);
			ClassPathResource file1 = new ClassPathResource("static/overtime.js");
			FileOutputStream fOut = new FileOutputStream(file1.getFile().getParent() + File.separator + excelApplyName);
			wb.write(fOut);
			wb.close();
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
