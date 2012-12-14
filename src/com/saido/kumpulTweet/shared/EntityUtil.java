package com.saido.kumpulTweet.shared;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
//import com.google.appengine.api.datastore.EntityNotFoundException;
//import com.google.appengine.api.datastore.Key;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EntityUtil {
	
	private static final Logger logger = Logger.getLogger(EntityUtil.class.getCanonicalName());
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public static void persistEntity(Entity entity) {
		try {
			datastore.put(entity);
		}catch (Exception e){
			String msg = "GAGAL PERSIST!! " + e.getMessage();
			logger.log(Level.WARNING, msg);
		}
	}
	
	static DatastoreService getDatastoreServiceInstance(){
		return datastore;
	}

}
