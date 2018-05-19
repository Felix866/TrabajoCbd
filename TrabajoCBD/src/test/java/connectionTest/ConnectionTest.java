package connectionTest;

import java.net.UnknownHostException;

import util.DatabaseService;
import util.Queries_restaurant;

public class ConnectionTest {

	public static void main(String[] args) throws UnknownHostException {
		Queries_restaurant test = new Queries_restaurant();
		DatabaseService.getCollection("restaurantes");
	//	Collection<DBObject> object = test.getAllId();
	//	System.out.println(object.toString());
	}
}
