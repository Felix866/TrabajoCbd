package graphics;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

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

public class NewInterface {

	private JFrame frame;
	
	public static String graphicSelected,tipo_tipo;
	public static Integer maxRating, minRating, anyoOpcional;
	public static boolean graficaBarras,isAnyoOpcional,isGeneral,isPrimaria,isSecundaria1,isSecundaria2,isEducSuperior;
	public static JTextPane textPane = new JTextPane();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewInterface window = new NewInterface();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 516, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphicSelected = comboBox.getSelectedItem().toString();
			}
		});
		comboBox.setToolTipText("Tipo de gráfico que desea");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rating", "Tipo de comida"}));
		graphicSelected = comboBox.getSelectedItem().toString();
		comboBox.setBounds(100, 72, 385, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblAoInicio = new JLabel("Rating Min");
		lblAoInicio.setBounds(20, 191, 70, 14);
		frame.getContentPane().add(lblAoInicio);
		
		JLabel lblAoFin = new JLabel("Rating Max");
		lblAoFin.setBounds(20, 219, 70, 14);
		frame.getContentPane().add(lblAoFin);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minRating = Integer.valueOf(comboBox_1.getSelectedItem().toString());
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		minRating = Integer.valueOf(comboBox_1.getSelectedItem().toString());
		comboBox_1.setBounds(100, 188, 50, 20);
		frame.getContentPane().add(comboBox_1);
		
		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxRating = Integer.valueOf(comboBox_2.getSelectedItem().toString());
			}
		});
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"6", "5", "4", "3", "2", "1"}));
		maxRating = Integer.valueOf(comboBox_2.getSelectedItem().toString());
		comboBox_2.setBounds(100, 216, 50, 20);
		frame.getContentPane().add(comboBox_2);
		
		final JCheckBox chckbxMostrarGrficaDe = new JCheckBox("Mostrar gráfica de barras (en caso de estar disponible)");
		chckbxMostrarGrficaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graficaBarras = chckbxMostrarGrficaDe.isSelected();
			}
		});
		chckbxMostrarGrficaDe.setBounds(6, 353, 372, 23);
		frame.getContentPane().add(chckbxMostrarGrficaDe);
		textPane.setBackground(Color.LIGHT_GRAY);
		
		
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setBounds(10, 377, 475, 51);
		textPane.setDisabledTextColor(new Color(0).RED);
		frame.getContentPane().add(textPane);
		
		final JCheckBox isFiltredUsed = new JCheckBox("Seleccione para usar filtros");
		isFiltredUsed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAnyoOpcional = isFiltredUsed.isSelected();
			}
		});
		isFiltredUsed.setBounds(148, 304, 166, 23);
		frame.getContentPane().add(isFiltredUsed);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(207, 214, 278, 62);
		frame.getContentPane().add(panel_1);
		
		final JCheckBox chckbxGeneral = new JCheckBox("Thai");
		chckbxGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isGeneral = chckbxGeneral.isSelected();
			}
		});
		isGeneral = chckbxGeneral.isSelected();
		panel_1.add(chckbxGeneral);
		
		final JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Chinese");
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPrimaria = chckbxNewCheckBox_1.isSelected();
			}
		});
		panel_1.add(chckbxNewCheckBox_1);
		isPrimaria = chckbxNewCheckBox_1.isSelected();
		
		final JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Breakfast");
		chckbxNewCheckBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSecundaria1 = chckbxNewCheckBox_2.isSelected();
			}
		});
		panel_1.add(chckbxNewCheckBox_2);
		isSecundaria1 = chckbxNewCheckBox_2.isSelected();
		
		final JCheckBox chckbxNewCheckBox_3 = new JCheckBox("American");
		chckbxNewCheckBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSecundaria2 = chckbxNewCheckBox_3.isSelected();
			}
		});
		isSecundaria2 = chckbxNewCheckBox_3.isSelected();
		panel_1.add(chckbxNewCheckBox_3);
		
		final JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Pizza");
		chckbxNewCheckBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEducSuperior = chckbxNewCheckBox_4.isSelected();
			}
		});
		isEducSuperior = chckbxNewCheckBox_4.isSelected();
		panel_1.add(chckbxNewCheckBox_4);
		
		JCheckBox checkBox = new JCheckBox("American");
		panel_1.add(checkBox);
		
		JCheckBox chckbxTurkish = new JCheckBox("Turkish");
		panel_1.add(chckbxTurkish);
		
		JCheckBox chckbxCurry = new JCheckBox("Curry");
		panel_1.add(chckbxCurry);
		
		JButton btnPopulate = new JButton("Populate");
		btnPopulate.setBackground(new Color(204, 255, 153));
		btnPopulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Populate.populateRestaurant();
					 textPane.setText("Colecciones cargadas correctamente.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnPopulate.setForeground(new Color(0, 0, 0));
		btnPopulate.setBounds(396, 11, 89, 36);
		frame.getContentPane().add(btnPopulate);
		
		JLabel lblTipoDeGrafico = new JLabel("Tipo de gráfica");
		lblTipoDeGrafico.setBounds(20, 75, 77, 14);
		frame.getContentPane().add(lblTipoDeGrafico);
		
		JLabel lblTipo = new JLabel("Precio");
		lblTipo.setBounds(20, 247, 29, 14);
		frame.getContentPane().add(lblTipo);
		
		final JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(100, 244, 70, 20);
		frame.getContentPane().add(comboBox_3);
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo_tipo = comboBox_3.getSelectedItem().toString();
			}
		});
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Ninguno", "Bajo", "medio", "Alto"}));
		
		JLabel lblTipoDeComida = new JLabel("Tipos de comida");
		lblTipoDeComida.setBounds(207, 191, 77, 14);
		frame.getContentPane().add(lblTipoDeComida);
		
		textField = new JTextField();
		textField.setBounds(100, 275, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(20, 278, 63, 14);
		frame.getContentPane().add(lblCiudad);
		
		JLabel lblFiltros = new JLabel("FILTROS");
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFiltros.setBounds(198, 151, 86, 29);
		frame.getContentPane().add(lblFiltros);
		
		JButton btnRunMongoServer = new JButton("Run Mongo server");
		btnRunMongoServer.setBackground(Color.ORANGE);
		btnRunMongoServer.setBounds(265, 11, 121, 37);
		frame.getContentPane().add(btnRunMongoServer);
		btnRunMongoServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MongoConnection.runServer("C:\\\\Program Files\\\\MongoDB\\\\Server\\\\3.6\\\\bin\\\\mongod.exe");
			}
		});
		
		JButton btnGenerarGrfica = new JButton("Generar gráfica");
		btnGenerarGrfica.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGenerarGrfica.setBounds(174, 103, 135, 37);
		frame.getContentPane().add(btnGenerarGrfica);
		tipo_tipo = comboBox_3.getSelectedItem().toString();
		btnGenerarGrfica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(graphicSelected.equals("Tipo de comida")) {
					try {
						if(!graficaBarras)
							RestaurantGraph.generateRestaurantTypeFoodLineGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes");
						else
							RestaurantGraph.generateRestaurantTypeFoodBarGraph("Gráfica", "Tipos de comida por restaurantes", "Tipos de comida", "Nº de restaurantes");
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}	
				}else if(graphicSelected.equals("Rating")) {
					try {
						if(!graficaBarras)
							RestaurantGraph.generateRestaurantRatioLineGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes");
						else
							RestaurantGraph.generateRestaurantRatioBarGraph("Gráfica", "Rating de restaurantes", "Rating", "Nº de restaurantes");
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
