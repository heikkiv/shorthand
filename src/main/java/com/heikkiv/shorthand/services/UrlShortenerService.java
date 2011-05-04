package com.heikkiv.shorthand.services;

public interface UrlShortenerService {

	String shorten(String url);
	String unShorten(String hash);
	
}
