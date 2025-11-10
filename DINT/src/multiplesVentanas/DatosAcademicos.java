package multiplesVentanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DatosAcademicos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DatosAcademicos dialog = new DatosAcademicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DatosAcademicos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				XestorDeXanelas.cerrarXanelasDatosAcademicos();
			}
		});
		setBounds(100, 100, 360, 235);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Indique o m\u00E1ximo grado acabado", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 109, 0 };
		gbl_contentPanel.rowHeights = new int[] { 23, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JRadioButton rdbtnESO = new JRadioButton("ESO");
			buttonGroup.add(rdbtnESO);
			GridBagConstraints gbc_rdbtnESO = new GridBagConstraints();
			gbc_rdbtnESO.anchor = GridBagConstraints.NORTHWEST;
			gbc_rdbtnESO.insets = new Insets(0, 0, 5, 0);
			gbc_rdbtnESO.gridx = 0;
			gbc_rdbtnESO.gridy = 0;
			contentPanel.add(rdbtnESO, gbc_rdbtnESO);
		}
		{
			JRadioButton rdbtnBach = new JRadioButton("Bacharelato");
			buttonGroup.add(rdbtnBach);
			GridBagConstraints gbc_rdbtnBach = new GridBagConstraints();
			gbc_rdbtnBach.anchor = GridBagConstraints.NORTHWEST;
			gbc_rdbtnBach.insets = new Insets(0, 0, 5, 0);
			gbc_rdbtnBach.gridx = 0;
			gbc_rdbtnBach.gridy = 1;
			contentPanel.add(rdbtnBach, gbc_rdbtnBach);
		}
		{
			JRadioButton rdbtnFP = new JRadioButton("FP");
			buttonGroup.add(rdbtnFP);
			GridBagConstraints gbc_rdbtnFP = new GridBagConstraints();
			gbc_rdbtnFP.anchor = GridBagConstraints.NORTHWEST;
			gbc_rdbtnFP.insets = new Insets(0, 0, 5, 0);
			gbc_rdbtnFP.gridx = 0;
			gbc_rdbtnFP.gridy = 2;
			contentPanel.add(rdbtnFP, gbc_rdbtnFP);
		}
		{
			JRadioButton rdbtnUniversidade = new JRadioButton("Universidade");
			buttonGroup.add(rdbtnUniversidade);
			GridBagConstraints gbc_rdbtnUniversidade = new GridBagConstraints();
			gbc_rdbtnUniversidade.anchor = GridBagConstraints.NORTHWEST;
			gbc_rdbtnUniversidade.gridx = 0;
			gbc_rdbtnUniversidade.gridy = 3;
			contentPanel.add(rdbtnUniversidade, gbc_rdbtnUniversidade);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
