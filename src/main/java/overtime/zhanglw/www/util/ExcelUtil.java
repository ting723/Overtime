package overtime.zhanglw.www.util;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

public class ExcelUtil {

	public static String createExcel(String json) {

		String url = "";

		return url;
	}

	public static void readExcel(String url) {
		
		try {
			ClassPathResource file = new ClassPathResource(url);
			Workbook wb = null;
			String fileType = url.substring(url.length() - 4).replace(".", "");
			if (fileType.equals("xls")) {
				wb = new HSSFWorkbook(file.getInputStream());
			} else if (fileType.equals("xlsx")) {
				wb = new XSSFWorkbook(file.getInputStream());
			}

			Sheet sheet1 = wb.getSheetAt(0);
			for (Row row : sheet1) {
				for (Cell cell : row) {
					System.out.print(cell.getStringCellValue() + "  ");
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExcelUtil.readExcel("excel/q.xlsx");
	}

}
