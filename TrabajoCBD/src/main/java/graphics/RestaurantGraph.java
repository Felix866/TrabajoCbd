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
		
//		Integer cont1 = cr.findByTypeFood("Thai").count();
//		Integer cont2 = cr.findByTypeFood("Pizza").count();
//		Integer cont3 = cr.findByTypeFood("Curry").count();
//		Integer cont4 = cr.findByTypeFood("African").count();
//		Integer cont5 = cr.findByTypeFood("Chinese").count();
//		Integer cont6 = cr.findByTypeFood("Turkish").count();
//		Integer cont7 = cr.findByTypeFood("Breakfast").count();
//		Integer cont8 = cr.findByTypeFood("American").count();
//		
//		data.addValue(cont1, "Nº de restaurantes", "Thai");
//		data.addValue(cont2, "Nº de restaurantes", "Pizza");
//		data.addValue(cont3, "Nº de restaurantes", "Curry");
//		data.addValue(cont4, "Nº de restaurantes", "African");
//		data.addValue(cont5, "Nº de restaurantes", "Chinese");
//		data.addValue(cont6, "Nº de restaurantes", "Turkish");
//		data.addValue(cont7, "Nº de restaurantes", "Breakfast");
//		data.addValue(cont8, "Nº de restaurantes", "American");
		
		if(graficaBarras) {
			gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);	
		}else {
			gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);	
		}
	}
	
	public static void showResults(Double minRating, Double maxRating, String ciudad, String price, List<String> comidas, String nombre,String postcode) throws UnknownHostException {
		GraphUtil gu = new GraphUtil();
		Queries_restaurant cr = new Queries_restaurant();
		List<RestaurantForm> restaurantes = new ArrayList<RestaurantForm>();
		DBCursor cursor;
		
		cursor = cr.findByFilters(minRating, maxRating, comidas, ciudad, postcode, price, nombre);
		restaurantes = ToolKit.cursorToColletion(cursor);
			
		gu.createShowResult(restaurantes);
	}
	
	public static void main(String[] args) throws UnknownHostException {
		//RestaurantGraph.generateRestaurantRatioLineGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes");
		List<String> comidas = new ArrayList<String>();
		comidas.add("Pizza");
		//RestaurantGraph.generateRestaurantRatioGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes", 1.0, 6.0, "London", "medium", comidas, true);
		//RestaurantGraph.generateRestaurantTypeFoodLineGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes");
		//RestaurantGraph.generateRestaurantTypeFoodBarGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes");
	}



}
