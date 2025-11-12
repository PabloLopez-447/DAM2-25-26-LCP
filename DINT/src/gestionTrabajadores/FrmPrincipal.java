package gestionTrabajadores;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textAP1;
	private JTextField textAP2;
	private JTextField textProfesion;
	private JTable table;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel Izquierda = new JPanel();
		contentPane.add(Izquierda);
		GridBagLayout gbl_Izquierda = new GridBagLayout();
		gbl_Izquierda.columnWidths = new int[]{282, 0};
		gbl_Izquierda.rowHeights = new int[]{162, 102, 245, 67, 0};
		gbl_Izquierda.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_Izquierda.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		Izquierda.setLayout(gbl_Izquierda);
		
		JPanel infoTrabajador = new JPanel();
		infoTrabajador.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Identificaci\u00F3n del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_infoTrabajador = new GridBagConstraints();
		gbc_infoTrabajador.fill = GridBagConstraints.BOTH;
		gbc_infoTrabajador.insets = new Insets(0, 0, 5, 0);
		gbc_infoTrabajador.gridx = 0;
		gbc_infoTrabajador.gridy = 0;
		Izquierda.add(infoTrabajador, gbc_infoTrabajador);
		GridBagLayout gbl_infoTrabajador = new GridBagLayout();
		gbl_infoTrabajador.columnWidths = new int[]{0, 0, 0};
		gbl_infoTrabajador.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_infoTrabajador.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_infoTrabajador.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		infoTrabajador.setLayout(gbl_infoTrabajador);
		
		JLabel lblDNI = new JLabel("DNI");
		GridBagConstraints gbc_lblDNI = new GridBagConstraints();
		gbc_lblDNI.insets = new Insets(0, 0, 5, 5);
		gbc_lblDNI.anchor = GridBagConstraints.EAST;
		gbc_lblDNI.gridx = 0;
		gbc_lblDNI.gridy = 0;
		infoTrabajador.add(lblDNI, gbc_lblDNI);
		
		textDNI = new JTextField();
		GridBagConstraints gbc_textDNI = new GridBagConstraints();
		gbc_textDNI.insets = new Insets(0, 0, 5, 0);
		gbc_textDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDNI.gridx = 1;
		gbc_textDNI.gridy = 0;
		infoTrabajador.add(textDNI, gbc_textDNI);
		textDNI.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		infoTrabajador.add(lblNombre, gbc_lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 1;
		gbc_textNombre.gridy = 1;
		infoTrabajador.add(textNombre, gbc_textNombre);
		
		JLabel lblAP1 = new JLabel("Apellido1");
		GridBagConstraints gbc_lblAP1 = new GridBagConstraints();
		gbc_lblAP1.anchor = GridBagConstraints.EAST;
		gbc_lblAP1.insets = new Insets(0, 0, 5, 5);
		gbc_lblAP1.gridx = 0;
		gbc_lblAP1.gridy = 2;
		infoTrabajador.add(lblAP1, gbc_lblAP1);
		
		textAP1 = new JTextField();
		textAP1.setColumns(10);
		GridBagConstraints gbc_textAP1 = new GridBagConstraints();
		gbc_textAP1.insets = new Insets(0, 0, 5, 0);
		gbc_textAP1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAP1.gridx = 1;
		gbc_textAP1.gridy = 2;
		infoTrabajador.add(textAP1, gbc_textAP1);
		
		JLabel lblAP2 = new JLabel("Apellido2");
		GridBagConstraints gbc_lblAP2 = new GridBagConstraints();
		gbc_lblAP2.anchor = GridBagConstraints.EAST;
		gbc_lblAP2.insets = new Insets(0, 0, 0, 5);
		gbc_lblAP2.gridx = 0;
		gbc_lblAP2.gridy = 3;
		infoTrabajador.add(lblAP2, gbc_lblAP2);
		
		textAP2 = new JTextField();
		textAP2.setColumns(10);
		GridBagConstraints gbc_textAP2 = new GridBagConstraints();
		gbc_textAP2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAP2.gridx = 1;
		gbc_textAP2.gridy = 3;
		infoTrabajador.add(textAP2, gbc_textAP2);
		
		JPanel provTrabajador = new JPanel();
		provTrabajador.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Provincia del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_provTrabajador = new GridBagConstraints();
		gbc_provTrabajador.fill = GridBagConstraints.BOTH;
		gbc_provTrabajador.insets = new Insets(0, 0, 5, 0);
		gbc_provTrabajador.gridx = 0;
		gbc_provTrabajador.gridy = 1;
		Izquierda.add(provTrabajador, gbc_provTrabajador);
		GridBagLayout gbl_provTrabajador = new GridBagLayout();
		gbl_provTrabajador.columnWidths = new int[]{253, 97, 0};
		gbl_provTrabajador.rowHeights = new int[]{0, 0, 0};
		gbl_provTrabajador.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_provTrabajador.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		provTrabajador.setLayout(gbl_provTrabajador);
		
		JComboBox<?> comboBox = new JComboBox<>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		provTrabajador.add(comboBox, gbc_comboBox);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		provTrabajador.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		provTrabajador.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel profTrabajador = new JPanel();
		profTrabajador.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Profesi\u00F3n del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_profTrabajador = new GridBagConstraints();
		gbc_profTrabajador.fill = GridBagConstraints.BOTH;
		gbc_profTrabajador.insets = new Insets(0, 0, 5, 0);
		gbc_profTrabajador.gridx = 0;
		gbc_profTrabajador.gridy = 2;
		Izquierda.add(profTrabajador, gbc_profTrabajador);
		GridBagLayout gbl_profTrabajador = new GridBagLayout();
		gbl_profTrabajador.columnWidths = new int[]{188, 188, 0};
		gbl_profTrabajador.rowHeights = new int[]{224, 0, 0};
		gbl_profTrabajador.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_profTrabajador.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		profTrabajador.setLayout(gbl_profTrabajador);
		
		JList<String> listProfesiones = new JList<>();
		GridBagConstraints gbc_listProfesiones = new GridBagConstraints();
		gbc_listProfesiones.fill = GridBagConstraints.BOTH;
		gbc_listProfesiones.insets = new Insets(0, 0, 5, 5);
		gbc_listProfesiones.gridx = 0;
		gbc_listProfesiones.gridy = 0;
		profTrabajador.add(listProfesiones, gbc_listProfesiones);
		
		JButton btnEliminarProfesion = new JButton("Eliminar Profesion");
		GridBagConstraints gbc_btnEliminarProfesion = new GridBagConstraints();
		gbc_btnEliminarProfesion.insets = new Insets(0, 0, 5, 0);
		gbc_btnEliminarProfesion.anchor = GridBagConstraints.NORTH;
		gbc_btnEliminarProfesion.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminarProfesion.gridx = 1;
		gbc_btnEliminarProfesion.gridy = 0;
		profTrabajador.add(btnEliminarProfesion, gbc_btnEliminarProfesion);
		
		textProfesion = new JTextField();
		GridBagConstraints gbc_textProfesion = new GridBagConstraints();
		gbc_textProfesion.insets = new Insets(0, 0, 0, 5);
		gbc_textProfesion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textProfesion.gridx = 0;
		gbc_textProfesion.gridy = 1;
		profTrabajador.add(textProfesion, gbc_textProfesion);
		textProfesion.setColumns(10);
		
		JButton btnAddProfesion = new JButton("Añadir profesion");
		GridBagConstraints gbc_btnAddProfesion = new GridBagConstraints();
		gbc_btnAddProfesion.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddProfesion.gridx = 1;
		gbc_btnAddProfesion.gridy = 1;
		profTrabajador.add(btnAddProfesion, gbc_btnAddProfesion);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		Izquierda.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{282, 0};
		gbl_panel.rowHeights = new int[]{49, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnAddTrabajador = new JButton("Añadir trabajador");
		GridBagConstraints gbc_btnAddTrabajador = new GridBagConstraints();
		gbc_btnAddTrabajador.anchor = GridBagConstraints.NORTH;
		gbc_btnAddTrabajador.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddTrabajador.gridx = 0;
		gbc_btnAddTrabajador.gridy = 0;
		panel.add(btnAddTrabajador, gbc_btnAddTrabajador);
		
		JPanel derecha = new JPanel();
		contentPane.add(derecha);
		GridBagLayout gbl_derecha = new GridBagLayout();
		gbl_derecha.columnWidths = new int[]{0, 0};
		gbl_derecha.rowHeights = new int[]{329, 0, 0, 0};
		gbl_derecha.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_derecha.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		derecha.setLayout(gbl_derecha);
		
		JPanel trabajadoresDisponibles = new JPanel();
		trabajadoresDisponibles.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Trabajadores disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_trabajadoresDisponibles = new GridBagConstraints();
		gbc_trabajadoresDisponibles.anchor = GridBagConstraints.SOUTHWEST;
		gbc_trabajadoresDisponibles.insets = new Insets(0, 0, 5, 0);
		gbc_trabajadoresDisponibles.gridx = 0;
		gbc_trabajadoresDisponibles.gridy = 0;
		derecha.add(trabajadoresDisponibles, gbc_trabajadoresDisponibles);
		trabajadoresDisponibles.setLayout(new BoxLayout(trabajadoresDisponibles, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		trabajadoresDisponibles.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre y apellidos", "Provincia", "Profesi\u00F3n"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		derecha.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{89, 0};
		gbl_panel_3.rowHeights = new int[]{56, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JButton btnNewButton_2 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 0;
		panel_3.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JPanel detalleTrabajador = new JPanel();
		detalleTrabajador.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Detalle del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_detalleTrabajador = new GridBagConstraints();
		gbc_detalleTrabajador.fill = GridBagConstraints.BOTH;
		gbc_detalleTrabajador.gridx = 0;
		gbc_detalleTrabajador.gridy = 2;
		derecha.add(detalleTrabajador, gbc_detalleTrabajador);
		detalleTrabajador.setLayout(new BoxLayout(detalleTrabajador, BoxLayout.X_AXIS));
		
		JTextArea textArea = new JTextArea();
		detalleTrabajador.add(textArea);

	}

}
