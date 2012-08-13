package com.heikkiv.shorthand.repositories.mongodb;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class UrlRepositoryMongoDbTest {

	@Ignore
	@Test
	public void genericInsert() throws Exception {
		Mongo m = new Mongo( "127.0.0.1" , 27017 );
		DB db = m.getDB( "urls" );
		DBCollection coll = db.getCollection("urls");
		
		BasicDBObject doc = new BasicDBObject();
        doc.put("_id", "sequence");
        doc.put("nextkey", 1);
        
        coll.insert(doc);
	}
	
	@Test
	public void find() throws Exception {
		Mongo m = new Mongo( "127.0.0.1" , 27017 );
		DB db = m.getDB( "urls" );
		DBCollection coll = db.getCollection("urls");
		
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);
	}
	
	@Test
	public void nextKey() {
		UrlRepositoryMongoDb urlRepository = new UrlRepositoryMongoDb();
		urlRepository.init();
		int key = urlRepository.nextKey();
		System.out.println(key);
		assertTrue(key > 0);
	}
	
	@Test
	public void saveUrl() {
		UrlRepositoryMongoDb urlRepository = new UrlRepositoryMongoDb();
		urlRepository.init();
		urlRepository.saveUrl(1, "http://www.google.com", "1234");
	}
	
	@Test
	public void findUrl() {
		UrlRepositoryMongoDb urlRepository = new UrlRepositoryMongoDb();
		urlRepository.init();
		String url = urlRepository.findUrlByHash("1234");
		assertEquals("http://www.google.com", url);
	}
	
	@Test
	public void urlNotFound() {
		UrlRepositoryMongoDb urlRepository = new UrlRepositoryMongoDb();
		urlRepository.init();
		String url = urlRepository.findUrlByHash("xyz");
		assertEquals("", url);
	}
	
}
