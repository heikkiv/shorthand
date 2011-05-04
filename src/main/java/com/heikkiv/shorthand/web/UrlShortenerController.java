package com.heikkiv.shorthand.web;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.heikkiv.shorthand.services.UrlShortenerService;

@Controller
public class UrlShortenerController {

	private static final Logger log = Logger.getLogger(UrlShortenerController.class);

	private UrlShortenerService urlShortenerService;
	
	@Autowired
	public UrlShortenerController(UrlShortenerService urlShortenerService) {
		this.urlShortenerService = urlShortenerService;
	}

	@RequestMapping(value="/shorten", params="url")
	public String getSystemInfoAndEstimates(@RequestParam("url") String url, Model model, HttpServletRequest request) {
		String hash = urlShortenerService.shorten(url);
		model.addAttribute("hash", hash);
		model.addAttribute("server", getServerName(request));
		log.info("Url " + url + " shortened to " + hash);
		return "shorten";
	}
	
	private String getServerName(HttpServletRequest request) {
		String serverName = request.getServerName();
		int port = request.getLocalPort();
		if(port != 80) {
			return serverName + ":" + port;
		} else {
			return serverName;
		}
	}
	
	@RequestMapping(value="/{hash}", method = RequestMethod.GET)
	public void redirectToUrl(@PathVariable("hash") String hash, HttpServletResponse response) throws Exception {
		String url = urlShortenerService.unShorten(hash);
		if(url.length() > 0) {
			response.sendRedirect(url);
			log.info("Redirecting from " + hash + " to " + url);
		} else {
			response.sendError(404);
		}
	}
	
}
