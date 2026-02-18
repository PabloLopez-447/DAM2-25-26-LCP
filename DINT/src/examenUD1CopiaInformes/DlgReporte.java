package examenUD1CopiaInformes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class DlgReporte extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTable table;

	public DlgReporte(FrmPrincipal parent) {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parent.isReporteAbierto = false;
			}
		});

		setBounds(100, 100, 750, 520);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());

		// ===============================
		// TABLA
		// ===============================
		JPanel panelTabla = new JPanel(new BorderLayout());
		panelTabla.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED), "Tabla de reportes",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		getContentPane().add(panelTabla, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);

		String[] movsHistory = parent.textHistorial.getText().split("\n");

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { "Nº de usuarios totales", parent.modeloUsuarios.getSize() },
						{ "Nº de usuarios en el gimnasio", parent.usuariosDentro.size() },
						{ "Nº de movimientos en el historial", movsHistory.length - 1 },
						{ "Edad media usuarios registrados", null }, { "Edad media usuarios en el gimnasio", null } },
				new String[] { "Item", "Valor" }) {

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		scrollPane.setViewportView(table);

		// ===============================
		// BOTONES INFORME
		// ===============================
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(panelBotones, BorderLayout.SOUTH);

		JButton btnVerInforme = new JButton("Ver informe");
		JButton btnDescargarInforme = new JButton("Descargar informe");

		panelBotones.add(btnVerInforme);
		panelBotones.add(btnDescargarInforme);

		// ===============================
		// ACCIONES
		// ===============================
		btnVerInforme.addActionListener(e -> generarInforme(false));
		btnDescargarInforme.addActionListener(e -> generarInforme(true));
	}

	// ======================================
	// GENERACIÓN DEL INFORME
	// ======================================
	private void generarInforme(boolean exportarPDF) {

		try {
			// -------- DATOS DESDE LA TABLA --------
			Collection<Map<String, ?>> data = new ArrayList<>();

			for (int i = 0; i < table.getRowCount(); i++) {
				Map<String, Object> fila = new HashMap<>();
				fila.put("item", table.getValueAt(i, 0));
				fila.put("valor", table.getValueAt(i, 1));
				data.add(fila);
			}

			JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);

			// -------- PARÁMETROS --------
			Map<String, Object> params = new HashMap<>();
			params.put("NOMBRE_GIMNASIO", "Gimnasio Hércules");
			params.put("TITULO_INFORME", "Informe resumen de actividad");

			// -------- CARGA INFORME --------
			JasperReport report = JasperCompileManager
					.compileReport("src\\examenUD1CopiaInformes\\Gym.jrxml");

			JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);

			// -------- MOSTRAR / EXPORTAR --------
			if (exportarPDF) {
				JasperExportManager.exportReportToPdfFile(print, "Informe_Gimnasio.pdf");
				JOptionPane.showMessageDialog(this, "Informe exportado correctamente.");
			} else {
				JasperViewer.viewReport(print, false);
			}

		} catch (JRException ex) {
			JOptionPane.showMessageDialog(this, "Error generando el informe:\n" + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
