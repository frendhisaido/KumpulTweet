package com.saido.kumpulTweet.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.appengine.api.datastore.Entity;
import com.saido.kumpulTweet.shared.EntityUtil;

public class Store {

	private static final Logger logger = Logger.getLogger(Store.class.getCanonicalName());
		
	
	static void tweet(String[] dataTweet){
		try{
			//Entity(Nama Kind, id dari tweet buat key)
			Entity aTweet = new Entity("TweetTri", dataTweet[0]);
			
			aTweet.setProperty("Author", dataTweet[1]);
			aTweet.setProperty("Time", dataTweet[2]);
			aTweet.setProperty("Content", dataTweet[3]);
			aTweet.setProperty("App", dataTweet[4]);
			
			EntityUtil.persistEntity(aTweet);
			
		}catch(Exception e){
			String msg = "GAGAL STORE!! " + e.getMessage();
			logger.log(Level.WARNING, msg);
		}
	}
}
