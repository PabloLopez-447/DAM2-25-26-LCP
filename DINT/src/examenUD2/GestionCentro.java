package examenUD2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GestionCentro {

	private JFrame frame;
	private JTextField tf_nota_ev1;
	private JTextField tf_nota_ev2;
	private JTextField tf_nota_ev3;
	private JTextField tf_faltas;

	private JComboBox<String> cb_curso_id_asignar;
	private JComboBox<String> cb_dni_asignar;
	private JComboBox<String> cb_curso_id_evaluar;
	private JComboBox<String> cb_dni_evaluar;
	private JTable table_curso_alumno;

	private javax.swing.table.DefaultTableModel modelCursos;
	private javax.swing.table.DefaultTableModel modelAlumnos;
	private javax.swing.table.DefaultTableModel modelCursoAlumno;

	// --- Conexión a la base de datos ---
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/examen_final_dint_centro", "root", // usuario
					"" // contraseña
			);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GestionCentro window = new GestionCentro();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public GestionCentro() {
		initialize();
		cargarCombos(); // Cargar combos al iniciar
		actualizarTablas(); // Actualizar tablas al iniciar
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 715);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblAsignar = new JLabel("Asignar curso / alumno");
		lblAsignar.setBounds(176, 86, 149, 17);
		frame.getContentPane().add(lblAsignar);

		JLabel lb_curso_asignar = new JLabel("Curso ID");
		lb_curso_asignar.setBounds(176, 110, 88, 17);
		frame.getContentPane().add(lb_curso_asignar);

		cb_curso_id_asignar = new JComboBox<>();
		cb_curso_id_asignar.setBounds(270, 105, 191, 26);
		frame.getContentPane().add(cb_curso_id_asignar);

		JLabel lb_dni_asignar = new JLabel("DNI");
		lb_dni_asignar.setBounds(176, 139, 88, 17);
		frame.getContentPane().add(lb_dni_asignar);

		cb_dni_asignar = new JComboBox<>();
		cb_dni_asignar.setBounds(270, 132, 191, 26);
		frame.getContentPane().add(cb_dni_asignar);

		JLabel lblEvaluar = new JLabel("Evaluar alumno");
		lblEvaluar.setBounds(176, 215, 149, 17);
		frame.getContentPane().add(lblEvaluar);

		JLabel lb_curso_evaluar = new JLabel("Curso ID");
		lb_curso_evaluar.setBounds(176, 241, 88, 17);
		frame.getContentPane().add(lb_curso_evaluar);

		cb_curso_id_evaluar = new JComboBox<>();
		cb_curso_id_evaluar.setBounds(270, 236, 115, 26);
		frame.getContentPane().add(cb_curso_id_evaluar);

		JLabel lb_dni_evaluar = new JLabel("DNI");
		lb_dni_evaluar.setBounds(176, 270, 88, 17);
		frame.getContentPane().add(lb_dni_evaluar);

		cb_dni_evaluar = new JComboBox<>();
		cb_dni_evaluar.setBounds(270, 263, 114, 26);
		frame.getContentPane().add(cb_dni_evaluar);

		JLabel lb_nota_ev1 = new JLabel("Nota EV1");
		lb_nota_ev1.setBounds(176, 301, 88, 17);
		frame.getContentPane().add(lb_nota_ev1);

		tf_nota_ev1 = new JTextField();
		tf_nota_ev1.setBounds(270, 299, 114, 21);
		frame.getContentPane().add(tf_nota_ev1);

		JLabel lb_nota_ev2 = new JLabel("Nota EV2");
		lb_nota_ev2.setBounds(176, 321, 88, 17);
		frame.getContentPane().add(lb_nota_ev2);

		tf_nota_ev2 = new JTextField();
		tf_nota_ev2.setBounds(270, 319, 114, 21);
		frame.getContentPane().add(tf_nota_ev2);

		JLabel lb_nota_ev3 = new JLabel("Nota EV3");
		lb_nota_ev3.setBounds(176, 341, 88, 17);
		frame.getContentPane().add(lb_nota_ev3);

		tf_nota_ev3 = new JTextField();
		tf_nota_ev3.setBounds(270, 339, 114, 21);
		frame.getContentPane().add(tf_nota_ev3);

		JLabel lb_faltas = new JLabel("Num. faltas");
		lb_faltas.setBounds(176, 361, 88, 17);
		frame.getContentPane().add(lb_faltas);

		tf_faltas = new JTextField();
		tf_faltas.setBounds(270, 359, 114, 21);
		frame.getContentPane().add(tf_faltas);

		// --- Botones ---
		JButton btn_registrar_curso = new JButton("Registrar Curso");
		btn_registrar_curso.setBounds(166, 11, 295, 26);
		frame.getContentPane().add(btn_registrar_curso);

		JButton btn_registrar_alumno = new JButton("Registrar alumno");
		btn_registrar_alumno.setBounds(166, 49, 295, 26);
		frame.getContentPane().add(btn_registrar_alumno);

		JButton btn_asignar_curso_alumno = new JButton("Asignar curso/alumno");
		btn_asignar_curso_alumno.setBounds(176, 169, 285, 26);
		frame.getContentPane().add(btn_asignar_curso_alumno);

		JButton btn_evaluar_alumno = new JButton("Evaluar alumno");
		btn_evaluar_alumno.setBounds(176, 389, 285, 26);
		frame.getContentPane().add(btn_evaluar_alumno);
		modelCursos = new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Nº Sesiones" });
		modelAlumnos = new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "DNI", "Nombre", "Apellidos" });

		JScrollPane scrollCursoAlumno = new JScrollPane();
		scrollCursoAlumno.setBounds(26, 426, 584, 224);
		frame.getContentPane().add(scrollCursoAlumno);

		table_curso_alumno = new JTable();
		modelCursoAlumno = new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "Curso ID", "DNI Alumno", "Nota EV1", "Nota EV2", "Nota EV3", "Faltas" });
		table_curso_alumno.setModel(modelCursoAlumno);
		scrollCursoAlumno.setViewportView(table_curso_alumno);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menú");
		menuBar.add(mnNewMenu);

		JMenuItem mntmRegistrarCurso = new JMenuItem("RegistrarCurso");
		mntmRegistrarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgCursos dlg = new DlgCursos(GestionCentro.this);
				dlg.setVisible(true);
			}
		});
		mnNewMenu.add(mntmRegistrarCurso);

		JMenuItem mntmRegistroAlumno = new JMenuItem("Registrar Alumno");
		mntmRegistroAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgAlumnos dlg = new DlgAlumnos(GestionCentro.this);
				dlg.setVisible(true);
			}
		});
		mnNewMenu.add(mntmRegistroAlumno);

		JMenu mnRegistro = new JMenu("Registro general");
		menuBar.add(mnRegistro);

		JMenuItem mntmVerregistro = new JMenuItem("Ver registro general");
		mntmVerregistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarReporteGlobalVer();
			}
		});
		mnRegistro.add(mntmVerregistro);

		JMenuItem mntmDescargarRegistroG = new JMenuItem("Descargar registro general");
		mntmDescargarRegistroG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarReporteGlobalDescargar();
			}
		});
		mnRegistro.add(mntmDescargarRegistroG);

		// --- Acciones botones ---

		// Registrar Curso
		btn_registrar_curso.addActionListener(e -> {
			DlgCursos dlg = new DlgCursos(GestionCentro.this);
			dlg.setVisible(true);
		});

		// Registrar Alumno
		btn_registrar_alumno.addActionListener(e -> {
			DlgAlumnos dlg = new DlgAlumnos(GestionCentro.this);
			dlg.setVisible(true);
		});

		// Asignar Curso/Alumno
		btn_asignar_curso_alumno.addActionListener(e -> {
			String cursoId = (String) cb_curso_id_asignar.getSelectedItem();
			String dniAlumno = (String) cb_dni_asignar.getSelectedItem();
			if (cursoId == null || dniAlumno == null)
				return;
			try (Connection con = getConnection()) {
				String sql = "INSERT INTO curso_alumno (id_curso, dni_alumno) VALUES (?, ?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, cursoId);
				pst.setString(2, dniAlumno);
				pst.executeUpdate();
				System.out.println("Curso asignado correctamente");
				actualizarTablas();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		// Evaluar Alumno
		btn_evaluar_alumno.addActionListener(e -> {
			String cursoId = (String) cb_curso_id_evaluar.getSelectedItem();
			String dniAlumno = (String) cb_dni_evaluar.getSelectedItem();
			if (cursoId == null || dniAlumno == null)
				return;
			int nota1, nota2, nota3, faltas;
			try {
				nota1 = Integer.parseInt(tf_nota_ev1.getText());
				nota2 = Integer.parseInt(tf_nota_ev2.getText());
				nota3 = Integer.parseInt(tf_nota_ev3.getText());
				faltas = Integer.parseInt(tf_faltas.getText());
			} catch (NumberFormatException ex) {
				System.out.println("Datos de evaluación inválidos");
				return;
			}
			try (Connection con = getConnection()) {
				String sql = "UPDATE curso_alumno SET nota_ev1=?, nota_ev2=?, nota_ev3=?, faltas=? WHERE id_curso=? AND dni_alumno=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, nota1);
				pst.setInt(2, nota2);
				pst.setInt(3, nota3);
				pst.setInt(4, faltas);
				pst.setString(5, cursoId);
				pst.setString(6, dniAlumno);
				pst.executeUpdate();
				System.out.println("Alumno evaluado correctamente");
				actualizarTablas();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		// --- Combo curso_id_asignar para filtrar alumnos no asignados ---
		cb_curso_id_asignar.addActionListener(e -> {
			String curso = (String) cb_curso_id_asignar.getSelectedItem();
			if (curso != null) {
				cargarAlumnosNoAsignados(curso);
			}
		});
	}

	private void actualizarTablas() {
		try (Connection con = getConnection()) {

			// --- Cursos ---
			modelCursos.setRowCount(0);
			ResultSet rsCursos = con.createStatement().executeQuery("SELECT * FROM cursos");
			while (rsCursos.next()) {
				modelCursos.addRow(new Object[] { rsCursos.getString("id"), rsCursos.getString("nombre"),
						rsCursos.getInt("num_total_sesiones") });
			}

			// --- Alumnos ---
			modelAlumnos.setRowCount(0);
			ResultSet rsAlumnos = con.createStatement().executeQuery("SELECT * FROM alumnos");
			while (rsAlumnos.next()) {
				modelAlumnos.addRow(new Object[] { rsAlumnos.getString("dni"), rsAlumnos.getString("nombre"),
						rsAlumnos.getString("apellidos") });
			}

			// --- Curso_Alumno ---
			modelCursoAlumno.setRowCount(0);
			ResultSet rsCA = con.createStatement().executeQuery("SELECT * FROM curso_alumno");
			while (rsCA.next()) {
				modelCursoAlumno.addRow(new Object[] { rsCA.getString("id_curso"), rsCA.getString("dni_alumno"),
						rsCA.getInt("nota_ev1"), rsCA.getInt("nota_ev2"), rsCA.getInt("nota_ev3"),
						rsCA.getInt("faltas") });
			}

			// Actualizar combos
			cargarCombos();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void cargarCombos() {
		try (Connection con = getConnection()) {
			// Cursos
			ResultSet rsCursos = con.createStatement().executeQuery("SELECT id FROM cursos");
			cb_curso_id_asignar.removeAllItems();
			cb_curso_id_evaluar.removeAllItems();
			while (rsCursos.next()) {
				String id = rsCursos.getString("id");
				cb_curso_id_asignar.addItem(id);
				cb_curso_id_evaluar.addItem(id);
			}

			// Alumnos (evaluar)
			ResultSet rsAlumnos = con.createStatement().executeQuery("SELECT dni FROM alumnos");
			cb_dni_evaluar.removeAllItems();
			while (rsAlumnos.next()) {
				cb_dni_evaluar.addItem(rsAlumnos.getString("dni"));
			}

			// cb_dni_asignar se llena según curso seleccionado
			String curso = (String) cb_curso_id_asignar.getSelectedItem();
			if (curso != null) {
				cargarAlumnosNoAsignados(curso);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void cargarAlumnosNoAsignados(String cursoId) {
		try (Connection con = getConnection()) {
			cb_dni_asignar.removeAllItems();
			String sql = "SELECT dni FROM alumnos WHERE dni NOT IN "
					+ "(SELECT dni_alumno FROM curso_alumno WHERE id_curso = ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cursoId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cb_dni_asignar.addItem(rs.getString("dni"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void generarReporteGlobalVer() {
		try {
			File jasperFile = new File("src\\examenUD2\\InformeGlobal.jrxml");
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

	public void generarReporteGlobalDescargar() {

		try {
			File jasperFile = new File("src\\examenUD2\\InformeGlobal.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());

			String url = "jdbc:mysql://localhost:3306/examen_final_dint_centro";
			String username = "root";
			String password = "";
			Connection conn = DriverManager.getConnection(url, username, password);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

			JasperExportManager.exportReportToPdfFile(jasperPrint, "InformeGlobal.pdf");

			System.out.println("Reporte generado correctamente.");

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
