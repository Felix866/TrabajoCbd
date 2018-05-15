package graphics;

import java.net.UnknownHostException;

import org.jfree.data.category.DefaultCategoryDataset;

import util.Queries_restaurant;

public class RestaurantGraph {
	
	public static void generateRestaurantRatioLineGraph(String windowName, String graphName, String xAxisName, String yAxisName) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_restaurant cr = new Queries_restaurant();
		
		Integer cont1 = cr.findByRating(1.0).count();
		Integer cont2 = cr.findByRating(2.0).count();
		Integer cont3 = cr.findByRating(3.0).count();
		Integer cont4 = cr.findByRating(4.0).count();
		Integer cont5 = cr.findByRating(5.0).count();
		Integer cont6 = cr.findByRating(6.0).count();
		
		data.addValue(cont1, "Nº de restaurantes", "1");
		data.addValue(cont2, "Nº de restaurantes", "2");
		data.addValue(cont3, "Nº de restaurantes", "3");
		data.addValue(cont4, "Nº de restaurantes", "4");
		data.addValue(cont5, "Nº de restaurantes", "5");
		data.addValue(cont6, "Nº de restaurantes", "6");
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	public static void generateRestaurantRatioBarGraph(String windowName, String graphName, String xAxisName, String yAxisName) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_restaurant cr = new Queries_restaurant();
		
		Integer cont1 = cr.findByRating(1.0).count();
		Integer cont2 = cr.findByRating(2.0).count();
		Integer cont3 = cr.findByRating(3.0).count();
		Integer cont4 = cr.findByRating(4.0).count();
		Integer cont5 = cr.findByRating(5.0).count();
		Integer cont6 = cr.findByRating(6.0).count();
		
		data.addValue(cont1, "Nº de restaurantes", "1");
		data.addValue(cont2, "Nº de restaurantes", "2");
		data.addValue(cont3, "Nº de restaurantes", "3");
		data.addValue(cont4, "Nº de restaurantes", "4");
		data.addValue(cont5, "Nº de restaurantes", "5");
		data.addValue(cont6, "Nº de restaurantes", "6");
		
		gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	public static void generateRestaurantTypeFoodLineGraph(String windowName, String graphName, String xAxisName, String yAxisName) throws UnknownHostException{
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
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	public static void generateRestaurantTypeFoodBarGraph(String windowName, String graphName, String xAxisName, String yAxisName) throws UnknownHostException{
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
		
		gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	public static void main(String[] args) throws UnknownHostException {
		//RestaurantGraph.generateRestaurantRatioLineGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes");
		//RestaurantGraph.generateRestaurantRatioBarGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes");
		//RestaurantGraph.generateRestaurantTypeFoodLineGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes");
		//RestaurantGraph.generateRestaurantTypeFoodBarGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes");
	}

}
