package graphics;

import java.net.UnknownHostException;
import java.util.Collection;

import org.jfree.data.category.DefaultCategoryDataset;

import com.mongodb.DBObject;

import util.Queries_restaurant;

public class RestaurantGraph {
	
	public static void generateRestaurantRatioLineGraph(String windowName, String graphName, String xAxisName, String yAxisName) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_restaurant cr = new Queries_restaurant();
		Collection<DBObject> restaurantForm = cr.getAllRating();
		Object[] aux;
		aux= restaurantForm.toArray();
		Integer cont1 = 0;
		Integer cont2 = 0;
		Integer cont3 = 0;
		Integer cont4 = 0;
		Integer cont5 = 0;
		for(int i = 0; i < restaurantForm.size(); i++) {
			if(aux[i].equals(Integer.valueOf(1))) {
				cont1++;
			}else if(aux[i].equals(Integer.valueOf(2))) {
				cont2++;
			}else if(aux[i].equals(Integer.valueOf(3))) {
				cont3++;
			}else if(aux[i].equals(Integer.valueOf(4))) {
				cont4++;
			}else{
				cont5++;
			}
		}
		data.addValue(cont1, "Rating", "1");
		data.addValue(cont2, "Rating", "2");
		data.addValue(cont3, "Rating", "3");
		data.addValue(cont4, "Rating", "4");
		data.addValue(cont5, "Rating", "5");
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	public static void main(String[] args) throws UnknownHostException {
		RestaurantGraph.generateRestaurantRatioLineGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes");
	}

}
