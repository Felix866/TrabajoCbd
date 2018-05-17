package graphics;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import forms.RestaurantForm;
import util.Queries_restaurant;
import util.ToolKit;

public class RestaurantGraph {
	
	public static void generateRestaurantRatioGraph(String windowName, String graphName, String xAxisName, String yAxisName, Double rangoInicio, Double rangoFin, String ciudad, String precio, List<String> comida, boolean graficaBarras) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_restaurant cr = new Queries_restaurant();
		List<RestaurantForm> restaurantes = new ArrayList<RestaurantForm>();
		List<RestaurantForm> restaurantes2;
		List<RestaurantForm> restaurantes3;
		List<RestaurantForm> restaurantes4 = new ArrayList<RestaurantForm>();
		Integer count = 0;
			
		for(Double i = rangoInicio; i<=rangoFin; i=i+0.5) {
			if(cr.findByRating(i).size() != 0) {
				restaurantes.addAll(ToolKit.cursorToColletion(cr.findByRating(i)));
			}	
		}
		restaurantes2 = ToolKit.cursorToColletion(cr.findByCity(ciudad));
		restaurantes3 = ToolKit.cursorToColletion(cr.findByPrecio(precio));
		
		restaurantes.retainAll(restaurantes2);
		restaurantes2.retainAll(restaurantes3);
		if(!comida.isEmpty()) {
			for(int i = 0; i < comida.size(); i++) {
				restaurantes4 = ToolKit.cursorToColletion(cr.findByTypeFood(comida.get(i)));
			}
			restaurantes3.retainAll(restaurantes4);
			for(Double i = rangoInicio; i <= rangoFin; i=i+0.5) {
				for(RestaurantForm aux:restaurantes4) {
					if(aux.getRating() == i) {
						count++;
					}
				}
				data.addValue(count, "Nº de restaurantes", i.toString());
			}
		}else {
			for(Double i = rangoInicio; i <= rangoFin; i=i+0.5) {
				for(RestaurantForm aux:restaurantes3) {
					if(aux.getRating() == i) {
						count++;
					}
				}
				data.addValue(count, "Nº de restaurantes", i.toString());
			}
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
		List<String> comidas = new ArrayList<String>();
		comidas.add("Pizza");
		RestaurantGraph.generateRestaurantRatioGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes", 1.0, 6.0, "London", "medium", comidas, true);
		//RestaurantGraph.generateRestaurantTypeFoodLineGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes");
		//RestaurantGraph.generateRestaurantTypeFoodBarGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes");
	}

}
