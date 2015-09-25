package org.simple;

import org.simple.geo.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class SimpleController {

	@Autowired
	private CountryMapper countryMapper;

	@RequestMapping("heatmap")
	public String greet(@RequestParam(value="userName") String name, Model model){
		model.addAttribute("name",name);
		model.addAttribute("countriesMap",countryMapper.getMap().values());
		return "heatmap";
	}
	
}
