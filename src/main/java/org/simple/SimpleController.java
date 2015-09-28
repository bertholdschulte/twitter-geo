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
	private TwitterGeoService twitterGeoService;

	@RequestMapping("heatmap")
	public String greet(@RequestParam(value="search") String search, Model model){
		model.addAttribute("name",search);
		model.addAttribute("locations",twitterGeoService.readLocations(search).toArray());
		return "heatmap";
	}
	
}
