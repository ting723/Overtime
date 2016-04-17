package overtime.zhanglw.www.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OvertimeController {
	@RequestMapping("/getExcel")
	public String generateExcel(@RequestParam(value="json", defaultValue="^Hello,Boys And Girls!^")String json) {
		System.out.println(json);
		return json;
	}
}
