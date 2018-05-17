package util;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Queries_restaurant {
	
	//Devuelve todos los elementos de la BD
	public DBCursor findAll() throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject query = new BasicDBObject();
		DBCursor result = collection.find(query);
		return result;
	}
	//Devuelve todos los elementos por rating
	public DBCursor findByRating(Double rating) throws UnknownHostException {
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject query = new BasicDBObject("rating", rating);
		DBCursor res = collection.find(query);
		return res;
	}
	//Devuelve todos los elementos por tipo de comida
	public DBCursor findByTypeFood(String type) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject query = new BasicDBObject("type_of_food",java.util.regex.Pattern.compile(type));
		DBCursor result = collection.find(query);
		return result;
	}
	//Devuelve todos los elementos por tipo de precio
	public DBCursor findByPrecio(String precio) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject query = new BasicDBObject("price",java.util.regex.Pattern.compile(precio));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra un objeto dado un Codigo Postal
	public DBCursor findByLocationCP(String number) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject query = new BasicDBObject("postcode",java.util.regex.Pattern.compile(number));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra un objeto dada una ciudad
	public DBCursor findByCity(String ciudad) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject query = new BasicDBObject("address line 2",java.util.regex.Pattern.compile(ciudad));
//		DBObject projection = new BasicDBObject("_id", 0);
//		projection.put("address", 0);
//		projection.put("URL", 0);
//		projection.put("address line 2", 0);
//		projection.put("outcode", 0);
//		projection.put("postcode", 0);
//		projection.put("rating", 0);
//		projection.put("type_of_food", 0);
//		DBCursor result = collection.find(query, projection);
		DBCursor result = collection.find(query);
		return result;
	}
	
	public static void main(String[] args) throws UnknownHostException {
		Queries_restaurant qr = new Queries_restaurant();
		//Collection<DBObject> res = qr.getAllRating();
//		DBCursor aux = qr.findByLocationCP("8NX");
//		while(aux.hasNext()) {
//			System.out.println(aux.next());
//		}
//		DBCursor res = qr.findAll();
//		System.out.println(res.count());
//		DBCursor aux2 = qr.findByRating(6.0);
//		System.out.println(aux2.count());
//		DBCursor res = qr.findByTypeFood("Thai");
//		System.out.println(res.count());
//		DBCursor aux = qr.findByCity("London");
//		DBCursor res = qr.findAll();
//		DBCursor aux = qr.findAll();
//		DBObject a;
//		while(res.hasNext()) {
//			a =  res.next();
//			if(a.get("address line 2").equals("London")) {
//				System.out.println(a);
//				System.out.println(a.keySet());
//			}
//			//System.out.println(res.next());
//		}
//		List<RestaurantForm> lista = ToolKit.cursorToColletion(aux);
//		for (int i = 0; i < lista.size()-1; i++) {
//			System.out.println(lista.get(i).getAddress());
//		}
		DBCursor res = qr.findByCity("");
		while(res.hasNext()) {
			System.out.println(res.count());
		}
	}
}
