package graphics;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import util.MongoConnection;
import util.Populate;
import util.ToolKit;

public class NewInterface {

	private JFrame frmMongo;
	
	public static String graphicSelected,price,name,city;
	public static Double maxRating, minRating, anyoOpcional;
	public static boolean graficaBarras,usarFiltros,isChinese,isThai,isAmerican,isKebab,isCurry,isTurkish,isPizza,isBreakfast,isAfrican,isDesserts,isChicken;
	public static JTextPane textPane = new JTextPane();
	private JTextField textField_city;
	private List<String> comidas = new ArrayList<String>();
	private JTextField textField_name;
	private JTextField textField_limit;
	private JTextField textField_postCode;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewInterface window = new NewInterface();
					window.frmMongo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	private void initialize() {
		frmMongo = new JFrame();
		frmMongo.setTitle("Aplicación MongoBD (usando cursores)");
		frmMongo.getContentPane().setForeground(Color.LIGHT_GRAY);
		frmMongo.setBounds(100, 100, 613, 549);
		frmMongo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMongo.getContentPane().setLayout(null);

		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphicSelected = comboBox.getSelectedItem().toString();
			}
		});
		comboBox.setToolTipText("Tipo de gráfico que desea");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rating", "Tipo de comida"}));
		graphicSelected = comboBox.getSelectedItem().toString();
		
		JButton btnPopulate = new JButton("Populate");
		btnPopulate.setBackground(new Color(204, 255, 153));
		btnPopulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Populate.populateRestaurant();
					 textPane.setText("Colecciones cargadas correctamente.");
				} catch (IOException e) {
					textPane.setText("Ocurrio un problema al ejecutar el populate.");
					e.printStackTrace();
				}
			}
		});
		
		JButton btnRunMongoServer = new JButton("Run Mongo server");
		btnRunMongoServer.setBackground(Color.ORANGE);
		btnRunMongoServer.setBounds(334, 11, 154, 37);
		frmMongo.getContentPane().add(btnRunMongoServer);
		btnRunMongoServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MongoConnection.runServer("C:\\\\Program Files\\\\MongoDB\\\\Server\\\\3.6\\\\bin\\\\mongod.exe");
				textPane.setText("Archivo mongod.exe ejecutado correctamente.");
			}
		});
		btnPopulate.setForeground(new Color(0, 0, 0));
		btnPopulate.setBounds(498, 11, 89, 36);
		frmMongo.getContentPane().add(btnPopulate);
		
		JLabel lblTipoDeGrafico = new JLabel("Tipo de gráfica");
		lblTipoDeGrafico.setBounds(21, 75, 99, 14);
		frmMongo.getContentPane().add(lblTipoDeGrafico);
		comboBox.setBounds(130, 72, 457, 20);
		frmMongo.getContentPane().add(comboBox);
		
		final JCheckBox chckbxMostrarGrficaDe = new JCheckBox("Mostrar gráfica de barras");
		chckbxMostrarGrficaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graficaBarras = chckbxMostrarGrficaDe.isSelected();
			}
		});
		chckbxMostrarGrficaDe.setBounds(130, 96, 308, 23);
		frmMongo.getContentPane().add(chckbxMostrarGrficaDe);
		
		JButton btnGenerarGrfica = new JButton("Generar gráfica");
		btnGenerarGrfica.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGenerarGrfica.setBounds(452, 103, 135, 45);
		frmMongo.getContentPane().add(btnGenerarGrfica);
		btnGenerarGrfica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comidas = ToolKit.getListTypeFood(isChinese,isThai,isKebab,isCurry,isTurkish,isPizza,isBreakfast,isAfrican,isDesserts,isChicken,isAmerican);
				if(price.equals("No aplica"))
					price="";
				
				if(usarFiltros) {
					if(maxRating>=minRating) {
						if(graphicSelected.equals("Tipo de comida")) {
							try {
								RestaurantGraph.generateRestaurantTypeFoodGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes",minRating,maxRating, textField_city.getText(), textField_name.getText(), price,textField_postCode.getText(), comidas, graficaBarras);
							} catch (UnknownHostException e) {
								e.printStackTrace();
							}	
						}else if(graphicSelected.equals("Rating")) {
							try {
								RestaurantGraph.generateRestaurantRatingGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes",minRating,maxRating, textField_city.getText(), textField_name.getText(), price,textField_postCode.getText(), comidas, graficaBarras);
							} catch (UnknownHostException e) {
								e.printStackTrace();
							}
						}
					}else {
						 textPane.setText("Rating maximo("+maxRating.toString()+") debe ser mayor o igual a Rating mínimo("+minRating.toString()+")");
					}
				}
				else {
					if(graphicSelected.equals("Tipo de comida")) {
						try {
							RestaurantGraph.generateRestaurantTypeFoodGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes",1.0, 6.0, "", "", "","", comidas, graficaBarras);
						} catch (UnknownHostException e) {
							e.printStackTrace();
						}	
					}else if(graphicSelected.equals("Rating")) {
						try {
							RestaurantGraph.generateRestaurantRatingGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes",1.0, 6.0, "", "", "","", comidas, graficaBarras);
						} catch (UnknownHostException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		JLabel lblAoInicio = new JLabel("Rating Min");
		lblAoInicio.setBounds(21, 199, 70, 14);
		frmMongo.getContentPane().add(lblAoInicio);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minRating = Double.valueOf(comboBox_1.getSelectedItem().toString());
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6"}));
		minRating = Double.valueOf(comboBox_1.getSelectedItem().toString());
		comboBox_1.setBounds(111, 196, 50, 20);
		frmMongo.getContentPane().add(comboBox_1);
		
		JLabel lblAoFin = new JLabel("Rating Max");
		lblAoFin.setBounds(21, 227, 70, 14);
		frmMongo.getContentPane().add(lblAoFin);
		
		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxRating = Double.valueOf(comboBox_2.getSelectedItem().toString());
			}
		});
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"6", "5.5", "5", "4.5", "4", "3.5", "3", "2.5", "2", "1.5", "1"}));
		maxRating = Double.valueOf(comboBox_2.getSelectedItem().toString());
		comboBox_2.setBounds(111, 224, 50, 20);
		frmMongo.getContentPane().add(comboBox_2);
		
		final JCheckBox chckBox = new JCheckBox("Seleccione para activar los filtros en las gráficas");
		chckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usarFiltros = chckBox.isSelected();
			}
		});
		chckBox.setBounds(130, 122, 308, 23);
		frmMongo.getContentPane().add(chckBox);
		
		JPanel panel_tipo_de_comida = new JPanel();
		FlowLayout fl_panel_tipo_de_comida = (FlowLayout) panel_tipo_de_comida.getLayout();
		fl_panel_tipo_de_comida.setAlignment(FlowLayout.LEFT);
		panel_tipo_de_comida.setBackground(Color.LIGHT_GRAY);
		panel_tipo_de_comida.setBounds(208, 222, 379, 94);
		frmMongo.getContentPane().add(panel_tipo_de_comida);
		
		final JCheckBox chckbxThai = new JCheckBox("Thai");
		chckbxThai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isThai = chckbxThai.isSelected();
			}
		});
		isThai = chckbxThai.isSelected();
		panel_tipo_de_comida.add(chckbxThai);
		
		final JCheckBox chckbxChinese = new JCheckBox("Chinese");
		chckbxChinese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isChinese = chckbxChinese.isSelected();
			}
		});
		isChinese = chckbxChinese.isSelected();
		panel_tipo_de_comida.add(chckbxChinese);
		
		final JCheckBox chckbxBreakfast = new JCheckBox("Breakfast");
		chckbxBreakfast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isBreakfast = chckbxBreakfast.isSelected();
			}
		});
		isBreakfast = chckbxBreakfast.isSelected();
		panel_tipo_de_comida.add(chckbxBreakfast);
		
		final JCheckBox chckbxAmerican = new JCheckBox("American");
		chckbxAmerican.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAmerican = chckbxAmerican.isSelected();
			}
		});
		isAmerican = chckbxAmerican.isSelected();
		panel_tipo_de_comida.add(chckbxAmerican);
		
		final JCheckBox chckbxPizza = new JCheckBox("Pizza");
		chckbxPizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPizza = chckbxPizza.isSelected();
			}
		});
		isPizza = chckbxPizza.isSelected();
		panel_tipo_de_comida.add(chckbxPizza);
		
		final JCheckBox chckbxKebab = new JCheckBox("Kebab");
		chckbxKebab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isKebab = chckbxKebab.isSelected();
			}
		});
		isKebab = chckbxKebab.isSelected();
		panel_tipo_de_comida.add(chckbxKebab);
		
		final JCheckBox chckbxTurkish = new JCheckBox("Turkish");
		chckbxTurkish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isTurkish = chckbxTurkish.isSelected();
			}
		});
		isTurkish = chckbxTurkish.isSelected();
		panel_tipo_de_comida.add(chckbxTurkish);
		
		final JCheckBox chckbxCurry = new JCheckBox("Curry");
		chckbxCurry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCurry = chckbxCurry.isSelected();
			}
		});
		isCurry = chckbxCurry.isSelected();
		panel_tipo_de_comida.add(chckbxCurry);
		
		final JCheckBox chckbxAfrican = new JCheckBox("African");
		chckbxAfrican.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAfrican = chckbxAfrican.isSelected();
			}
		});
		isAfrican = chckbxAfrican.isSelected();
		panel_tipo_de_comida.add(chckbxAfrican);
		
		final JCheckBox chckbxDessert = new JCheckBox("Desserts");
		chckbxDessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isDesserts = chckbxDessert.isSelected();
			}
		});
		isDesserts = chckbxDessert.isSelected();
		panel_tipo_de_comida.add(chckbxDessert);
		
		final JCheckBox chckbxChicken = new JCheckBox("Chicken");
		chckbxChicken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isChicken = chckbxChicken.isSelected();
			}
		});
		isChicken = chckbxChicken.isSelected();
		panel_tipo_de_comida.add(chckbxChicken);
		
		JLabel lblTipo = new JLabel("Precio");
		lblTipo.setBounds(21, 255, 50, 14);
		frmMongo.getContentPane().add(lblTipo);
		
		final JComboBox comboBox_price = new JComboBox();
		comboBox_price.setBounds(111, 252, 79, 20);
		frmMongo.getContentPane().add(comboBox_price);
		comboBox_price.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price = comboBox_price.getSelectedItem().toString();
			}
		});
		comboBox_price.setModel(new DefaultComboBoxModel(new String[] {"No aplica", "low", "medium", "high"}));
		
		JLabel lblTipoDeComida = new JLabel("Tipos de comida");
		lblTipoDeComida.setBounds(208, 199, 126, 14);
		frmMongo.getContentPane().add(lblTipoDeComida);
		
		textField_city = new JTextField();
		textField_city.setBounds(111, 283, 79, 20);
		frmMongo.getContentPane().add(textField_city);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(21, 286, 63, 14);
		frmMongo.getContentPane().add(lblCiudad);
		
		
		JLabel lblFiltros = new JLabel("FILTROS");
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFiltros.setBounds(249, 159, 86, 29);
		frmMongo.getContentPane().add(lblFiltros);
		textPane.setBackground(Color.LIGHT_GRAY);
		
		
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setBounds(10, 429, 577, 70);
		textPane.setDisabledTextColor(new Color(0).RED);
		frmMongo.getContentPane().add(textPane);
		
		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(111, 312, 79, 20);
		frmMongo.getContentPane().add(textField_name);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(21, 315, 63, 14);
		frmMongo.getContentPane().add(lblNombre);
		
		JButton btnMostrarResultados = new JButton("Mostrar Resultados Filtrados");
		btnMostrarResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(price.equals("No aplica"))
					price="";
				
				if(maxRating>=minRating) {
				comidas = ToolKit.getListTypeFood(isChinese,isThai,isKebab,isCurry,isTurkish,isPizza,isBreakfast,isAfrican,isDesserts,isChicken,isAmerican);
				try {
					RestaurantGraph.showResults(minRating,maxRating, textField_city.getText(), price, comidas,textField_name.getText(),textField_postCode.getText());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}else {
				textPane.setText("Rating maximo("+maxRating.toString()+") debe ser mayor o igual a Rating mínimo("+minRating.toString()+")");
			}
		}});
		btnMostrarResultados.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMostrarResultados.setBounds(208, 360, 230, 45);
		frmMongo.getContentPane().add(btnMostrarResultados);
		
		textField_limit = new JTextField();
		textField_limit.setBounds(415, 329, 37, 20);
		frmMongo.getContentPane().add(textField_limit);
		
		JLabel lblLimitarLaBusqueda = new JLabel("Limitar elementos de busqueda");
		lblLimitarLaBusqueda.setBounds(218, 335, 210, 14);
		frmMongo.getContentPane().add(lblLimitarLaBusqueda);
		
		textField_postCode = new JTextField();
		textField_postCode.setBounds(111, 343, 79, 20);
		frmMongo.getContentPane().add(textField_postCode);
		
		JLabel lblCodigoPostal = new JLabel("Codigo postal");
		lblCodigoPostal.setBounds(21, 346, 86, 14);
		frmMongo.getContentPane().add(lblCodigoPostal);
		
		
		price = comboBox_price.getSelectedItem().toString();
	}
}
