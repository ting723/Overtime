package overtime.zhanglw.www.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import overtime.zhanglw.www.util.ExcelUtil;

@RestController
public class OvertimeController {
	@RequestMapping("/getExcel")
	public String generateExcel(@RequestParam(value="json", defaultValue="^Hello,Boys And Girls!^")String json) {
		String url = ExcelUtil.createExcel(json);
		return url;
	}
}
