package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import forms.RestaurantForm;

public class ToolKit {
	/*
	 * Recoge un cursor y devuelve una lista de restaurantes
	 */
	public static List<RestaurantForm> cursorToColletion(DBCursor cursor){		
		List<RestaurantForm> res = new ArrayList<RestaurantForm>();
		DBObject aux = null;
		RestaurantForm form = null;
		String id,address,address2,name,outcode,postcode,rating,typefood,price;
		
		while(cursor.hasNext()) {
			aux =  cursor.next();
			id = aux.get("_id").toString();
			address = aux.get("address").toString();
			address2 =  aux.get("address line 2").toString();
			name = aux.get("name").toString();
			outcode = aux.get("outcode").toString();
			postcode = aux.get("postcode").toString();
			rating = aux.get("rating").toString();
			typefood = aux.get("type_of_food").toString();
			price = aux.get("price").toString();
			form = new RestaurantForm(id,address, address2, name, outcode, postcode ,Double.parseDouble(rating), typefood,price);
			res.add(form);
		}
		
		return res;	
	}

	public static List<String> getListTypeFood(boolean isChinese, boolean isThai, boolean isKebab, boolean isCurry,
			boolean isTurkish, boolean isPizza, boolean isBreakfast, boolean isAfrican, boolean isDesserts, boolean isChicken, boolean isAmerican) {
		List<String> res = new ArrayList<String>();
		if(isChinese)
			res.add("Chinese");
		if(isThai)
			res.add("Thai");
		if(isKebab)
			res.add("Kebab");
		if(isCurry)
			res.add("Curry");
		if(isTurkish)
			res.add("Turkish");
		if(isPizza)
			res.add("Pizza");
		if(isBreakfast)
			res.add("Breakfast");
		if(isAfrican)
			res.add("African");
		if(isDesserts)
			res.add("Desserts");
		if(isChicken)
			res.add("Chicken");
		if(isAmerican)
			res.add("American");
		
		
		return res;
	}
	
	public static String getOrderAttribute(String orden) {
		String res = orden;
		if(orden.equals("Ciudad"))
			res = "address line 2";
		if(orden.equals("Codigo Postal"))
			res = "postcode";
		if(orden.equals("Nombre"))
			res = "postcode";
		if(orden.equals("Precio"))
			res = "price";
		if(orden.equals("Rating"))
			res = "rating";
		if(orden.equals("Tipo de comida"))
			res = "type_of_food";
		
		return res;
	}
	
	public static List<String> getAllTypeFood(){
		List<String> res = new ArrayList<String>();
		res.add("Chinese");res.add("Thai");res.add("Kebab");res.add("Curry");res.add("Turkish");res.add("Pizza");res.add("African");res.add("Desserts");res.add("American");res.add("Chicken");res.add("Breakfast");
		return res;
	}


	public static List<String> getAllTypePrice() {
		List<String> res = new ArrayList<String>();
		res.add("low");res.add("medium");res.add("high");
		return res;
	}
	
	public boolean writeFile(DBCursor cursor) {
		Boolean res;
		
		FileWriter archivo;
		
		@SuppressWarnings("unused")
		BufferedWriter bwEscritor;
		try {
			archivo = new FileWriter("C:\\data\\resultados.json");
		//De momento se deja hasta aqui, listo para escribir, se escribe en el momento de dar la orden.
//		for (int i=0; i<=mTextos.length-1;i++)
		while(cursor.hasNext())
		{
			//Se va escribiendo cada linea en el archivo de texto.
			archivo.write(cursor.next().toString() + "\r\n" );
		}
		//Este metodo escribe el archivo en el disco duro.
		bwEscritor = new BufferedWriter(archivo);
		res=true;
		} catch (IOException e) {
			e.printStackTrace();
			res= false;
		}
		return res;
		
		}

	}
