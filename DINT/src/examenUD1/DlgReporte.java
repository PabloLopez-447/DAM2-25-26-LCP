package examenUD1;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DlgReporte extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	public DlgReporte(FrmPrincipal parent) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parent.isReporteAbierto = false;
			}
		});
		setBounds(100, 100, 743, 491);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Tabla de reportes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);

		String[] movsHistory = parent.textHistorial.getText().split("\n");

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { "Nº de usuarios totales", parent.modeloUsuarios.getSize() },
						{ "Nº de usuarios en el gimnaso", parent.usuariosDentro.size() },
						{ "Nº de movimientros en el historial", movsHistory.length - 1 },
						{ "Edad media usuarios registrados", null }, { "Edad media usuarios en el gimnasio", }, },
				new String[] { "Item", "Valor" }) {
			Class[] columnTypes = new Class[] { String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
	}

}
