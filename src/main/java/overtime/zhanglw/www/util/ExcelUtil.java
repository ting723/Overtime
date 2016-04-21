package overtime.zhanglw.www.util;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class ExcelUtil {

	public static String createExcel(String json) {

		String url = "";

		return url;
	}

	public static void readExcel(String url) {

		FileSystemResource file = new FileSystemResource("classpath:excel/q.xlsx");
		try {
			POIFSFileSystem poifsFileSystem = new POIFSFileSystem(file.getInputStream());
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String var1 = System.getProperty("user.home");
		String var2 = System.getProperty("user.dir");
		String var3 = ExcelUtil.class.getResource("").getPath();
		String var4 = ClassLoader.getSystemResource("").getPath();
		String var5 = Resource.class.getResource("").getPath();

		System.out.println(var1 + "\n" + var2 + "\n" + var3 + "\n" + var4 + "\n" + var5 + "\n" + file.getPath());
	}

	public static void main(String[] args) {
		ExcelUtil.readExcel("");
	}

}
