package org.simple;

import javax.validation.Valid;

import org.simple.geo.GeoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller()
public class TwitterGeoController {

	@Autowired
	private TwitterGeoService twitterGeoService;

	@RequestMapping(value="/search.do", method = {RequestMethod.POST})
	public String heatmapPost(@Valid @ModelAttribute("searchForm") SearchForm searchForm, BindingResult result, Model model){
		if(result.hasErrors()){
			model.addAttribute("message","search.form.validation");
			return "heatmap";
		}

		GeoData geoData = twitterGeoService.readExactLocations(searchForm.getSearchTerm());
		//GeoData geoData = twitterGeoService.readLocations(query);
		model.addAttribute("message","search.result");
		model.addAttribute("searchForm",searchForm);
		model.addAttribute("locations",geoData.getLocations().toArray());
		model.addAttribute("geoLocationProvided",geoData.getLocationProvided());
		model.addAttribute("geoHits",geoData.getHits());
		model.addAttribute("geoCount",geoData.getCount());
		return "heatmap";
	}
	
	@RequestMapping(value="/", method = {RequestMethod.GET})
	public String heatmapGet(Model model){
		model.addAttribute("searchForm", new SearchForm());
		return "heatmap";
	}
	@ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingParameter() {
        return "Your custom result";
    }
}
