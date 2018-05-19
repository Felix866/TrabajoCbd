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

import org.apache.commons.lang3.StringUtils;

import com.mongodb.DBCursor;

import util.MongoConnection;
import util.Populate;
import util.Queries_restaurant;
import util.ToolKit;

public class Interface {

	private JFrame frmMongo;
	
	public static String graphicSelected,price,name,city,limit;
	public static Double maxRating, minRating, anyoOpcional;
	public static boolean graficaBarras,usarFiltros,isChinese,isThai,isAmerican,isKebab,isCurry,isTurkish,isPizza,isBreakfast,isAfrican,isDesserts,isChicken;
	public static JTextPane textPane = new JTextPane();
	private JTextField textField_city;
	private List<String> comidas = new ArrayList<String>();
	private JTextField textField_name;
	private JTextField textField_limit;
	private JTextField textField_postCode;
	private DBCursor cursor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
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
	public Interface() {
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
		frmMongo.setBounds(100, 100, 630, 630);
		frmMongo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMongo.getContentPane().setLayout(null);

		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphicSelected = comboBox.getSelectedItem().toString();
			}
		});
		comboBox.setToolTipText("Tipo de gráfico que desea");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rating", "Tipo de comida", "Precio"}));
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
		
		JLabel lblTipoDeGrafico = new JLabel("Gráficas");
		lblTipoDeGrafico.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblTipoDeGrafico.setBounds(21, 24, 99, 37);
		frmMongo.getContentPane().add(lblTipoDeGrafico);
		comboBox.setBounds(21, 63, 274, 29);
		frmMongo.getContentPane().add(comboBox);
		
		final JCheckBox chckbxMostrarGrficaDe = new JCheckBox("Mostrar gráfica de barras");
		chckbxMostrarGrficaDe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxMostrarGrficaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graficaBarras = chckbxMostrarGrficaDe.isSelected();
			}
		});
		chckbxMostrarGrficaDe.setBounds(21, 99, 274, 23);
		frmMongo.getContentPane().add(chckbxMostrarGrficaDe);
		
		JButton btnGenerarGrfica = new JButton("Generar gráfica");
		btnGenerarGrfica.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGenerarGrfica.setBounds(334, 59, 253, 89);
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
						}else if(graphicSelected.equals("Precio")) {
							try {
								RestaurantGraph.generateRestaurantPriceGraph("Gráfica", "Precio de restaurantes", "Tipos de Precio", "Nº de restaurantes",minRating,maxRating, textField_city.getText(), textField_name.getText(), price,textField_postCode.getText(), comidas, graficaBarras);
							} catch (UnknownHostException e) {
								e.printStackTrace();
							}
						}
					}else {
						 textPane.setText("Rating maximo("+maxRating.toString()+") debe ser mayor o igual a Rating mínimo("+minRating.toString()+")");
					}
				} else {
					if(graphicSelected.equals("Tipo de comida")) {
						try {
							RestaurantGraph.generateRestaurantTypeFoodGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes",1.0, 6.0, "", "", "","", new ArrayList<String>(), graficaBarras);
						} catch (UnknownHostException e) {
							e.printStackTrace();
						}	
					}else if(graphicSelected.equals("Rating")) {
						try {
							RestaurantGraph.generateRestaurantRatingGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes",1.0, 6.0, "", "", "","", new ArrayList<String>(), graficaBarras);
						} catch (UnknownHostException e) {
							e.printStackTrace();
						}
					}else if(graphicSelected.equals("Precio")) {
						try {
							RestaurantGraph.generateRestaurantPriceGraph("Gráfica", "Precio de restaurantes", "Tipos de Precio", "Nº de restaurantes",1.0, 6.0, "", "", "","", new ArrayList<String>(), graficaBarras);
						} catch (UnknownHostException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		JLabel lblAoInicio = new JLabel("Rating Mínimo:");
		lblAoInicio.setBounds(21, 199, 99, 14);
		frmMongo.getContentPane().add(lblAoInicio);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minRating = Double.valueOf(comboBox_1.getSelectedItem().toString());
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6"}));
		minRating = Double.valueOf(comboBox_1.getSelectedItem().toString());
		comboBox_1.setBounds(111, 196, 50, 20);
		frmMongo.getContentPane().add(comboBox_1);
		
		JLabel lblAoFin = new JLabel("Rating Máximo:");
		lblAoFin.setBounds(21, 227, 99, 14);
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
		
		final JCheckBox chckBox = new JCheckBox("Activar filtros a gráfica");
		chckBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usarFiltros = chckBox.isSelected();
			}
		});
		chckBox.setBounds(21, 125, 308, 23);
		frmMongo.getContentPane().add(chckBox);
		
		JPanel panel_tipo_de_comida = new JPanel();
		FlowLayout fl_panel_tipo_de_comida = (FlowLayout) panel_tipo_de_comida.getLayout();
		fl_panel_tipo_de_comida.setAlignment(FlowLayout.LEFT);
		panel_tipo_de_comida.setBackground(Color.LIGHT_GRAY);
		panel_tipo_de_comida.setBounds(21, 267, 566, 70);
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
		
		JLabel lblTipo = new JLabel("Precio:");
		lblTipo.setBounds(210, 199, 50, 14);
		frmMongo.getContentPane().add(lblTipo);
		
		final JComboBox comboBox_price = new JComboBox();
		comboBox_price.setBounds(270, 193, 79, 20);
		frmMongo.getContentPane().add(comboBox_price);
		comboBox_price.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price = comboBox_price.getSelectedItem().toString();
			}
		});
		comboBox_price.setModel(new DefaultComboBoxModel(new String[] {"No aplica", "low", "medium", "high"}));
		
		textField_city = new JTextField();
		textField_city.setBounds(508, 221, 79, 20);
		frmMongo.getContentPane().add(textField_city);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(418, 224, 63, 14);
		frmMongo.getContentPane().add(lblCiudad);
		
		
		JLabel lblFiltros = new JLabel("Filtros");
		lblFiltros.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblFiltros.setBounds(21, 159, 86, 29);
		frmMongo.getContentPane().add(lblFiltros);
		textPane.setBackground(Color.LIGHT_GRAY);
		
		
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setBounds(21, 482, 577, 98);
		textPane.setDisabledTextColor(new Color(0).RED);
		frmMongo.getContentPane().add(textPane);
		
		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(270, 224, 79, 20);
		frmMongo.getContentPane().add(textField_name);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(210, 227, 63, 14);
		frmMongo.getContentPane().add(lblNombre);
		
		JButton btnMostrarResultados = new JButton("Mostrar Resultados Filtrados");
		btnMostrarResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(price.equals("No aplica"))
					price="";
				
				if(textField_limit.getText().isEmpty()) {
					limit = "0";
				}else {
					limit = textField_limit.getText();
				}
		
				if(StringUtils.isNumeric(limit)) {
					if(maxRating>=minRating) {
					comidas = ToolKit.getListTypeFood(isChinese,isThai,isKebab,isCurry,isTurkish,isPizza,isBreakfast,isAfrican,isDesserts,isChicken,isAmerican);
					try {
						RestaurantGraph.showResults(minRating,maxRating, textField_city.getText(), price, comidas,textField_name.getText(),textField_postCode.getText(),Integer.valueOf(limit));
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					}else {
					textPane.setText("Rating maximo("+maxRating.toString()+") debe ser mayor o igual a Rating mínimo("+minRating.toString()+")");
					}
				}else {
					textPane.setText("El límite de elementos buscados debe contener SOLO números.");
				}
		}});
		btnMostrarResultados.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMostrarResultados.setBounds(180, 371, 253, 45);
		frmMongo.getContentPane().add(btnMostrarResultados);
		
		textField_limit = new JTextField();
		textField_limit.setBounds(378, 346, 37, 20);
		frmMongo.getContentPane().add(textField_limit);
		
		JLabel lblLimitarLaBusqueda = new JLabel("Limitar elementos mostrados");
		lblLimitarLaBusqueda.setBounds(208, 349, 172, 14);
		frmMongo.getContentPane().add(lblLimitarLaBusqueda);
		
		textField_postCode = new JTextField();
		textField_postCode.setBounds(508, 193, 79, 20);
		frmMongo.getContentPane().add(textField_postCode);
		
		JLabel lblCodigoPostal = new JLabel("Codigo postal:");
		lblCodigoPostal.setBounds(418, 196, 86, 14);
		frmMongo.getContentPane().add(lblCodigoPostal);
		
		final JButton btnSiguienteElemento = new JButton("Siguiente elemento");
		btnSiguienteElemento.setEnabled(false);
		btnSiguienteElemento.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSiguienteElemento.setBounds(425, 427, 173, 45);
		btnSiguienteElemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				while(cursor.hasNext()) {
					textPane.setText(cursor.next().toString());
					break;
				}
			}
		});
		frmMongo.getContentPane().add(btnSiguienteElemento);
		
		JButton btnLoadCursor = new JButton("Cargar Cursor");
		btnLoadCursor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(price.equals("No aplica"))
					price="";
				
				comidas = ToolKit.getListTypeFood(isChinese,isThai,isKebab,isCurry,isTurkish,isPizza,isBreakfast,isAfrican,isDesserts,isChicken,isAmerican);
				Queries_restaurant cr = new Queries_restaurant();
				try {
					cursor = cr.findByFilters(minRating, maxRating, comidas, textField_city.getText(), textField_postCode.getText(), price, textField_name.getText());
					if(cursor.count()!=0) {
						btnSiguienteElemento.setEnabled(true);
						textPane.setText("Cursor cargado con los valores de los filtros. \nCargados "+cursor.count()+" elementos.");
					}else {
						btnSiguienteElemento.setEnabled(false);
						textPane.setText("No se pudo realizar la carga del cursor ya que se encontraron 0 elementos coincidentes.");
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		});
		btnLoadCursor.setEnabled(true);
		btnLoadCursor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLoadCursor.setBounds(226, 427, 154, 45);
		frmMongo.getContentPane().add(btnLoadCursor);
		
		JLabel lblTipoDeComida = new JLabel("Tipos de comida:");
		lblTipoDeComida.setBounds(21, 252, 121, 14);
		frmMongo.getContentPane().add(lblTipoDeComida);
		
		
		price = comboBox_price.getSelectedItem().toString();
	}
}
