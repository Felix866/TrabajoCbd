package util;

import java.net.UnknownHostException;
import java.util.Collection;

import com.google.common.collect.Lists;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Queries_restaurant {
	
	// Devuelve todas los Ratings de la Base de datos
	@SuppressWarnings("deprecation")
	public Collection<DBObject> getAllRating() throws UnknownHostException{
		Collection<DBObject> result;
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject group = new BasicDBObject("$group", new BasicDBObject("rating","rating"));
		AggregationOutput agout = collection.aggregate(group);
		result = Lists.newArrayList(agout.results());
		return result;
	}
	
	//Encuentra un objeto dado un Codigo Postal. String
	public DBCursor findByLocationCP(String number) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject query = new BasicDBObject("postcode",java.util.regex.Pattern.compile(number));
		DBCursor result = collection.find(query);
		return result;
	}
	
	public static void main(String[] args) throws UnknownHostException {
		Queries_restaurant qr = new Queries_restaurant();
		//Collection<DBObject> res = qr.getAllRating();
		DBCursor aux = qr.findByLocationCP("8NX");
		System.out.println(aux);
	}
}
