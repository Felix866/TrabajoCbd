package util;

import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public class DatabaseService {
	
	

	//Get table
	public static DBCollection getCollection(String collectionName) throws UnknownHostException{
		DB db = MongoConnection.connect("localhost", 27017);
		DBCollection table = db.getCollection(collectionName);
		return table;
	}
	
	//Drop collection
	public void dropCollection(String name) throws UnknownHostException{
		DB db = MongoConnection.connect("localhost", 27017);
		try{
			DBCollection table = db.getCollection(name);
			table.drop();
		}catch(Throwable e){
			System.err.println(e.toString());
		}
	}
	
	public static void main(String[] args) throws IOException {
		getCollection("recetas");
	}
	
}
