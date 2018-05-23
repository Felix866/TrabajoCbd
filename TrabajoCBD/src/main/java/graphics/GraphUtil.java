package graphics;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import forms.RestaurantForm;

public class GraphUtil extends javax.swing.JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GraphUtil(){
		
	}
	
	/**
	 * Crea una gráfica de líneas
	 * 
	 * @param windowName (Nombre de la ventana)
	 * @param graphName (Título de la gráfica)
	 * @param xAxisName (Nombre Eje x)
	 * @param yAxisName (Nombre Eje y)
	 * @param data
	 */
	public void createLineGraph(String windowName, String graphName, String xAxisName, String yAxisName, DefaultCategoryDataset data){
		JFreeChart graph = ChartFactory.createLineChart(graphName, xAxisName, yAxisName, data, PlotOrientation.VERTICAL,
				true, true, false);
		
		ChartPanel panel = new ChartPanel(graph);
		JFrame window = new JFrame(windowName);
		window.getContentPane().add(panel);
		window.setBounds(50, 50, 1000, 700);
//		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	/**
	 * Crea una gráfica de barras
	 * 
	 * @param windowName(Nombre de la ventana)
	 * @param graphName (Título de la gráfica)
	 * @param xAxisName (Nombre Eje x)
	 * @param yAxisName (Nombre Eje y)
	 * @param data
	 */
	public void createBarGraph(String windowName, String graphName, String xAxisName, String yAxisName, DefaultCategoryDataset data){
		JFreeChart graph = ChartFactory.createBarChart(graphName, xAxisName, yAxisName, data, PlotOrientation.VERTICAL,
				true, true, false);
		
		ChartPanel panel = new ChartPanel(graph);
		JFrame window = new JFrame(windowName);
		window.getContentPane().add(panel);
		window.setBounds(50, 50, 1000, 700);
//		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	public void createShowResult(List<RestaurantForm> forms){
		JFrame window = new JFrame();
		window.getContentPane().setForeground(Color.LIGHT_GRAY);
		window.setTitle("Mostrar Resultados");
		window.setBounds(50, 50, 1200, 700);
		window.getContentPane().setLayout(null);
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.BLACK);
		textPane.setBackground(Color.WHITE);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPane.setEnabled(false);
		textPane.setEditable(true);
		textPane.setBounds(10, 20, 1200, 700);
		textPane.setDisabledTextColor(Color.BLACK);
		
		JLabel label = new JLabel("Número de documentos encontrados:"+forms.size());
		label.setBounds(10, 2, 400, 18);
		window.getContentPane().add(label);
		
		String text ="";
		Integer count = 1;

		for (RestaurantForm form: forms) {
			text += "Restaurant"+String.valueOf(count)+" -> "+form.toString()+"\n ";
			count++;
		}
		textPane.setText(text);
		window.getContentPane().add(textPane);
//		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	
	}
	
	
	
}
