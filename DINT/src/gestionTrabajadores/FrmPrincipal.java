package gestionTrabajadores;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	// -------- CAMPOS --------
	private JTextField textDNI, textNombre, textAP1, textAP2, textProfesion;
	private JComboBox<String> comboBox;
	private DefaultListModel<String> modeloProfesiones;
	private JTable tablaTrabajadores;
	private DefaultTableModel modeloTrabajadores;
	private JButton btnAddTrabajador, btnEliminarProvincia, btnEliminarProfesion, btnDelTrabajador;
	private JTextArea textDetalle;

	private String emailSesion = "usuario@example.com";
	private String tokenSesion = "token_12345";

	public FrmPrincipal() {
		setTitle("Gestión de Trabajadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(980, 600);
		setLocationRelativeTo(null);

		// ------------------------- MENU ------------------------------

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// -------- MENU SESION --------

		JMenu menuSesion = new JMenu("Sesión");
		JMenuItem itemDetalles = new JMenuItem("Detalles de sesión");
		JMenuItem itemCerrar = new JMenuItem("Cerrar sesión");
		JMenuItem itemSalir = new JMenuItem("Salir");

		menuSesion.add(itemDetalles);
		menuSesion.add(itemCerrar);
		menuSesion.addSeparator();
		menuSesion.add(itemSalir);
		menuBar.add(menuSesion);

		itemDetalles.addActionListener(
				e -> JOptionPane.showMessageDialog(this, "Email: " + emailSesion + "\nAccess Token: " + tokenSesion,
						"Detalles de sesión", JOptionPane.INFORMATION_MESSAGE));

		itemCerrar.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Sesión cerrada. Regresando al login...");
			dispose();
			// hacer login
		});

		itemSalir.addActionListener(e -> System.exit(0));

		// -------- MENU ACCIONES --------

		JMenu menuAcciones = new JMenu("Acciones");
		JMenuItem itemValidar = new JMenuItem("Validación datos del trabajador");
		JMenuItem itemLimpiar = new JMenuItem("Limpiar datos del trabajador");
		JMenuItem itemAdd = new JMenuItem("Añadir trabajador");
		JMenuItem itemDel = new JMenuItem("Eliminar trabajador");

		menuAcciones.add(itemValidar);
		menuAcciones.add(itemLimpiar);
		menuAcciones.add(itemAdd);
		menuAcciones.add(itemDel);
		menuBar.add(menuAcciones);

		itemValidar.addActionListener(e -> validar());
		itemLimpiar.addActionListener(e -> limpiarDatos());
		itemAdd.addActionListener(e -> btnAddTrabajador.doClick());
		itemDel.addActionListener(e -> btnDelTrabajador.doClick());

		// -------- MENU MODO --------

		JMenu menuModo = new JMenu("Modo");
		JCheckBoxMenuItem modoOffline = new JCheckBoxMenuItem("Offline", true);
		JCheckBoxMenuItem modoOnline = new JCheckBoxMenuItem("Online", false);

		menuModo.add(modoOffline);
		menuModo.add(modoOnline);
		menuBar.add(menuModo);

		modoOffline.addActionListener(e -> {
			if (modoOffline.isSelected())
				modoOnline.setSelected(false);
		});

		modoOnline.addActionListener(e -> {
			if (modoOnline.isSelected())
				modoOffline.setSelected(false);
		});

		// --------------------- LAYOUT PRINCIPAL ----------------------

		JPanel contentPane = new JPanel(new GridLayout(1, 2));
		setContentPane(contentPane);

		// ----------------------- PANEL IZQUIERDO ---------------------

		JPanel izquierda = new JPanel(new GridBagLayout());
		contentPane.add(izquierda);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// 1. IDENTIFICACION

		JPanel panelId = new JPanel(new GridLayout(4, 2, 5, 5));
		panelId.setBorder(new TitledBorder("Identificación del trabajador"));

		panelId.add(new JLabel("DNI:"));
		textDNI = new JTextField();
		panelId.add(textDNI);

		panelId.add(new JLabel("Nombre:"));
		textNombre = new JTextField();
		panelId.add(textNombre);

		panelId.add(new JLabel("Apellido 1:"));
		textAP1 = new JTextField();
		panelId.add(textAP1);

		panelId.add(new JLabel("Apellido 2:"));
		textAP2 = new JTextField();
		panelId.add(textAP2);

		gbc.gridx = 0;
		gbc.gridy = 0;
		izquierda.add(panelId, gbc);

		// 2. PROVINCIAS

		JPanel panelProv = new JPanel(new GridBagLayout());
		panelProv.setBorder(new TitledBorder("Provincia del trabajador"));

		comboBox = new JComboBox<>();
		comboBox.setSelectedIndex(-1);

		btnEliminarProvincia = new JButton("Eliminar provincia");
		btnEliminarProvincia.setEnabled(false);

		JButton btnAddProv = new JButton("Añadir provincia");

		GridBagConstraints g2 = new GridBagConstraints();
		g2.insets = new Insets(5, 5, 5, 5);
		g2.fill = GridBagConstraints.HORIZONTAL;

		g2.gridx = 0;
		g2.gridy = 0;
		g2.weightx = 1;
		panelProv.add(comboBox, g2);

		g2.gridx = 1;
		g2.gridy = 0;
		panelProv.add(btnEliminarProvincia, g2);

		g2.gridx = 0;
		g2.gridy = 1;
		g2.gridwidth = 2;
		panelProv.add(btnAddProv, g2);

		gbc.gridy = 1;
		izquierda.add(panelProv, gbc);

		// 3. PROFESIONES

		JPanel panelProf = new JPanel(new GridBagLayout());
		panelProf.setBorder(new TitledBorder("Profesión del trabajador"));

		modeloProfesiones = new DefaultListModel<>();
		JList<String> listaProfesiones = new JList<>(modeloProfesiones);
		JScrollPane scrollProf = new JScrollPane(listaProfesiones);

		btnEliminarProfesion = new JButton("Eliminar profesión");
		btnEliminarProfesion.setEnabled(false);

		textProfesion = new JTextField();
		JButton btnAddProf = new JButton("Añadir profesión");

		GridBagConstraints g3 = new GridBagConstraints();
		g3.insets = new Insets(5, 5, 5, 5);

		g3.gridx = 0;
		g3.gridy = 0;
		g3.weightx = 1;
		g3.weighty = 1;
		g3.fill = GridBagConstraints.BOTH;
		panelProf.add(scrollProf, g3);

		g3.gridx = 1;
		g3.gridy = 0;
		g3.weightx = 0;
		g3.fill = GridBagConstraints.HORIZONTAL;
		panelProf.add(btnEliminarProfesion, g3);

		g3.gridx = 0;
		g3.gridy = 1;
		g3.weightx = 1;
		g3.fill = GridBagConstraints.HORIZONTAL;
		panelProf.add(textProfesion, g3);

		g3.gridx = 1;
		g3.gridy = 1;
		panelProf.add(btnAddProf, g3);

		gbc.gridy = 2;
		izquierda.add(panelProf, gbc);

		// 4. BOTÓN AÑADIR TRABAJADOR

		btnAddTrabajador = new JButton("Añadir trabajador");
		btnAddTrabajador.setEnabled(false);
		gbc.gridy = 3;
		izquierda.add(btnAddTrabajador, gbc);

		// ----------------------- PANEL DERECHA -----------------------

		JPanel derecha = new JPanel(new GridBagLayout());
		contentPane.add(derecha);

		// TABLA

		modeloTrabajadores = new DefaultTableModel(
				new Object[] { "Nombre y apellidos", "Provincia", "Profesión", "DNI" }, 0);

		tablaTrabajadores = new JTable(modeloTrabajadores);
		JScrollPane scrollTabla = new JScrollPane(tablaTrabajadores);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		derecha.add(scrollTabla, gbc);

		// BOTÓN ELIMINAR

		btnDelTrabajador = new JButton("Eliminar trabajador");
		btnDelTrabajador.setEnabled(false);

		gbc.gridy = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		derecha.add(btnDelTrabajador, gbc);

		// DETALLE TRABAJADOR

		JPanel panelDetalle = new JPanel(new BorderLayout());
		panelDetalle.setBorder(new TitledBorder("Detalle del trabajador"));

		textDetalle = new JTextArea();
		textDetalle.setEditable(false);
		panelDetalle.add(textDetalle);

		gbc.gridy = 2;
		gbc.weighty = 0.4;
		gbc.fill = GridBagConstraints.BOTH;
		derecha.add(panelDetalle, gbc);

		// ---------------- EVENTOS Y LÓGICA --------------------------

		// Provincias

		comboBox.addActionListener(e -> btnEliminarProvincia.setEnabled(comboBox.getSelectedIndex() != -1));

		btnEliminarProvincia.addActionListener(e -> {
			if (comboBox.getSelectedIndex() != -1)
				comboBox.removeItemAt(comboBox.getSelectedIndex());
			comboBox.setSelectedIndex(-1);
			comprobarEstadoBtnTrabajador();
		});

		btnAddProv.addActionListener(e -> {
			DlgAddProvincia dlg = new DlgAddProvincia(this);
			dlg.setVisible(true);
			String provincia = dlg.getProvinciaSeleccionada();
			if (provincia != null) {
				DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
				if (model.getIndexOf(provincia) == -1)
					comboBox.addItem(provincia);
			}
			comprobarEstadoBtnTrabajador();
		});

		// Profesiones

		listaProfesiones.addListSelectionListener(
				e -> btnEliminarProfesion.setEnabled(listaProfesiones.getSelectedIndex() != -1));

		btnAddProf.addActionListener(e -> {
			String prof = textProfesion.getText().trim();
			if (prof.isEmpty()) {
				JOptionPane.showMessageDialog(this, "La profesión no puede estar vacía.");
				return;
			}
			if (modeloProfesiones.contains(prof)) {
				JOptionPane.showMessageDialog(this, "La profesión ya existe en la lista.");
				return;
			}
			modeloProfesiones.addElement(prof);
			textProfesion.setText("");
			comprobarEstadoBtnTrabajador();
		});

		btnEliminarProfesion.addActionListener(e -> {
			int index = listaProfesiones.getSelectedIndex();
			if (index != -1)
				modeloProfesiones.remove(index);
			comprobarEstadoBtnTrabajador();
		});

		// Habilitar botón añadir trabajador

		DocumentListener doc = new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				comprobarEstadoBtnTrabajador();
			}

			public void removeUpdate(DocumentEvent e) {
				comprobarEstadoBtnTrabajador();
			}

			public void changedUpdate(DocumentEvent e) {
				comprobarEstadoBtnTrabajador();
			}
		};

		textDNI.getDocument().addDocumentListener(doc);
		textNombre.getDocument().addDocumentListener(doc);
		textAP1.getDocument().addDocumentListener(doc);
		textAP2.getDocument().addDocumentListener(doc);

		// Añadir trabajador

		btnAddTrabajador.addActionListener(e -> {
			if (!validar())
				return;
			if (!dniUnico())
				return;

			String nombreCompleto = textNombre.getText().trim() + " " + textAP1.getText().trim() + " "
					+ textAP2.getText().trim();

			modeloTrabajadores.addRow(new Object[] { nombreCompleto, comboBox.getSelectedItem(),
					modeloProfesiones.getElementAt(0), textDNI.getText().trim() });

			actualizarDetalle(null);
			limpiarDatos();
			comprobarEstadoBtnTrabajador();

			JOptionPane.showMessageDialog(this, "Trabajador añadido correctamente.");
		});

		// Tabla

		tablaTrabajadores.getSelectionModel().addListSelectionListener(e -> {
			int fila = tablaTrabajadores.getSelectedRow();
			btnDelTrabajador.setEnabled(fila != -1);

			if (fila != -1) {
				actualizarDetalle(fila);
			}
		});

		// Eliminar trabajador

		btnDelTrabajador.addActionListener(e -> {
			int fila = tablaTrabajadores.getSelectedRow();
			if (fila != -1) {
				modeloTrabajadores.removeRow(fila);
				actualizarDetalle(null);
			}
		});
	}

	// --------------------- VALIDACIONES Y UTILIDADES ----------------------------

	private boolean validar() {
		String msg = "";

		if (!textDNI.getText().matches("^[0-9]{8}[A-Z]$"))
			msg += "\n - DNI inválido (8 dígitos + letra mayúscula)";

		if (!textNombre.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+$"))
			msg += "\n - Nombre inválido";

		if (!textAP1.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+$"))
			msg += "\n - Apellido 1 inválido";

		if (!textAP2.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+$"))
			msg += "\n - Apellido 2 inválido";

		if (!msg.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Errores:\n" + msg);
			return false;
		}
		return true;
	}

	private boolean dniUnico() {
		String dni = textDNI.getText().trim();
		for (int i = 0; i < modeloTrabajadores.getRowCount(); i++) {
			if (modeloTrabajadores.getValueAt(i, 3).equals(dni)) {
				JOptionPane.showMessageDialog(this, "El DNI ya existe.");
				return false;
			}
		}
		return true;
	}

	private void comprobarEstadoBtnTrabajador() {
		boolean ok = true;
		if (textDNI.getText().trim().isEmpty())
			ok = false;
		if (textNombre.getText().trim().isEmpty())
			ok = false;
		if (textAP1.getText().trim().isEmpty())
			ok = false;
		if (textAP2.getText().trim().isEmpty())
			ok = false;
		if (comboBox.getSelectedIndex() == -1)
			ok = false;
		if (modeloProfesiones.isEmpty())
			ok = false;
		btnAddTrabajador.setEnabled(ok);
	}

	private void limpiarDatos() {
		textDNI.setText("");
		textNombre.setText("");
		textAP1.setText("");
		textAP2.setText("");
		comboBox.setSelectedIndex(-1);
		modeloProfesiones.clear();
		textProfesion.setText("");
	}

	private void actualizarDetalle(Integer fila) {
		if (fila == null) {
			textDetalle.setText("");
			return;
		}

		String nombre = modeloTrabajadores.getValueAt(fila, 0).toString();
		String provincia = modeloTrabajadores.getValueAt(fila, 1).toString();
		String profesion = modeloTrabajadores.getValueAt(fila, 2).toString();
		String dni = modeloTrabajadores.getValueAt(fila, 3).toString();

		textDetalle.setText(dni + " - " + nombre + " - " + provincia + " - " + profesion);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new FrmPrincipal().setVisible(true));
	}
}
