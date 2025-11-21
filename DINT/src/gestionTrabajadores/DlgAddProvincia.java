package gestionTrabajadores;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;

public class DlgAddProvincia extends JDialog {

	private static final long serialVersionUID = 1L;

	private String provinciaSeleccionada = null;

	private final String[] provincias = { "Álava", "Albacete", "Alicante", "Almería", "Asturias", "Ávila", "Badajoz",
			"Barcelona", "Burgos", "Cáceres", "Cádiz", "Cantabria", "Castellón", "Ciudad Real", "Córdoba", "Cuenca",
			"Girona", "Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca", "Islas Baleares", "Jaén", "La Coruña",
			"La Rioja", "Las Palmas", "León", "Lleida", "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Ourense",
			"Palencia", "Pontevedra", "Salamanca", "Santa Cruz de Tenerife", "Segovia", "Sevilla", "Soria", "Tarragona",
			"Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza", "Ceuta", "Melilla" };

	public DlgAddProvincia(JFrame parent) {
		super(parent, "Seleccione una provincia", true);
		setSize(350, 150);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lbl = new JLabel("Seleccione una provincia");
		panel.add(lbl);

		JComboBox<String> combo = new JComboBox<>(provincias);
		combo.setSelectedIndex(-1);
		panel.add(combo);
		add(panel, BorderLayout.CENTER);

		JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnAceptar = new JButton("Aceptar");
		JButton btnCancelar = new JButton("Cancelar");

		btnAceptar.setEnabled(false);

		combo.addActionListener(e -> {
			if (combo.getSelectedIndex() != -1) {
				btnAceptar.setEnabled(true);
			}
		});

		btnAceptar.addActionListener(e -> {
			provinciaSeleccionada = (String) combo.getSelectedItem();
			dispose();
		});

		btnCancelar.addActionListener(e -> {
			provinciaSeleccionada = null;
			dispose();
		});

		botones.add(btnAceptar);
		botones.add(btnCancelar);
		add(botones, BorderLayout.SOUTH);
	}

	public String getProvinciaSeleccionada() {
		return provinciaSeleccionada;
	}
}
