package com.heikkiv.shorthand.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heikkiv.shorthand.repositories.UrlRepository;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

	private UrlRepository urlRepository;
	private HashService hashService;
	
	@Autowired
	public UrlShortenerServiceImpl(UrlRepository urlRepository, HashService hashService) {
		this.urlRepository = urlRepository;
		this.hashService = hashService;
	}

	@Override
	public String shorten(String url) {
		String hash = urlRepository.findHashByLongUrl(url);
		if(hash.equals("")) {
			int key = urlRepository.nextKey();
			hash = hashService.createHash(key);
			urlRepository.saveUrl(key, url, hash);
		}
		return hash;
	}

	@Override
	public String unShorten(String hash) {
		return urlRepository.findUrlByHash(hash);
	}

}
