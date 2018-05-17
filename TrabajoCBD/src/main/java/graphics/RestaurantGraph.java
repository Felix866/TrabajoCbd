package graphics;

import java.net.UnknownHostException;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import forms.RestaurantForm;
import util.Queries_restaurant;
import util.ToolKit;

public class RestaurantGraph {
	
	public static void generateRestaurantRatioGraph(String windowName, String graphName, String xAxisName, String yAxisName, Double rangoInicio, Double rangoFin, boolean graficaBarras) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_restaurant cr = new Queries_restaurant();
		List<RestaurantForm> restaurantes;
		List<RestaurantForm> restaurantes2;
	
		restaurantes = ToolKit.cursorToColletion(cr.findAll());
		restaurantes2 = ToolKit.cursorToColletion(cr.findByRating(5.0));
		
		restaurantes.retainAll(restaurantes2);
			
		for(Double i = rangoInicio; i<=rangoFin; i=i+0.5) {
			data.addValue(cr.findByRating(i).count(), "Nº de restaurantes", i.toString());
		}
		
		
		
		if(graficaBarras) {
			gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);
		}else {
			gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
		}
	}
	
	public static void generateRestaurantTypeFoodGraph(String windowName, String graphName, String xAxisName, String yAxisName, boolean graficasBarras) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_restaurant cr = new Queries_restaurant();
		
		Integer cont1 = cr.findByTypeFood("Thai").count();
		Integer cont2 = cr.findByTypeFood("Pizza").count();
		Integer cont3 = cr.findByTypeFood("Curry").count();
		Integer cont4 = cr.findByTypeFood("African").count();
		Integer cont5 = cr.findByTypeFood("Chinese").count();
		Integer cont6 = cr.findByTypeFood("Turkish").count();
		Integer cont7 = cr.findByTypeFood("Breakfast").count();
		Integer cont8 = cr.findByTypeFood("American").count();
		
		data.addValue(cont1, "Nº de restaurantes", "Thai");
		data.addValue(cont2, "Nº de restaurantes", "Pizza");
		data.addValue(cont3, "Nº de restaurantes", "Curry");
		data.addValue(cont4, "Nº de restaurantes", "African");
		data.addValue(cont5, "Nº de restaurantes", "Chinese");
		data.addValue(cont6, "Nº de restaurantes", "Turkish");
		data.addValue(cont7, "Nº de restaurantes", "Breakfast");
		data.addValue(cont8, "Nº de restaurantes", "American");
		
		if(graficasBarras) {
			gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);	
		}else {
			gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);	
		}
	}
	
	public static void main(String[] args) throws UnknownHostException {
		//RestaurantGraph.generateRestaurantRatioLineGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes");
		//RestaurantGraph.generateRestaurantRatioBarGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes", 1.0, 1.0);
		//RestaurantGraph.generateRestaurantTypeFoodLineGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes");
		//RestaurantGraph.generateRestaurantTypeFoodBarGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes");
	}

}
