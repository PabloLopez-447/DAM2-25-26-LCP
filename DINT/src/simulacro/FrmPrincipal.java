package simulacro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApellido;
	JTable table;
	private DefaultTableModel modelo;
	public boolean dlgAbierto = false;

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
		setBounds(100, 100, 745, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Gesti\u00F3n alumnos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 71, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 2;
		gbc_verticalStrut.gridy = 0;
		panel.add(verticalStrut, gbc_verticalStrut);

		JLabel lblDNI = new JLabel("DNI");
		GridBagConstraints gbc_lblDNI = new GridBagConstraints();
		gbc_lblDNI.anchor = GridBagConstraints.WEST;
		gbc_lblDNI.insets = new Insets(0, 0, 5, 5);
		gbc_lblDNI.gridx = 1;
		gbc_lblDNI.gridy = 1;
		panel.add(lblDNI, gbc_lblDNI);

		textDNI = new JTextField();
		GridBagConstraints gbc_textDNI = new GridBagConstraints();
		gbc_textDNI.insets = new Insets(0, 0, 5, 5);
		gbc_textDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDNI.gridx = 2;
		gbc_textDNI.gridy = 1;
		panel.add(textDNI, gbc_textDNI);
		textDNI.setColumns(10);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 2;
		panel.add(horizontalStrut, gbc_horizontalStrut);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		panel.add(lblNombre, gbc_lblNombre);

		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 2;
		panel.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.WEST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 3;
		panel.add(lblApellidos, gbc_lblApellidos);

		textApellido = new JTextField();
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellido.gridx = 2;
		gbc_textApellido.gridy = 3;
		panel.add(textApellido, gbc_textApellido);
		textApellido.setColumns(10);

		JButton btnAddAlumno = new JButton("Añadir alumno");
		btnAddAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verify()) {
					add();
				}

			}
		});
		GridBagConstraints gbc_btnAddAlumno = new GridBagConstraints();
		gbc_btnAddAlumno.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddAlumno.gridwidth = 2;
		gbc_btnAddAlumno.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddAlumno.gridx = 1;
		gbc_btnAddAlumno.gridy = 4;
		panel.add(btnAddAlumno, gbc_btnAddAlumno);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Resultados",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 330, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verifySelected()) {
					String dni = table.getValueAt(table.getSelectedRow(), 0).toString();
					String nombre = table.getValueAt(table.getSelectedRow(), 1).toString();
					String apellido = table.getValueAt(table.getSelectedRow(), 2).toString();

					if (!dlgAbierto) {
						DlgActualizarAlumno dlgActualizarAlumno = new DlgActualizarAlumno(FrmPrincipal.this, false, dni,
								nombre, apellido);
						dlgActualizarAlumno.setVisible(true);
						dlgAbierto = true;
					}

				}
			}
		});
		GridBagConstraints gbc_btnActualizar = new GridBagConstraints();
		gbc_btnActualizar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnActualizar.insets = new Insets(0, 0, 5, 5);
		gbc_btnActualizar.gridx = 0;
		gbc_btnActualizar.gridy = 1;
		panel_1.add(btnActualizar, gbc_btnActualizar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verifySelected()) {
					for (int i = 0; i < modelo.getRowCount(); i++) {
						if (table.getSelectedRow() == i) {
							modelo.removeRow(i);
						}
					}
				}
			}
		});
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 0);
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.gridx = 1;
		gbc_btnEliminar.gridy = 1;
		panel_1.add(btnEliminar, gbc_btnEliminar);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Nombre", "Apellido" }));
		modelo = (DefaultTableModel) table.getModel();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 2;
		scrollPane.setViewportView(table);

	}

	public boolean verify() {
		if (textDNI.getText().isBlank()) {
			JOptionPane.showMessageDialog(FrmPrincipal.this, "El DNI no puede estar vacio", "",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (textNombre.getText().isBlank()) {
			JOptionPane.showMessageDialog(FrmPrincipal.this, "El nombre no puede estar vacio", "",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (textApellido.getText().isBlank()) {
			JOptionPane.showMessageDialog(FrmPrincipal.this, "El apellido no puede estar vacio", "",
					JOptionPane.WARNING_MESSAGE);
			return false;

		}

		for (int i = 0; i < modelo.getRowCount(); i++) {
			if (modelo.getValueAt(i, 0) == textDNI.getText())
				;
			JOptionPane.showMessageDialog(FrmPrincipal.this, "El DNI ya esta registrado", "",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		return true;
	}

	public void add() {
		modelo.setRowCount(modelo.getRowCount() + 1);
		modelo.setValueAt(textDNI.getText(), modelo.getRowCount() - 1, 0);
		modelo.setValueAt(textNombre.getText(), modelo.getRowCount() - 1, 1);
		modelo.setValueAt(textApellido.getText(), modelo.getRowCount() - 1, 2);
	}

	public boolean verifySelected() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(FrmPrincipal.this, "Nada seleccionado", "", JOptionPane.WARNING_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

}
