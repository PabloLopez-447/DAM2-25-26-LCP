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

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class DlgCursos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tf_curso_id;
	private JTextField tf_curso_nombre;
	private JTextField tf_curso_sesiones;

	private javax.swing.table.DefaultTableModel modelCursos;
	private JTable table_cursos;
	GestionCentro parent;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DlgCursos(GestionCentro parent) {
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
			JLabel lblCursos = new JLabel("Registro de cursos");
			GridBagConstraints gbc_lblCursos = new GridBagConstraints();
			gbc_lblCursos.insets = new Insets(0, 0, 5, 5);
			gbc_lblCursos.gridx = 0;
			gbc_lblCursos.gridy = 0;
			contentPanel.add(lblCursos, gbc_lblCursos);
		}
		{
			JLabel lb_curso_id = new JLabel("ID");
			GridBagConstraints gbc_lb_curso_id = new GridBagConstraints();
			gbc_lb_curso_id.insets = new Insets(0, 0, 5, 5);
			gbc_lb_curso_id.anchor = GridBagConstraints.WEST;
			gbc_lb_curso_id.gridx = 0;
			gbc_lb_curso_id.gridy = 1;
			contentPanel.add(lb_curso_id, gbc_lb_curso_id);
		}
		{
			tf_curso_id = new JTextField();
			GridBagConstraints gbc_tf_curso_id = new GridBagConstraints();
			gbc_tf_curso_id.insets = new Insets(0, 0, 5, 0);
			gbc_tf_curso_id.fill = GridBagConstraints.HORIZONTAL;
			gbc_tf_curso_id.gridx = 1;
			gbc_tf_curso_id.gridy = 1;
			contentPanel.add(tf_curso_id, gbc_tf_curso_id);
		}
		{
			JLabel lb_curso_nombre = new JLabel("Nombre");
			GridBagConstraints gbc_lb_curso_nombre = new GridBagConstraints();
			gbc_lb_curso_nombre.anchor = GridBagConstraints.WEST;
			gbc_lb_curso_nombre.insets = new Insets(0, 0, 5, 5);
			gbc_lb_curso_nombre.gridx = 0;
			gbc_lb_curso_nombre.gridy = 2;
			contentPanel.add(lb_curso_nombre, gbc_lb_curso_nombre);
		}
		{
			tf_curso_nombre = new JTextField();
			GridBagConstraints gbc_tf_curso_nombre = new GridBagConstraints();
			gbc_tf_curso_nombre.insets = new Insets(0, 0, 5, 0);
			gbc_tf_curso_nombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_tf_curso_nombre.gridx = 1;
			gbc_tf_curso_nombre.gridy = 2;
			contentPanel.add(tf_curso_nombre, gbc_tf_curso_nombre);
		}
		{
			JLabel lb_curso_sesiones = new JLabel("Nº Sesiones");
			GridBagConstraints gbc_lb_curso_sesiones = new GridBagConstraints();
			gbc_lb_curso_sesiones.anchor = GridBagConstraints.WEST;
			gbc_lb_curso_sesiones.insets = new Insets(0, 0, 5, 5);
			gbc_lb_curso_sesiones.gridx = 0;
			gbc_lb_curso_sesiones.gridy = 3;
			contentPanel.add(lb_curso_sesiones, gbc_lb_curso_sesiones);
		}
		{
			tf_curso_sesiones = new JTextField();
			GridBagConstraints gbc_tf_curso_sesiones = new GridBagConstraints();
			gbc_tf_curso_sesiones.insets = new Insets(0, 0, 5, 0);
			gbc_tf_curso_sesiones.fill = GridBagConstraints.HORIZONTAL;
			gbc_tf_curso_sesiones.gridx = 1;
			gbc_tf_curso_sesiones.gridy = 3;
			contentPanel.add(tf_curso_sesiones, gbc_tf_curso_sesiones);
		}
		{
			JButton btn_registrar_curso = new JButton("Registrar curso");
			btn_registrar_curso.setEnabled(false);
			btn_registrar_curso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = tf_curso_id.getText();
					String nombre = tf_curso_nombre.getText();
					int sesiones;
					try {
						sesiones = Integer.parseInt(tf_curso_sesiones.getText());
					} catch (NumberFormatException ex) {
						System.out.println("Número de sesiones inválido");
						return;
					}
					try (Connection con = parent.getConnection()) {
						String sql = "INSERT INTO cursos (id, nombre, num_total_sesiones) VALUES (?, ?, ?)";
						PreparedStatement pst = con.prepareStatement(sql);
						pst.setString(1, id);
						pst.setString(2, nombre);
						pst.setInt(3, sesiones);
						pst.executeUpdate();
						System.out.println("Curso registrado correctamente");
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
							btn_registrar_curso.setEnabled(true);
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
			GridBagConstraints gbc_btn_registrar_curso = new GridBagConstraints();
			gbc_btn_registrar_curso.fill = GridBagConstraints.HORIZONTAL;
			gbc_btn_registrar_curso.insets = new Insets(0, 0, 5, 0);
			gbc_btn_registrar_curso.gridx = 1;
			gbc_btn_registrar_curso.gridy = 5;
			contentPanel.add(btn_registrar_curso, gbc_btn_registrar_curso);
		}
		{
			JScrollPane scrollCursos = new JScrollPane();
			GridBagConstraints gbc_scrollCursos = new GridBagConstraints();
			gbc_scrollCursos.insets = new Insets(0, 0, 5, 0);
			gbc_scrollCursos.gridwidth = 2;
			gbc_scrollCursos.fill = GridBagConstraints.BOTH;
			gbc_scrollCursos.gridx = 0;
			gbc_scrollCursos.gridy = 6;
			contentPanel.add(scrollCursos, gbc_scrollCursos);
			table_cursos = new JTable();
			modelCursos = new javax.swing.table.DefaultTableModel(new Object[][] {},
					new String[] { "ID", "Nombre", "Nº Sesiones" });
			table_cursos.setModel(modelCursos);
			scrollCursos.setViewportView(table_cursos);
		}
		{
			JButton btnVerRCurso = new JButton("Ver informe de cursos");
			btnVerRCurso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					generarReporteCursoVer();
				}
			});
			GridBagConstraints gbc_btnVerRCurso = new GridBagConstraints();
			gbc_btnVerRCurso.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnVerRCurso.gridwidth = 2;
			gbc_btnVerRCurso.insets = new Insets(0, 0, 5, 0);
			gbc_btnVerRCurso.gridx = 0;
			gbc_btnVerRCurso.gridy = 7;
			contentPanel.add(btnVerRCurso, gbc_btnVerRCurso);
		}
		{
			JButton btnDescargarInformeC = new JButton("Descargar informe de cursos");
			btnDescargarInformeC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					generarReporteCursoDescargar();
				}
			});
			GridBagConstraints gbc_btnDescargarInformeC = new GridBagConstraints();
			gbc_btnDescargarInformeC.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnDescargarInformeC.gridwidth = 2;
			gbc_btnDescargarInformeC.gridx = 0;
			gbc_btnDescargarInformeC.gridy = 8;
			contentPanel.add(btnDescargarInformeC, gbc_btnDescargarInformeC);
		}
		actualizarTabla();
	}

	public void actualizarTabla() {

		try (Connection con = parent.getConnection()) {

			modelCursos.setRowCount(0);
			ResultSet rsCursos = con.createStatement().executeQuery("SELECT * FROM cursos");
			while (rsCursos.next()) {
				modelCursos.addRow(new Object[] { rsCursos.getString("id"), rsCursos.getString("nombre"),
						rsCursos.getInt("num_total_sesiones") });
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean validar() {
		String msg = "";

		if (!tf_curso_nombre.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+$") || tf_curso_nombre.getText() == null)
			msg += "\n - Nombre inválido";

		if (!tf_curso_sesiones.getText().matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\s]+$") || tf_curso_sesiones.getText() == null)
			msg += "\n - Apellidos inválidos";
		if (!msg.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Errores:\n" + msg);
			return false;
		}
		return true;
	}

	public void generarReporteCursoVer() {
		try {
			File jasperFile = new File("src\\examenUD2\\InformeCurso.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());

			String url = "jdbc:mysql://localhost:3306/examen_final_dint_centro";
			String username = "root";
			String password = "";
			Connection conn = DriverManager.getConnection(url, username, password);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

			JasperViewer view1 = new JasperViewer(jasperPrint, false);
			view1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
			view1.setVisible(true);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generarReporteCursoDescargar() {

		try {
			File jasperFile = new File("src\\examenUD2\\InformeCurso.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());

			String url = "jdbc:mysql://localhost:3306/examen_final_dint_centro";
			String username = "root";
			String password = "";
			Connection conn = DriverManager.getConnection(url, username, password);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

			JasperExportManager.exportReportToPdfFile(jasperPrint, "InformeCurso.pdf");

			System.out.println("Reporte generado correctamente.");

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
