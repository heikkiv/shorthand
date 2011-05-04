package com.heikkiv.shorthand.repositories.mongodb;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.heikkiv.shorthand.repositories.UrlRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

@Repository
public class UrlRepositoryMongoDb implements UrlRepository {

	private Mongo mongo;
	private DB db;
	private DBCollection collection;
	
	@PostConstruct
	public void init() {
		try {
			mongo = new Mongo( "flame.mongohq.com" , 27075 );
			db = mongo.getDB( "urls" );
			db.authenticate("YOUR_USERNAME", "YOUR_PASSWORD".toCharArray());
			collection = db.getCollection("urls");
		} catch (Exception e) {
			throw new RuntimeException("Problem initializing MongoDb", e);
		}
	}
	
	@Override
	public int nextKey() {
		DBObject sequenceDoc = collection.findOne();
		int nextKey = (Integer) sequenceDoc.get("nextkey");
		nextKey += 1;
		sequenceDoc.put("nextkey", nextKey);
		collection.save(sequenceDoc);
		return nextKey;
	}

	@Override
	public void saveUrl(int key, String url, String hash) {
		BasicDBObject urlDoc = new BasicDBObject();
		urlDoc.put("_id", hash);
		urlDoc.put("key", key);
		urlDoc.put("url", url);
		collection.insert(urlDoc);
	}

	@Override
	public String findUrlByHash(String hash) {
		DBObject urlDoc = new BasicDBObject();
		urlDoc.put("_id", hash);
		urlDoc = collection.findOne(urlDoc);
		return (String) (urlDoc != null ? urlDoc.get("url") : "");
	}

	@Override
	public String findHashByLongUrl(String longUrl) {
		DBObject urlDoc = new BasicDBObject();
		urlDoc.put("url", longUrl);
		urlDoc = collection.findOne(urlDoc);
		return (String) (urlDoc != null ? urlDoc.get("_id") : "");
	}

}
