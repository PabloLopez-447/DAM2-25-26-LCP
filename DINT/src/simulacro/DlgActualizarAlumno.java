package simulacro;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgActualizarAlumno extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApellido;
	private JComboBox comboBox;
	private JTextField textMod;

	/**
	 * Create the dialog.
	 */
	public DlgActualizarAlumno(FrmPrincipal parent, boolean modal, String dni, String nombre, String apellido) {
		super(parent, modal);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parent.dlgAbierto = false;
			}
		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 0, 0, 0));
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Datos alumno",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 37, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 2;
		gbc_verticalStrut.gridy = 0;
		panel_1.add(verticalStrut, gbc_verticalStrut);

		JLabel lblDNI = new JLabel("DNI");
		GridBagConstraints gbc_lblDNI = new GridBagConstraints();
		gbc_lblDNI.anchor = GridBagConstraints.WEST;
		gbc_lblDNI.insets = new Insets(0, 0, 5, 5);
		gbc_lblDNI.gridx = 1;
		gbc_lblDNI.gridy = 1;
		panel_1.add(lblDNI, gbc_lblDNI);

		textDNI = new JTextField();
		textDNI.setEditable(false);
		textDNI.setText(dni);
		GridBagConstraints gbc_textDNI = new GridBagConstraints();
		gbc_textDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDNI.insets = new Insets(0, 0, 5, 5);
		gbc_textDNI.gridx = 2;
		gbc_textDNI.gridy = 1;
		panel_1.add(textDNI, gbc_textDNI);
		textDNI.setColumns(10);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 2;
		panel_1.add(horizontalStrut, gbc_horizontalStrut);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		panel_1.add(lblNombre, gbc_lblNombre);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setText(nombre);
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 2;
		panel_1.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.WEST;
		gbc_lblApellidos.insets = new Insets(0, 0, 0, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 3;
		panel_1.add(lblApellidos, gbc_lblApellidos);

		textApellido = new JTextField();
		textApellido.setEditable(false);
		textApellido.setText(apellido);
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.insets = new Insets(0, 0, 0, 5);
		gbc_textApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellido.gridx = 2;
		gbc_textApellido.gridy = 3;
		panel_1.add(textApellido, gbc_textApellido);
		textApellido.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPanel.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch (comboBox.getSelectedItem().toString()) {
				case "Nombre" -> textMod.setText(textNombre.getText());
				case "Apellidos" -> textMod.setText(textApellido.getText());
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "", "Nombre", "Apellidos" }));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		panel_2.add(comboBox, gbc_comboBox);

		textMod = new JTextField();
		GridBagConstraints gbc_textMod = new GridBagConstraints();
		gbc_textMod.insets = new Insets(0, 0, 5, 5);
		gbc_textMod.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMod.gridx = 1;
		gbc_textMod.gridy = 2;
		panel_2.add(textMod, gbc_textMod);
		textMod.setColumns(10);

		JButton btnAplicarCambio = new JButton("Aplicar cambio");
		btnAplicarCambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (comboBox.getSelectedItem().toString()) {
				case "Nombre" -> {
					textNombre.setText(textMod.getText());
					parent.table.setValueAt(textMod.getText(), parent.table.getSelectedRow(), 1);
				}
				case "Apellidos" -> textMod.setText(textApellido.getText());
				}
			}
		});
		GridBagConstraints gbc_btnAplicarCambio = new GridBagConstraints();
		gbc_btnAplicarCambio.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAplicarCambio.insets = new Insets(0, 0, 5, 5);
		gbc_btnAplicarCambio.gridx = 1;
		gbc_btnAplicarCambio.gridy = 3;
		panel_2.add(btnAplicarCambio, gbc_btnAplicarCambio);

		JButton btnNewButton_1 = new JButton("Descartar cambio");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (comboBox.getSelectedItem().toString()) {
				case "Nombre" -> textMod.setText(textNombre.getText());
				case "Apellidos" -> textMod.setText(textApellido.getText());
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 4;
		panel_2.add(btnNewButton_1, gbc_btnNewButton_1);
	}

}
