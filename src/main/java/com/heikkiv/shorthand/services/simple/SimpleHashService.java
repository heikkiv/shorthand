package com.heikkiv.shorthand.services.simple;

import org.springframework.stereotype.Service;

import com.heikkiv.shorthand.services.HashService;

@Service
public class SimpleHashService implements HashService {

	private static final String ALBHABET = "0123456789abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ";
	
	@Override
	public String createHash(int seed) {
		String key = (seed == 0) ? "0" : "";
        int mod = 0;

        while( seed != 0 ) {
            mod = seed % ALBHABET.length();
            key = ALBHABET.substring( mod, mod + 1 ) + key;
            seed = seed / ALBHABET.length();
        }

        return key;
	}

}
