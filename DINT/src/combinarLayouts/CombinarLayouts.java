package combinarLayouts;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class CombinarLayouts extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textPassword;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CombinarLayouts frame = new CombinarLayouts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CombinarLayouts() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblPassword = new JLabel("Contraseña");
		panel_1.add(lblPassword);
		
		JLabel lblDocumento = new JLabel("Documento");
		panel_1.add(lblDocumento);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		textPassword = new JTextField();
		panel_1.add(textPassword);
		textPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Iniciar Sesión");
		panel_1.add(btnLogin);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblFooter = new JLabel("Desarrollado por: Pablo López Couso -DAM2 Curso 2025-2026");
		panel_2.add(lblFooter);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.WEST);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("\\\\lapaman\\alumnos\\plopecous\\mis_documentos_windows\\Mis imágenes\\Screenshots\\Captura de pantalla 2025-10-08 124214.png"));
		panel_5.add(lblNewLabel_8);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.EAST);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4_1 = new JPanel();
		panel_6.add(panel_4_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_4_1 = new GridBagLayout();
		gbl_panel_4_1.columnWidths = new int[]{72, 0, 85, 0, 0};
		gbl_panel_4_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4_1.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_4_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4_1.setLayout(gbl_panel_4_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel_3_1 = new GridBagConstraints();
		gbc_lblNewLabel_3_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3_1.gridx = 0;
		gbc_lblNewLabel_3_1.gridy = 0;
		panel_4_1.add(lblNewLabel_3_1, gbc_lblNewLabel_3_1);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 0;
		panel_4_1.add(textField_7, gbc_textField_7);
		
		JLabel lblNewLabel_2_1 = new JLabel("Documento");
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 2;
		gbc_lblNewLabel_2_1.gridy = 0;
		panel_4_1.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.insets = new Insets(0, 0, 5, 0);
		gbc_textField_8.gridx = 3;
		gbc_textField_8.gridy = 0;
		panel_4_1.add(textField_8, gbc_textField_8);
		
		JLabel lblNewLabel_4_1 = new JLabel("Apellidos");
		GridBagConstraints gbc_lblNewLabel_4_1 = new GridBagConstraints();
		gbc_lblNewLabel_4_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4_1.gridx = 0;
		gbc_lblNewLabel_4_1.gridy = 1;
		panel_4_1.add(lblNewLabel_4_1, gbc_lblNewLabel_4_1);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.gridx = 1;
		gbc_textField_9.gridy = 1;
		panel_4_1.add(textField_9, gbc_textField_9);
		
		JLabel lblNewLabel_1_1 = new JLabel("Teléfono");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 2;
		gbc_lblNewLabel_1_1.gridy = 1;
		panel_4_1.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.insets = new Insets(0, 0, 5, 0);
		gbc_textField_10.gridx = 3;
		gbc_textField_10.gridy = 1;
		panel_4_1.add(textField_10, gbc_textField_10);
		
		JLabel lblNewLabel_5_1 = new JLabel("Direccion");
		GridBagConstraints gbc_lblNewLabel_5_1 = new GridBagConstraints();
		gbc_lblNewLabel_5_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5_1.gridx = 0;
		gbc_lblNewLabel_5_1.gridy = 2;
		panel_4_1.add(lblNewLabel_5_1, gbc_lblNewLabel_5_1);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridwidth = 3;
		gbc_textField_11.insets = new Insets(0, 0, 5, 0);
		gbc_textField_11.gridx = 1;
		gbc_textField_11.gridy = 2;
		panel_4_1.add(textField_11, gbc_textField_11);
		
		JLabel lblNewLabel_6_1 = new JLabel("Curso");
		GridBagConstraints gbc_lblNewLabel_6_1 = new GridBagConstraints();
		gbc_lblNewLabel_6_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6_1.gridx = 0;
		gbc_lblNewLabel_6_1.gridy = 3;
		panel_4_1.add(lblNewLabel_6_1, gbc_lblNewLabel_6_1);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_12.gridwidth = 3;
		gbc_textField_12.insets = new Insets(0, 0, 5, 0);
		gbc_textField_12.gridx = 1;
		gbc_textField_12.gridy = 3;
		panel_4_1.add(textField_12, gbc_textField_12);
		
		JLabel lblNewLabel_7_1 = new JLabel("Descripción corta");
		GridBagConstraints gbc_lblNewLabel_7_1 = new GridBagConstraints();
		gbc_lblNewLabel_7_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7_1.gridx = 0;
		gbc_lblNewLabel_7_1.gridy = 4;
		panel_4_1.add(lblNewLabel_7_1, gbc_lblNewLabel_7_1);
		
		JTextArea textArea_1 = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridheight = 2;
		gbc_textArea_1.gridwidth = 4;
		gbc_textArea_1.gridx = 0;
		gbc_textArea_1.gridy = 5;
		panel_4_1.add(textArea_1, gbc_textArea_1);
		
		JLabel lblNewLabel = new JLabel("REGISTRO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(btnNewButton);

	}

}
