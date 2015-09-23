package org.simple;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class SimpleController {

	@RequestMapping("greet")
	public String greet(@RequestParam(value="userName") String name, Model model){
		model.addAttribute("name",name);
		return "greet";
	}
	
}
