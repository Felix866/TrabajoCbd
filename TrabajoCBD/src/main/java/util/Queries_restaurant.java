package util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Queries_restaurant {
	
	//Devuelve todos los elementos de la BD
	public DBCursor findAll() throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBCursor result = collection.find();
		result.limit(0);
		return result;
	}
	//Devuelve todos los elementos por rating
	public DBCursor findByRating(Double rating) throws UnknownHostException {
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject query = new BasicDBObject("rating", rating);
		DBCursor res = collection.find(query);
		return res;
	}
	//Devuelve todos los elementos por name
	public DBCursor findByName(String name) throws UnknownHostException {
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject query = new BasicDBObject("name",java.util.regex.Pattern.compile(name));
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
	
	//Encuentra los objeto dado un Codigo Postal
	public DBCursor findByLocationCP(String number) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject query = new BasicDBObject("postcode",java.util.regex.Pattern.compile(number));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra los objeto dada una ciudad
	public DBCursor findByCity(String ciudad) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		DBObject query = new BasicDBObject("address line 2",java.util.regex.Pattern.compile(ciudad));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra los objetos dado un rango de rating
	public DBCursor findByRatingRange(Double minRating,Double maxRating) throws UnknownHostException {
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		BasicDBObject query = new BasicDBObject();
		query.put("rating", new BasicDBObject("$gte", minRating).append("$lte", maxRating));
		DBCursor res = collection.find(query);
		return res;
	}	
	
	//Encuentra los objetos que coincidan con los tipos de comidas dados
	public DBCursor findByTypeFood(List<String> typeFoods) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		BasicDBObject query = new BasicDBObject();
		query.put("type_of_food", new BasicDBObject("$in", typeFoods));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra los objetos que coincidan con los datos aportados
	public DBCursor findByFilters(Double minRating,Double maxRating,List<String> typeFoods,String ciudad,String postcode,String precio,String nombre) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("Restaurantes");
		BasicDBObject query = new BasicDBObject();
		if(!typeFoods.isEmpty()) {
			query.put("type_of_food", new BasicDBObject("$in", typeFoods));
		}
		query.put("rating", new BasicDBObject("$gte", minRating).append("$lte", maxRating));
		query.put("address line 2", new BasicDBObject("$regex",ciudad));
		query.put("postcode", new BasicDBObject("$regex",postcode));
		query.put("price", new BasicDBObject("$regex",precio));
		query.put("name", new BasicDBObject("$regex",nombre));
		DBCursor result = collection.find(query);
		return result;
	}
		
	public static void main(String[] args) throws UnknownHostException {
		Queries_restaurant qr = new Queries_restaurant();
		//Collection<DBObject> res = qr.getAllRating();
//		DBCursor aux = qr.findByLocationCP("8NX");
		
		List<String> types = new ArrayList<String>();
//		types.add("African");
//		types.add("Curry");
//		List<String> types2 = new ArrayList<String>();
//		types2.add("Chinese");
//		types2.add("Hola");
//		types.retainAll(types2);

		
		//DBCursor res = qr.findByTypeFood(types);
		//DBCursor res = qr.findByCity(" ");
		//DBCursor res = qr.findByRatingRange(2.5, 5.5);
		//DBCursor res = qr.findAll();
		//DBCursor res = qr.findByPrecio("low");
		//DBCursor res = qr.findByFilters(1.0, 6.0,types,"","8","","");
		
		DBCursor res = qr.findAll();
		System.out.println(res.count());
		res.sort(new BasicDBObject("rating",1));
		while(res.hasNext()) {
			System.out.println(res.next());
		}
	}
}
