package org.simple;

import org.simple.geo.GeoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class TwitterGeoController {

	@Autowired
	private TwitterGeoService twitterGeoService;

	@RequestMapping(value="/", method = {RequestMethod.POST,RequestMethod.GET})
	public String greet(@RequestParam(value="search", required=false) String query, Model model){
		if(StringUtils.isEmpty(query)){
			model.addAttribute("message","Submit a term.");
			return "heatmap";
		}
		GeoData geoData = twitterGeoService.readLocations(query);
		model.addAttribute("message",String.format("Heatmap for: %s",query));
		model.addAttribute("query",query);
		model.addAttribute("locations",geoData.getLocations().toArray());
		model.addAttribute("geoLocationProvided",geoData.getLocationProvided());
		model.addAttribute("geoHits",geoData.getHits());
		model.addAttribute("geoCount",geoData.getCount());
		return "heatmap";
	}
	
}
