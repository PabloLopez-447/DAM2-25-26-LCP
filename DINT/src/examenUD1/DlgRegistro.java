package examenUD1;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;

import javax.swing.border.BevelBorder;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class DlgRegistro extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textEdad;
	private JButton btnRegistrar;
	private JButton btnValidar;
	private JCheckBox chckbxForzarVal;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;

	/**
	 * Create the dialog.
	 */
	public DlgRegistro(FrmPrincipal parent) {
		setMinimumSize(new Dimension(650, 300));
		setBounds(100, 100, 628, 293);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setMinimumSize(new Dimension(0, 0));
		contentPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Registro ususario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("DNI");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			textDNI = new JTextField();
			GridBagConstraints gbc_textDNI = new GridBagConstraints();
			gbc_textDNI.insets = new Insets(0, 0, 5, 5);
			gbc_textDNI.fill = GridBagConstraints.HORIZONTAL;
			gbc_textDNI.gridx = 2;
			gbc_textDNI.gridy = 0;
			contentPanel.add(textDNI, gbc_textDNI);
			textDNI.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			textNombre = new JTextField();
			GridBagConstraints gbc_textNombre = new GridBagConstraints();
			gbc_textNombre.insets = new Insets(0, 0, 5, 5);
			gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_textNombre.gridx = 2;
			gbc_textNombre.gridy = 1;
			contentPanel.add(textNombre, gbc_textNombre);
			textNombre.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Apellidos");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 1;
			gbc_lblNewLabel_2.gridy = 2;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			textApellidos = new JTextField();
			GridBagConstraints gbc_textApellidos = new GridBagConstraints();
			gbc_textApellidos.insets = new Insets(0, 0, 5, 5);
			gbc_textApellidos.fill = GridBagConstraints.HORIZONTAL;
			gbc_textApellidos.gridx = 2;
			gbc_textApellidos.gridy = 2;
			contentPanel.add(textApellidos, gbc_textApellidos);
			textApellidos.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Edad");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 1;
			gbc_lblNewLabel_3.gridy = 3;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			textEdad = new JTextField();
			GridBagConstraints gbc_textEdad = new GridBagConstraints();
			gbc_textEdad.insets = new Insets(0, 0, 5, 5);
			gbc_textEdad.fill = GridBagConstraints.HORIZONTAL;
			gbc_textEdad.gridx = 2;
			gbc_textEdad.gridy = 3;
			contentPanel.add(textEdad, gbc_textEdad);
			textEdad.setColumns(10);
		}
		{
			chckbxForzarVal = new JCheckBox("Forzar validación positiva");
			GridBagConstraints gbc_chckbxForzarVal = new GridBagConstraints();
			gbc_chckbxForzarVal.anchor = GridBagConstraints.EAST;
			gbc_chckbxForzarVal.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxForzarVal.gridx = 2;
			gbc_chckbxForzarVal.gridy = 4;
			contentPanel.add(chckbxForzarVal, gbc_chckbxForzarVal);
		}
		{
			btnValidar = new JButton("Validar datos");
			btnValidar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (validar()) {
						btnRegistrar.setEnabled(true);
						mntmNewMenuItem_1.setEnabled(true);
						JOptionPane.showMessageDialog(btnValidar, "Validado correctamente.");
					}
					if (chckbxForzarVal.isSelected()) {
						btnRegistrar.setEnabled(true);
						JOptionPane.showMessageDialog(btnValidar, "Validado correctamente.", "",
								JOptionPane.WARNING_MESSAGE);
						btnRegistrar.setEnabled(true);
						mntmNewMenuItem_1.setEnabled(true);
					}

				}
			});
			GridBagConstraints gbc_btnValidar = new GridBagConstraints();
			gbc_btnValidar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnValidar.gridwidth = 2;
			gbc_btnValidar.insets = new Insets(0, 0, 5, 5);
			gbc_btnValidar.gridx = 1;
			gbc_btnValidar.gridy = 5;
			contentPanel.add(btnValidar, gbc_btnValidar);
		}
		{
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parent.modeloUsuarios.addElement(crearUsuario());
					dispose();
				}
			});
			btnRegistrar.setEnabled(false);
			GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
			gbc_btnRegistrar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnRegistrar.gridwidth = 2;
			gbc_btnRegistrar.insets = new Insets(0, 0, 0, 5);
			gbc_btnRegistrar.gridx = 1;
			gbc_btnRegistrar.gridy = 6;
			contentPanel.add(btnRegistrar, gbc_btnRegistrar);
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnNewMenu = new JMenu("Menú");
				menuBar.add(mnNewMenu);
				{
					mntmNewMenuItem = new JMenuItem("Validar datos");
					mntmNewMenuItem.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (validar()) {
								btnRegistrar.setEnabled(true);
								mntmNewMenuItem_1.setEnabled(true);
								JOptionPane.showMessageDialog(btnValidar, "Validado correctamente.");
							}
							if (chckbxForzarVal.isSelected()) {
								btnRegistrar.setEnabled(true);
								JOptionPane.showMessageDialog(btnValidar, "Validado correctamente.", "",
										JOptionPane.WARNING_MESSAGE);
								btnRegistrar.setEnabled(true);
								mntmNewMenuItem_1.setEnabled(true);
							}
						}
					});
					mnNewMenu.add(mntmNewMenuItem);
				}
				{
					mntmNewMenuItem_1 = new JMenuItem("Registrar");
					mntmNewMenuItem_1.setEnabled(false);
					mntmNewMenuItem_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							parent.modeloUsuarios.addElement(crearUsuario());
							dispose();
						}
					});

					mnNewMenu.add(mntmNewMenuItem_1);
				}
			}
		}
	}

	private boolean validar() {
		String msg = "";

		if (!textDNI.getText().matches("^[0-9]{8}[A-Z]$") || textDNI.getText() == null)
			msg += "\n - DNI inválido (8 dígitos + letra mayúscula)";

		if (!textNombre.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+$") || textNombre.getText() == null)
			msg += "\n - Nombre inválido";

		if (!textApellidos.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\s]+$") || textApellidos.getText() == null)
			msg += "\n - Apellidos inválidos";

		if (!textEdad.getText().matches("^[0-9]+$") || textEdad.getText() == null)
			msg += "\n - Edad inválida";
		else if (Integer.parseInt(textEdad.getText()) > 100 || Integer.parseInt(textEdad.getText()) < 0)
			msg += "\n Edad inválida";

		if (!msg.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Errores:\n" + msg);
			return false;
		}
		return true;
	}

	public Usuario crearUsuario() {
		String dni, nombre, apellidos;
		int edad;

		dni = textDNI.getText();
		nombre = textNombre.getText();
		apellidos = textApellidos.getText();
		edad = Integer.valueOf(textEdad.getText());

		return new Usuario(dni, nombre, apellidos, edad);

	}

}
