package com.heikkiv.shorthand.services;

import static org.junit.Assert.*;

import org.junit.Test;

import com.heikkiv.shorthand.repositories.mongodb.UrlRepositoryMongoDb;
import com.heikkiv.shorthand.services.simple.SimpleHashService;

public class UrlShortenerServiceImplTest {
	
	@Test
	public void shortenUrl() {
		HashService hashService = new SimpleHashService();
		UrlRepositoryMongoDb urlRepository = new UrlRepositoryMongoDb();
		urlRepository.init();
		UrlShortenerServiceImpl urlService = new UrlShortenerServiceImpl(urlRepository, hashService);
		
		String hash = urlService.shorten("http://www.test.com");
		System.out.println(hash);
		
		assertTrue(hash.length() > 0);
	}

}
