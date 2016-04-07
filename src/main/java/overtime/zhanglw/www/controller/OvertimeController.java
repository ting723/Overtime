package overtime.zhanglw.www.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("overtime")
public class OvertimeController {
	@RequestMapping("test")
	@ResponseBody
	public Map<String, String> generateExcel(String json) {
		Map<String,String> map = new HashMap<>();
		map.put("result", json);
		
		return map;
	}
}
