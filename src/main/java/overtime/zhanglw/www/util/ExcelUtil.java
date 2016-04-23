package overtime.zhanglw.www.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import overtime.zhanglw.www.bean.Employee;
import overtime.zhanglw.www.bean.OverTimeBean;

public class ExcelUtil {

	private static String url = "http://localhost:8080/test.xlsx";

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

	public static String createExcel(String json) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			Employee employee = mapper.readValue(json, Employee.class);
			writeExcel("excel/model.xlsx", employee);
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

			CellStyle dateStyle = wb.createCellStyle();
			DataFormat dateFormat = wb.createDataFormat();
			dateStyle.setDataFormat(dateFormat.getFormat("yyyy-MM-dd"));

			Sheet sheet1 = wb.getSheetAt(0);
			for (int i = 0; i < employee.getOvetTimeList().size(); i++) {
				sheet1.shiftRows(4 + i, sheet1.getLastRowNum(), 1);
				sheet1.createRow(4 + i);
				OverTimeBean bean = employee.getOvetTimeList().get(i);
				Row row = sheet1.getRow(4+i);
				row.createCell(0);
				row.getCell(0).setCellValue(employee.getEmptyId());
				row.createCell(1);
				row.getCell(1).setCellValue(employee.getName());
				row.createCell(2);
				row.getCell(2).setCellValue("开发工程师");
				row.createCell(3);
				row.getCell(3).setCellStyle(dateStyle);
				row.getCell(3).setCellValue(sdf.parse(bean.getDate()));
				row.createCell(4);
				row.getCell(4).setCellValue(bean.getFlag() == 0 ? Constant.WORKDAY_TYPE : Constant.OFF_DAY);
				row.createCell(5);
				row.getCell(5).setCellValue(bean.getStart());
				row.createCell(6);
				row.getCell(6).setCellValue(bean.getEnd());
				row.createCell(7);
				row.getCell(7).setCellValue("1");
				row.createCell(8);
				row.getCell(8).setCellValue("加班");
				row.createCell(9);
				row.getCell(9).setCellValue(Constant.TREATMENT);
			}
			ClassPathResource file1 = new ClassPathResource("static/overtime.js");
			FileOutputStream fOut = new FileOutputStream(file1.getFile().getParent()+File.separator+"test.xlsx");
			wb.write(fOut);
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// ExcelUtil.writeExcel("excel/model.xlsx");
	// }

}
