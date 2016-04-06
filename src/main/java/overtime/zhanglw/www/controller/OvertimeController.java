package overtime.zhanglw.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/overtime")
public class OvertimeController {
	@RequestMapping(value = "/generateExcel", method = { RequestMethod.GET,RequestMethod.POST})
	public void generateExcel() {
		System.out.println("Hello World!");
	}
}
