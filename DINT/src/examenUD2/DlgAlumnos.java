package examenUD2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import examenUD1CopiaInformes.FrmPrincipal;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class DlgAlumnos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tf_alumno_dni;
	private JTextField tf_alumno_nombre;
	private JTextField tf_alumno_apellido;

	private javax.swing.table.DefaultTableModel modelAlumnos;
	private JTable table_alumnos;
	GestionCentro parent;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DlgAlumnos(GestionCentro parent) {
		this.parent = parent;

		setBounds(100, 100, 528, 351);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblAlumno = new JLabel("Registro de alumnos");
			GridBagConstraints gbc_lblAlumno = new GridBagConstraints();
			gbc_lblAlumno.insets = new Insets(0, 0, 5, 5);
			gbc_lblAlumno.gridx = 0;
			gbc_lblAlumno.gridy = 0;
			contentPanel.add(lblAlumno, gbc_lblAlumno);
		}
		{
			JLabel lb_alumno_dni = new JLabel("DNI");
			GridBagConstraints gbc_lb_alumno_dni = new GridBagConstraints();
			gbc_lb_alumno_dni.insets = new Insets(0, 0, 5, 5);
			gbc_lb_alumno_dni.anchor = GridBagConstraints.WEST;
			gbc_lb_alumno_dni.gridx = 0;
			gbc_lb_alumno_dni.gridy = 1;
			contentPanel.add(lb_alumno_dni, gbc_lb_alumno_dni);
		}
		{
			tf_alumno_dni = new JTextField();
			GridBagConstraints gbc_tf_alumno_dni = new GridBagConstraints();
			gbc_tf_alumno_dni.insets = new Insets(0, 0, 5, 0);
			gbc_tf_alumno_dni.fill = GridBagConstraints.HORIZONTAL;
			gbc_tf_alumno_dni.gridx = 1;
			gbc_tf_alumno_dni.gridy = 1;
			contentPanel.add(tf_alumno_dni, gbc_tf_alumno_dni);
		}
		{
			JLabel lb_alumno_nombre = new JLabel("Nombre");
			GridBagConstraints gbc_lb_alumno_nombre = new GridBagConstraints();
			gbc_lb_alumno_nombre.anchor = GridBagConstraints.WEST;
			gbc_lb_alumno_nombre.insets = new Insets(0, 0, 5, 5);
			gbc_lb_alumno_nombre.gridx = 0;
			gbc_lb_alumno_nombre.gridy = 2;
			contentPanel.add(lb_alumno_nombre, gbc_lb_alumno_nombre);
		}
		{
			tf_alumno_nombre = new JTextField();
			GridBagConstraints gbc_tf_alumno_nombre = new GridBagConstraints();
			gbc_tf_alumno_nombre.insets = new Insets(0, 0, 5, 0);
			gbc_tf_alumno_nombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_tf_alumno_nombre.gridx = 1;
			gbc_tf_alumno_nombre.gridy = 2;
			contentPanel.add(tf_alumno_nombre, gbc_tf_alumno_nombre);
		}
		{
			JLabel lb_alumno_apellido = new JLabel("Apellidos");
			GridBagConstraints gbc_lb_alumno_apellido = new GridBagConstraints();
			gbc_lb_alumno_apellido.anchor = GridBagConstraints.WEST;
			gbc_lb_alumno_apellido.insets = new Insets(0, 0, 5, 5);
			gbc_lb_alumno_apellido.gridx = 0;
			gbc_lb_alumno_apellido.gridy = 3;
			contentPanel.add(lb_alumno_apellido, gbc_lb_alumno_apellido);
		}
		{
			tf_alumno_apellido = new JTextField();
			GridBagConstraints gbc_tf_alumno_apellido = new GridBagConstraints();
			gbc_tf_alumno_apellido.insets = new Insets(0, 0, 5, 0);
			gbc_tf_alumno_apellido.fill = GridBagConstraints.HORIZONTAL;
			gbc_tf_alumno_apellido.gridx = 1;
			gbc_tf_alumno_apellido.gridy = 3;
			contentPanel.add(tf_alumno_apellido, gbc_tf_alumno_apellido);
		}
		{
			JButton btn_registrar_alumno = new JButton("Registrar alumno");
			btn_registrar_alumno.setEnabled(false);
			btn_registrar_alumno.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String dni = tf_alumno_dni.getText();
					String nombre = tf_alumno_nombre.getText();
					String apellidos = tf_alumno_apellido.getText();
					try (Connection con = parent.getConnection()) {
						String sql = "INSERT INTO alumnos (dni, nombre, apellidos) VALUES (?, ?, ?)";
						PreparedStatement pst = con.prepareStatement(sql);
						pst.setString(1, dni);
						pst.setString(2, nombre);
						pst.setString(3, apellidos);
						pst.executeUpdate();
						System.out.println("Alumno registrado correctamente");
						actualizarTabla();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			});
			{
				JButton btnValidar = new JButton("Validar Datos");
				btnValidar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (validar()) {
							btn_registrar_alumno.setEnabled(true);
							JOptionPane.showMessageDialog(btnValidar, "Validado correctamente.");
						}
					}
				});
				GridBagConstraints gbc_btnValidar = new GridBagConstraints();
				gbc_btnValidar.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnValidar.insets = new Insets(0, 0, 5, 0);
				gbc_btnValidar.gridx = 1;
				gbc_btnValidar.gridy = 4;
				contentPanel.add(btnValidar, gbc_btnValidar);
			}
			GridBagConstraints gbc_btn_registrar_alumno = new GridBagConstraints();
			gbc_btn_registrar_alumno.fill = GridBagConstraints.HORIZONTAL;
			gbc_btn_registrar_alumno.insets = new Insets(0, 0, 5, 0);
			gbc_btn_registrar_alumno.gridx = 1;
			gbc_btn_registrar_alumno.gridy = 5;
			contentPanel.add(btn_registrar_alumno, gbc_btn_registrar_alumno);
		}
		{
			JScrollPane scrollAlumnos = new JScrollPane();
			GridBagConstraints gbc_scrollAlumnos = new GridBagConstraints();
			gbc_scrollAlumnos.insets = new Insets(0, 0, 5, 0);
			gbc_scrollAlumnos.gridwidth = 2;
			gbc_scrollAlumnos.fill = GridBagConstraints.BOTH;
			gbc_scrollAlumnos.gridx = 0;
			gbc_scrollAlumnos.gridy = 6;
			contentPanel.add(scrollAlumnos, gbc_scrollAlumnos);
			table_alumnos = new JTable();
			modelAlumnos = new javax.swing.table.DefaultTableModel(new Object[][] {},
					new String[] { "DNI", "Nombre", "Apellidos" });
			table_alumnos.setModel(modelAlumnos);
			scrollAlumnos.setViewportView(table_alumnos);
		}
		{
			JButton btnVerReporteAlu = new JButton("Ver informe alumno");
			btnVerReporteAlu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					generarReporteFichaVer(tf_alumno_dni.getText());
				}
			});
			GridBagConstraints gbc_btnVerReporteAlu = new GridBagConstraints();
			gbc_btnVerReporteAlu.insets = new Insets(0, 0, 5, 0);
			gbc_btnVerReporteAlu.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnVerReporteAlu.gridwidth = 2;
			gbc_btnVerReporteAlu.gridx = 0;
			gbc_btnVerReporteAlu.gridy = 7;
			contentPanel.add(btnVerReporteAlu, gbc_btnVerReporteAlu);
		}
		{
			JButton btnDescargarReporteAlu = new JButton("Descargar informe alumno");
			btnDescargarReporteAlu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					generarReporteFichaDescargar(tf_alumno_dni.getText());
				}
			});
			GridBagConstraints gbc_btnDescargarReporteAlu = new GridBagConstraints();
			gbc_btnDescargarReporteAlu.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnDescargarReporteAlu.gridwidth = 2;
			gbc_btnDescargarReporteAlu.gridx = 0;
			gbc_btnDescargarReporteAlu.gridy = 8;
			contentPanel.add(btnDescargarReporteAlu, gbc_btnDescargarReporteAlu);
		}
		actualizarTabla();
	}

	public void actualizarTabla() {

		try (Connection con = parent.getConnection()) {

			modelAlumnos.setRowCount(0);
			ResultSet rsAlumnos = con.createStatement().executeQuery("SELECT * FROM alumnos");
			while (rsAlumnos.next()) {
				modelAlumnos.addRow(new Object[] { rsAlumnos.getString("dni"), rsAlumnos.getString("nombre"),
						rsAlumnos.getString("apellidos") });
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean validar() {
		String msg = "";

		if (!tf_alumno_dni.getText().matches("^[0-9]{8}[A-Z]$") || tf_alumno_dni.getText() == null)
			msg += "\n - DNI inválido (8 dígitos + letra mayúscula)";

		if (!tf_alumno_nombre.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+$") || tf_alumno_nombre.getText() == null)
			msg += "\n - Nombre inválido";

		if (!tf_alumno_apellido.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\s]+$")
				|| tf_alumno_apellido.getText() == null)
			msg += "\n - Apellidos inválidos";
		if (!msg.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Errores:\n" + msg);
			return false;
		}
		return true;
	}

	public void generarReporteFichaVer(String dni) {
		try {
			File jasperFile = new File("src\\examenUD2\\InformeAlumno.jrxml");

			String url = "jdbc:mysql://localhost:3306/examen_final_dint_centro";
			String username = "root";
			String password = "";
			Connection conn = DriverManager.getConnection(url, username, password);

			Map<String, Object> params = new HashMap<>();
			params.put("dni", dni);

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

			JasperViewer view1 = new JasperViewer(jasperPrint, false);
			view1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
			view1.setVisible(true);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generarReporteFichaDescargar(String dni) {

		try {
			File jasperFile = new File("src\\examenUD1CopiaInformes\\FichaUsuario.jrxml");

			String url = "jdbc:mysql://localhost:3306/gym";
			String username = "root";
			String password = "";
			Connection conn = DriverManager.getConnection(url, username, password);

			Map<String, Object> params = new HashMap<>();
			params.put("dniUsuario", dni);

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

			JasperExportManager.exportReportToPdfFile(jasperPrint, "FichaUsuario.pdf");

			System.out.println("Reporte generado correctamente.");

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
