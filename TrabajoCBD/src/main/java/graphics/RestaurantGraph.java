package graphics;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import com.mongodb.DBCursor;

import forms.RestaurantForm;
import util.Queries_restaurant;
import util.ToolKit;

public class RestaurantGraph {
	
	public static void generateRestaurantRatingGraph(String windowName, String graphName, String xAxisName, String yAxisName, Double rangoInicio, Double rangoFin, String ciudad,String nombre, String precio,String postcode, List<String> tipo_comida, boolean graficaBarras) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_restaurant cr = new Queries_restaurant();
		List<RestaurantForm> restaurantes = new ArrayList<RestaurantForm>();
		DBCursor cursor;
		Integer count = 0;

		cursor = cr.findByFilters(rangoInicio, rangoFin, tipo_comida, ciudad, postcode, precio, nombre);
		restaurantes = ToolKit.cursorToColletion(cursor);
		
		for(Double i = rangoInicio; i <= rangoFin; i=i+0.5) {
			count=0;
			for(RestaurantForm aux:restaurantes) {
				if(aux.getRating() == i) {
					count++;
				}
			}
			data.addValue(count, "Nº de restaurantes", i.toString());
		}
		
		if(graficaBarras) {
			gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);
		}else {
			gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
		}
	}
	
	public static void generateRestaurantTypeFoodGraph(String windowName, String graphName, String xAxisName, String yAxisName, Double rangoInicio, Double rangoFin, String ciudad,String nombre, String precio,String postcode, List<String> tipo_comida, boolean graficaBarras) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_restaurant cr = new Queries_restaurant();
		DBCursor cursor;
		int count = 0;
		
		List<RestaurantForm> restaurantes = new ArrayList<RestaurantForm>();
		
		cursor = cr.findByFilters(rangoInicio, rangoFin, tipo_comida, ciudad, postcode, precio, nombre);
		restaurantes = ToolKit.cursorToColletion(cursor);
		
		
		for(String typeFood:ToolKit.getAllTypeFood()) {
			count=0;
			for(RestaurantForm aux:restaurantes) {
				if(aux.getType_food().equals(typeFood)) {
					count++;
				}
			}
			data.addValue(count, "Nº de restaurantes", typeFood);
		}

		if(graficaBarras) {
			gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);	
		}else {
			gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);	
		}
	}
	
	public static void generateRestaurantPriceGraph(String windowName, String graphName, String xAxisName, String yAxisName, Double rangoInicio, Double rangoFin, String ciudad,String nombre, String precio,String postcode, List<String> tipo_comida, boolean graficaBarras) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_restaurant cr = new Queries_restaurant();
		DBCursor cursor;
		int count = 0;
		
		List<RestaurantForm> restaurantes = new ArrayList<RestaurantForm>();
		
		cursor = cr.findByFilters(rangoInicio, rangoFin, tipo_comida, ciudad, postcode, precio, nombre);
		restaurantes = ToolKit.cursorToColletion(cursor);
		
		for(String typePrice:ToolKit.getAllTypePrice()) {
			count=0;
			for(RestaurantForm aux:restaurantes) {
				if(aux.getPrice().equals(typePrice)) {
					count++;
				}
			}
			data.addValue(count, "Nº de restaurantes", typePrice);
		}
		
		
		if(graficaBarras) {
			gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);	
		}else {
			gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);	
		}
	}
	
	
	
	public static void showResults(Double minRating, Double maxRating, String ciudad, String price, List<String> comidas, String nombre,String postcode,int limit,int tipoorden, String orden) throws UnknownHostException {
		GraphUtil gu = new GraphUtil();
		Queries_restaurant cr = new Queries_restaurant();
		List<RestaurantForm> restaurantes = new ArrayList<RestaurantForm>();
		DBCursor cursor;
		
		cursor = cr.findByFiltersOrder(minRating, maxRating, comidas, ciudad, postcode, price, nombre,tipoorden,orden);
		cursor.limit(limit);
		restaurantes = ToolKit.cursorToColletion(cursor);
			
		gu.createShowResult(restaurantes);
	}
	
}
