package com.heikkiv.shorthand.repositories;

public interface UrlRepository {

	int nextKey();

	void saveUrl(int key, String url, String hash);
	
	String findUrlByHash(String hash);
	
	String findHashByLongUrl(String longUrl);

}
