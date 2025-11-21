package examenUD1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;

import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ItemEvent;
import javax.swing.JMenuItem;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Usuario> comboBox;
	private JPanel panelControlAcceso;
	private JButton btnEntra;
	private JButton btnSale;
	private JButton btnReporte;
	private JButton btnRegistro;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JPanel panelzq;
	private JPanel panelPersonasIn;
	private JPanel panelDer;
	JTextArea textHistorial;
	private JButton btnLimpiarHistorial;

	List<Usuario> usuariosDentro = new ArrayList<>();
	DefaultComboBoxModel<Usuario> modeloUsuarios;
	boolean isReporteAbierto = false;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setMinimumSize(new Dimension(1000, 420));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 541);
		modeloUsuarios = new DefaultComboBoxModel<Usuario>();

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("Menú");
		menuBar.add(mnNewMenu);

		mnNewMenu_1 = new JMenu("Control de acceso");
		mnNewMenu.add(mnNewMenu_1);

		mntmNewMenuItem_1 = new JMenuItem("Registro");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgRegistro dlgRegistro = new DlgRegistro(FrmPrincipal.this);
				dlgRegistro.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);

		mntmNewMenuItem_2 = new JMenuItem("Reporte");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgReporte dlgReporte = new DlgReporte(FrmPrincipal.this);
				if (!isReporteAbierto) {
					dlgReporte.setVisible(true);
					isReporteAbierto = true;
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);

		mntmNewMenuItem = new JMenuItem("Limpiar historial");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textHistorial.setText("");
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		panelzq = new JPanel();
		contentPane.add(panelzq);
		GridBagLayout gbl_panelzq = new GridBagLayout();
		gbl_panelzq.columnWidths = new int[] { 340, 0 };
		gbl_panelzq.rowHeights = new int[] { 117, 0, 33, 0 };
		gbl_panelzq.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelzq.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelzq.setLayout(gbl_panelzq);

		panelControlAcceso = new JPanel();
		panelControlAcceso.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Control de acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelControlAcceso = new GridBagConstraints();
		gbc_panelControlAcceso.fill = GridBagConstraints.BOTH;
		gbc_panelControlAcceso.insets = new Insets(0, 0, 5, 0);
		gbc_panelControlAcceso.gridx = 0;
		gbc_panelControlAcceso.gridy = 0;
		panelzq.add(panelControlAcceso, gbc_panelControlAcceso);
		GridBagLayout gbl_panelControlAcceso = new GridBagLayout();
		gbl_panelControlAcceso.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelControlAcceso.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panelControlAcceso.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelControlAcceso.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelControlAcceso.setLayout(gbl_panelControlAcceso);

		comboBox = new JComboBox<>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				combrobarBotonesEntraSale();
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		comboBox.setModel(modeloUsuarios);
		comboBox.setSelectedIndex(-1);
		panelControlAcceso.add(comboBox, gbc_comboBox);

		btnEntra = new JButton("Entra");
		btnEntra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = (Usuario) comboBox.getSelectedItem();
				user.setDentro(true);
				String textoDentro = "";
				usuariosDentro.add(user);
				for (Usuario u : usuariosDentro) {
					if (u.isDentro()) {
						textoDentro += u.toString() + "\n";
					}
				}
				textArea.setText(textoDentro);
				textHistorial.setText(textHistorial.getText() + "\n" + user.toString());
				comboBox.setSelectedIndex(-1);
				combrobarBotonesEntraSale();
			}
		});
		btnEntra.setEnabled(false);
		GridBagConstraints gbc_btnEntra = new GridBagConstraints();
		gbc_btnEntra.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEntra.insets = new Insets(0, 0, 5, 5);
		gbc_btnEntra.gridx = 0;
		gbc_btnEntra.gridy = 1;
		panelControlAcceso.add(btnEntra, gbc_btnEntra);

		btnSale = new JButton("Sale");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = (Usuario) comboBox.getSelectedItem();
				user.setDentro(false);
				String textoDentro = "";
				usuariosDentro.remove(user);
				for (Usuario u : usuariosDentro) {
					if (u.isDentro()) {
						textoDentro += u.toString() + "\n";
					}
				}
				textArea.setText(textoDentro);
				textHistorial.setText(textHistorial.getText() + "\n" + user.toString());
				comboBox.setSelectedIndex(-1);
				combrobarBotonesEntraSale();
			}
		});
		btnSale.setEnabled(false);
		GridBagConstraints gbc_btnSale = new GridBagConstraints();
		gbc_btnSale.insets = new Insets(0, 0, 5, 0);
		gbc_btnSale.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSale.gridx = 1;
		gbc_btnSale.gridy = 1;
		panelControlAcceso.add(btnSale, gbc_btnSale);

		btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgReporte dlgReporte = new DlgReporte(FrmPrincipal.this);
				if (!isReporteAbierto) {
					dlgReporte.setVisible(true);
					isReporteAbierto = true;
				}
			}
		});
		GridBagConstraints gbc_btnReporte = new GridBagConstraints();
		gbc_btnReporte.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReporte.gridwidth = 2;
		gbc_btnReporte.insets = new Insets(0, 0, 0, 5);
		gbc_btnReporte.gridx = 0;
		gbc_btnReporte.gridy = 2;
		panelControlAcceso.add(btnReporte, gbc_btnReporte);

		btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgRegistro dlgRegistro = new DlgRegistro(FrmPrincipal.this);
				dlgRegistro.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
		gbc_btnRegistro.anchor = GridBagConstraints.NORTH;
		gbc_btnRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegistro.insets = new Insets(0, 0, 5, 0);
		gbc_btnRegistro.gridx = 0;
		gbc_btnRegistro.gridy = 1;
		panelzq.add(btnRegistro, gbc_btnRegistro);

		panelPersonasIn = new JPanel();
		panelPersonasIn.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Personas en el gimnasio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelPersonasIn = new GridBagConstraints();
		gbc_panelPersonasIn.fill = GridBagConstraints.BOTH;
		gbc_panelPersonasIn.gridx = 0;
		gbc_panelPersonasIn.gridy = 2;
		panelzq.add(panelPersonasIn, gbc_panelPersonasIn);
		panelPersonasIn.setLayout(new GridLayout(0, 1, 0, 0));

		textArea = new JTextArea();
		panelPersonasIn.add(textArea);

		panelDer = new JPanel();
		panelDer.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Histrorial",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelDer);
		GridBagLayout gbl_panelDer = new GridBagLayout();
		gbl_panelDer.columnWidths = new int[] { 0, 0 };
		gbl_panelDer.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelDer.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelDer.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panelDer.setLayout(gbl_panelDer);

		textHistorial = new JTextArea();
		GridBagConstraints gbc_textHistorial = new GridBagConstraints();
		gbc_textHistorial.insets = new Insets(0, 0, 5, 0);
		gbc_textHistorial.fill = GridBagConstraints.BOTH;
		gbc_textHistorial.gridx = 0;
		gbc_textHistorial.gridy = 0;
		panelDer.add(textHistorial, gbc_textHistorial);

		btnLimpiarHistorial = new JButton("Limpiar historial");
		btnLimpiarHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textHistorial.setText("");
			}
		});
		GridBagConstraints gbc_btnLimpiarHistorial = new GridBagConstraints();
		gbc_btnLimpiarHistorial.anchor = GridBagConstraints.NORTH;
		gbc_btnLimpiarHistorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLimpiarHistorial.gridx = 0;
		gbc_btnLimpiarHistorial.gridy = 1;
		panelDer.add(btnLimpiarHistorial, gbc_btnLimpiarHistorial);

	}

	void combrobarBotonesEntraSale() {

		if (comboBox.getSelectedIndex() == -1) {
			btnEntra.setEnabled(false);
			btnSale.setEnabled(false);
			return;
		}
		Usuario user = (Usuario) comboBox.getSelectedItem();

		if (user.isDentro()) {
			btnEntra.setEnabled(false);
			btnSale.setEnabled(true);
		} else {
			btnEntra.setEnabled(true);
			btnSale.setEnabled(false);
		}

	}

}
