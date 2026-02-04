package examenUD1CopiaInformes;

import java.awt.EventQueue;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Test extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					generarReporte();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);

	}

	public void generarReporte() throws SQLException {
		try {
			// 1. Compilar el archivo JRXML
			// JRXML es la plantilla del reporte diseñada con JasperReports.
			// Debe existir en la ruta especificada.
			File jasperFile = new File(
					"D:\\plopecous\\DAM2-25-26-LCP\\DINT\\src\\examenUD1CopiaInformes\\Tarea1.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());

			// 2. Crear parámetros
			// Los parámetros son valores que se pasan al reporte, como filtros o títulos.
			// Vamos a pasar ID de pedido y un título.
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("id_pedido", "11076");
			parameters.put("p_titulo", "Mi Reporte");

			// OPCION 1.
			// 3. Crear los datos que se vana mostrar, cada una de las filas. Importa el
			// tipo de dato debe coincidir con el del informe.
			Collection<Map<String, ?>> data = new ArrayList<>();
			Map<String, Object> row1 = new HashMap<>();
			row1.put("id_pedido", 11076);
			row1.put("producto", "Producto A");
			data.add(row1);
			Map<String, Object> row2 = new HashMap<>();
			row2.put("id_pedido", 11076);
			row2.put("producto", "Producto B");
			data.add(row2);

			// 4. Llenar reporte con datos
			// JasperFillManager genera el reporte combinando la plantilla, parámetros y
			// datos de entrada.
			JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			// OPCION 2.
			// 3. Crear conexión a la base de datos
			String url = "jdbc:mysql://localhost:3306/dint"; // Cambiar
			// 'mi_base_de_datos' por vuesta base de datos
			String username = "root"; // Cambiar si usan otro usuario
			String password = ""; // Cambiar la contraseña
			Connection conn = DriverManager.getConnection(url, username, password);

			// 4. Llenar reporte con datos
			// JasperFillManager genera el reporte combinando la plantilla, parámetros y
			// conexión a la DB.
			JasperPrint jasperPrint1 = JasperFillManager.fillReport(jasperReport, parameters, conn);

			// Mostrar el reporte en pantalla
			JasperViewer view1 = new JasperViewer(jasperPrint1, false);
			view1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
			view1.setVisible(true);

			// 5️⃣ Exportar a PDF
			// Guarda el reporte generado en un archivo PDF en la carpeta del proyecto
			JasperExportManager.exportReportToPdfFile(jasperPrint1, "ReportePedidos.pdf");
			System.out.println("Reporte generado correctamente.");

		} catch (JRException e) {
			e.printStackTrace();
		}
		// catch (SQLException e) {
		// e.printStackTrace();
		// }
	}

}
