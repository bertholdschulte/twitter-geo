package org.simple;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.simple.geo.GeoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller()
public class TwitterGeoController {

	@Autowired
	private TwitterGeoService twitterGeoService;

	@RequestMapping(value = "/search.do", method = { RequestMethod.POST })
	public String heatmapPost(@Valid @ModelAttribute("searchForm") SearchForm searchForm, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("message", "search.form.validation");
			return "heatmap";
		}
		GeoData geoData = twitterGeoService.readExactLocations(searchForm.getSearchTerm(), searchForm.getConsumerKey(),
				searchForm.getConsumerSecret());
		model.addAttribute("message", "search.result");
		model.addAttribute("searchForm", searchForm);
		model.addAttribute("locations", geoData.getLocations().toArray());
		model.addAttribute("geoLocationProvided", geoData.getLocationProvided());
		model.addAttribute("geoHits", geoData.getHits());
		model.addAttribute("geoCount", geoData.getCount());
		return "heatmap";
	}

	@RequestMapping(value = "/", method = { RequestMethod.GET })
	public String heatmapGet(Model model, HttpServletResponse response) {
		model.addAttribute("message", "search.result");

		model.addAttribute("searchForm", new SearchForm());
		return "heatmap";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login(Model model, HttpServletResponse response) {
		model.addAttribute("message", "search.result");

		model.addAttribute("searchForm", new SearchForm());

		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new TwitterConnectionFactory("consumerKey", "consumerSecret"));

		TwitterConnectionFactory connectionFactory = new TwitterConnectionFactory("consumerKey", "consumerSecret");
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuthToken requestToken = oauthOperations.fetchRequestToken("https://localhost:8080/auth", null);
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(requestToken.getValue(), OAuth1Parameters.NONE);
		try {
			response.sendRedirect(authorizeUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "heatmap";
	}

	@RequestMapping(value = "/auth", method = { RequestMethod.GET })
	public String auth(Model model, HttpServletResponse response, @RequestParam String oauthVerifier) {
		model.addAttribute("message", "search.result");

		model.addAttribute("searchForm", new SearchForm());

		// upon receiving the callback from the provider:
		TwitterConnectionFactory connectionFactory = new TwitterConnectionFactory("consumerKey", "consumerSecret");
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuthToken requestToken = oauthOperations.fetchRequestToken("https://localhost:8080/auth", null);

		OAuthToken accessToken = oauthOperations
				.exchangeForAccessToken(new AuthorizedRequestToken(requestToken, oauthVerifier), null);
		Connection<Twitter> connection = connectionFactory.createConnection(accessToken);
		return "heatmap";
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMissingParameter() {
		return "Your custom result";
	}
}
