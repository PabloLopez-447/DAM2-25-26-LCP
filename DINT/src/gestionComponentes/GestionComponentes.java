package gestionComponentes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.BevelBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionComponentes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	String descuentos;
	int descuento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionComponentes frame = new GestionComponentes();
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
	public GestionComponentes() {
		setAutoRequestFocus(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1007, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPieza = new JLabel("Pieza");
		lblPieza.setBounds(48, 44, 90, 14);
		contentPane.add(lblPieza);
		
		JLabel lblPU = new JLabel("Precio/Unidade");
		lblPU.setBounds(48, 99, 90, 14);
		contentPane.add(lblPU);
		
		textField = new JTextField();
		textField.setBounds(148, 41, 253, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(148, 96, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Unidades Adquiridas", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel.setToolTipText("");
		panel.setBounds(48, 157, 353, 141);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtn1 = new JRadioButton("1-10");
		buttonGroup.add(rdbtn1);
		rdbtn1.setBounds(29, 18, 109, 23);
		panel.add(rdbtn1);
		
		JRadioButton rdbtn2 = new JRadioButton("11-50 (Descuento 5%)");
		buttonGroup.add(rdbtn2);
		rdbtn2.setBounds(29, 59, 155, 23);
		panel.add(rdbtn2);
		
		JRadioButton rdbtn3 = new JRadioButton(">50 (Descuento 10%)");
		buttonGroup.add(rdbtn3);
		rdbtn3.setBounds(29, 100, 138, 23);
		panel.add(rdbtn3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setToolTipText("");
		panel_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Descuentos Adicionales", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(48, 340, 353, 141);
		contentPane.add(panel_1);
		
		JCheckBox chckbxEmpleado = new JCheckBox("Empleado (Descuento 10%)");
		chckbxEmpleado.setBounds(33, 31, 271, 23);
		panel_1.add(chckbxEmpleado);
		
		JCheckBox chckbxPremium = new JCheckBox("Cliente premium (Descuento 5%)");
		chckbxPremium.setBounds(33, 85, 220, 23);
		panel_1.add(chckbxPremium);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Resultado de calculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(471, 40, 463, 441);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(255, 255, 128));
		textPane.setBounds(10, 26, 443, 404);
		panel_2.add(textPane);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textField.getText().trim();
				String precioTexto = textField_1.getText().trim();
				
				double precio = Double.parseDouble(precioTexto);
				double descuento = 0;
				String mensaje = "Pieza: " + nombre + "\nPrecio inicial: " + precio + " €\n\n";
				
				 
				if (rdbtn1.isSelected()) {
				descuento += 0.05;
				mensaje += "Descuento por unidades (11-50): 5%\n";
				} else if (rdbtn2.isSelected()) {
				descuento += 0.15;
				mensaje += "Descuento por unidades (>50): 15%\n";
				} else {
				mensaje += "Sin descuento por unidades.\n";
				}
				 
				if (chckbxPremium.isSelected()) {
				descuento += 0.10;
				mensaje += "Descuento empregado: 10%\n";
				}
				if (chckbxPremium.isSelected()) {
				descuento += 0.05;
				mensaje += "Descuento cliente premium: 5%\n";
				}
				 
				if (descuento == 0) {
				textPane.setText(mensaje + "\nNo se aplico ningún descuento.\nPrecio final: " + precio + " €");
				} else {
				double precioFinal = precio * (1 - descuento);
				textPane.setText(mensaje + "\nPrecio final con descuentos: " + String.format("%.2f", precioFinal) + " €");
				}
			}
		});
		btnCalcular.setBounds(553, 506, 205, 23);
		contentPane.add(btnCalcular);
		
		JButton btnNuevoCalculo = new JButton("Nuevo Calculo");
		btnNuevoCalculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				buttonGroup.clearSelection();
				chckbxEmpleado.setSelected(false);
				chckbxPremium.setSelected(false);
				textPane.setText("");
			}
		});
		btnNuevoCalculo.setBounds(232, 506, 205, 23);
		contentPane.add(btnNuevoCalculo);

	}
}
