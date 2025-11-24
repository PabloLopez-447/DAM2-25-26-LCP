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
	JMenuItem itemAdd;

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
		});

		itemSalir.addActionListener(e -> System.exit(0));

		// -------- MENU ACCIONES --------

		JMenu menuAcciones = new JMenu("Acciones");
		JMenuItem itemValidar = new JMenuItem("Validación datos del trabajador");
		JMenuItem itemLimpiar = new JMenuItem("Limpiar datos del trabajador");
		itemAdd = new JMenuItem("Añadir trabajador");
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

		GridBagConstraints gbc_id = new GridBagConstraints();
		gbc_id.insets = new Insets(5, 5, 5, 5);
		gbc_id.fill = GridBagConstraints.HORIZONTAL;
		gbc_id.gridx = 0;
		gbc_id.gridy = 0;
		izquierda.add(panelId, gbc_id);

		// 2. PROVINCIAS

		JPanel panelProv = new JPanel(new GridBagLayout());
		panelProv.setBorder(new TitledBorder("Provincia del trabajador"));

		comboBox = new JComboBox<>();
		comboBox.setSelectedIndex(-1);

		btnEliminarProvincia = new JButton("Eliminar provincia");
		btnEliminarProvincia.setEnabled(false);

		JButton btnAddProv = new JButton("Añadir provincia");

		// -- ComboBox --
		GridBagConstraints gbc_cb = new GridBagConstraints();
		gbc_cb.insets = new Insets(5, 5, 5, 5);
		gbc_cb.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb.gridx = 0;
		gbc_cb.gridy = 0;
		gbc_cb.weightx = 1;
		panelProv.add(comboBox, gbc_cb);

		// -- Botón eliminar provincia --
		GridBagConstraints gbc_btnDelProv = new GridBagConstraints();
		gbc_btnDelProv.insets = new Insets(5, 5, 5, 5);
		gbc_btnDelProv.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelProv.gridx = 1;
		gbc_btnDelProv.gridy = 0;
		panelProv.add(btnEliminarProvincia, gbc_btnDelProv);

		// -- Botón añadir provincia --
		GridBagConstraints gbc_btnAddProv = new GridBagConstraints();
		gbc_btnAddProv.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddProv.insets = new Insets(5, 5, 5, 5);
		gbc_btnAddProv.gridx = 0;
		gbc_btnAddProv.gridy = 1;
		gbc_btnAddProv.gridwidth = 2;
		panelProv.add(btnAddProv, gbc_btnAddProv);

		// Añadir panelProv a la izquierda
		GridBagConstraints gbc_prov = new GridBagConstraints();
		gbc_prov.insets = new Insets(5, 5, 5, 5);
		gbc_prov.fill = GridBagConstraints.HORIZONTAL;
		gbc_prov.gridx = 0;
		gbc_prov.gridy = 1;
		izquierda.add(panelProv, gbc_prov);

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

		// --- Scroll profesiones ---
		GridBagConstraints gbc_scrollProf = new GridBagConstraints();
		gbc_scrollProf.insets = new Insets(5, 5, 5, 5);
		gbc_scrollProf.gridx = 0;
		gbc_scrollProf.gridy = 0;
		gbc_scrollProf.weightx = 1;
		gbc_scrollProf.weighty = 1;
		gbc_scrollProf.fill = GridBagConstraints.BOTH;
		panelProf.add(scrollProf, gbc_scrollProf);

		// --- Botón eliminar profesion ---
		GridBagConstraints gbc_btnElimProf = new GridBagConstraints();
		gbc_btnElimProf.insets = new Insets(5, 5, 5, 5);
		gbc_btnElimProf.gridx = 1;
		gbc_btnElimProf.gridy = 0;
		gbc_btnElimProf.fill = GridBagConstraints.HORIZONTAL;
		panelProf.add(btnEliminarProfesion, gbc_btnElimProf);

		// --- Texto profesión ---
		GridBagConstraints gbc_textProf = new GridBagConstraints();
		gbc_textProf.insets = new Insets(5, 5, 5, 5);
		gbc_textProf.gridx = 0;
		gbc_textProf.gridy = 1;
		gbc_textProf.weightx = 1;
		gbc_textProf.fill = GridBagConstraints.HORIZONTAL;
		panelProf.add(textProfesion, gbc_textProf);

		// --- Botón añadir profesión ---
		GridBagConstraints gbc_btnAddProf2 = new GridBagConstraints();
		gbc_btnAddProf2.insets = new Insets(5, 5, 5, 5);
		gbc_btnAddProf2.gridx = 1;
		gbc_btnAddProf2.gridy = 1;
		gbc_btnAddProf2.fill = GridBagConstraints.HORIZONTAL;
		panelProf.add(btnAddProf, gbc_btnAddProf2);

		// --- Añadir panelProf a la izquierda ---
		GridBagConstraints gbc_prof = new GridBagConstraints();
		gbc_prof.insets = new Insets(5, 5, 5, 5);
		gbc_prof.fill = GridBagConstraints.HORIZONTAL;
		gbc_prof.gridx = 0;
		gbc_prof.gridy = 2;
		izquierda.add(panelProf, gbc_prof);

		// 4. BOTÓN AÑADIR TRABAJADOR

		btnAddTrabajador = new JButton("Añadir trabajador");
		btnAddTrabajador.setEnabled(false);

		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(5, 5, 5, 5);
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 3;
		izquierda.add(btnAddTrabajador, gbc_btnAdd);

		// ----------------------- PANEL DERECHA -----------------------

		JPanel derecha = new JPanel(new GridBagLayout());
		contentPane.add(derecha);

		// TABLA

		modeloTrabajadores = new DefaultTableModel(
				new Object[] { "Nombre y apellidos", "Provincia", "Profesión", "DNI" }, 0);

		tablaTrabajadores = new JTable(modeloTrabajadores);
		JScrollPane scrollTabla = new JScrollPane(tablaTrabajadores);

		GridBagConstraints gbc_tabla = new GridBagConstraints();
		gbc_tabla.insets = new Insets(5, 5, 5, 5);
		gbc_tabla.gridx = 0;
		gbc_tabla.gridy = 0;
		gbc_tabla.weightx = 1;
		gbc_tabla.weighty = 1;
		gbc_tabla.fill = GridBagConstraints.BOTH;
		derecha.add(scrollTabla, gbc_tabla);

		// BOTÓN ELIMINAR

		btnDelTrabajador = new JButton("Eliminar trabajador");
		btnDelTrabajador.setEnabled(false);

		GridBagConstraints gbc_btnDelTrab = new GridBagConstraints();
		gbc_btnDelTrab.insets = new Insets(5, 5, 5, 5);
		gbc_btnDelTrab.gridx = 0;
		gbc_btnDelTrab.gridy = 1;
		gbc_btnDelTrab.fill = GridBagConstraints.HORIZONTAL;
		derecha.add(btnDelTrabajador, gbc_btnDelTrab);

		// DETALLE TRABAJADOR

		JPanel panelDetalle = new JPanel(new BorderLayout());
		panelDetalle.setBorder(new TitledBorder("Detalle del trabajador"));

		textDetalle = new JTextArea();
		textDetalle.setEditable(false);
		panelDetalle.add(textDetalle);

		GridBagConstraints gbc_detalle = new GridBagConstraints();
		gbc_detalle.insets = new Insets(5, 5, 5, 5);
		gbc_detalle.gridx = 0;
		gbc_detalle.gridy = 2;
		gbc_detalle.weighty = 0.4;
		gbc_detalle.fill = GridBagConstraints.BOTH;
		derecha.add(panelDetalle, gbc_detalle);

		// ---------------- EVENTOS Y LÓGICA --------------------------

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

		tablaTrabajadores.getSelectionModel().addListSelectionListener(e -> {
			int fila = tablaTrabajadores.getSelectedRow();
			btnDelTrabajador.setEnabled(fila != -1);

			if (fila != -1) {
				actualizarDetalle(fila);
			}
		});

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

		if (!textDNI.getText().matches("^[0-9]{8}[A-Z]$") || textDNI.getText() == null)
			msg += "\n - DNI inválido (8 dígitos + letra mayúscula)";

		if (!textNombre.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+$") || textNombre.getText() == null)
			msg += "\n - Nombre inválido";

		if (!textAP1.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+$") || textAP1.getText() == null)
			msg += "\n - Apellido 1 inválido";

		if (!textAP2.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+$") || textAP2.getText() == null)
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
		itemAdd.setEnabled(ok);
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
