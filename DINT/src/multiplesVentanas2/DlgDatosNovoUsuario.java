package multiplesVentanas2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

public class DlgDatosNovoUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textNome;
	private JTextField textApelidos;
	private JTextField textProvincia;
	private int idUsuario;

	/**
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { try { DlgDatosNovoUsuario dialog =
	 * new DlgDatosNovoUsuario();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */
	/**
	 * Create the dialog.
	 */
	public DlgDatosNovoUsuario(Frame parent, boolean modal, int id) {
		super(parent, modal);
		idUsuario = id;
		setTitle("Datos novo usuario - ID " + idUsuario);
		setBounds(100, 100, 449, 173);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNome = new JLabel("Nome");
			GridBagConstraints gbc_lblNome = new GridBagConstraints();
			gbc_lblNome.insets = new Insets(0, 0, 5, 5);
			gbc_lblNome.anchor = GridBagConstraints.WEST;
			gbc_lblNome.gridx = 0;
			gbc_lblNome.gridy = 0;
			contentPanel.add(lblNome, gbc_lblNome);
		}
		{
			textNome = new JTextField();
			GridBagConstraints gbc_textNome = new GridBagConstraints();
			gbc_textNome.gridwidth = 2;
			gbc_textNome.insets = new Insets(0, 0, 5, 5);
			gbc_textNome.fill = GridBagConstraints.HORIZONTAL;
			gbc_textNome.gridx = 1;
			gbc_textNome.gridy = 0;
			contentPanel.add(textNome, gbc_textNome);
			textNome.setColumns(10);
		}
		{
			JLabel lblApelidos = new JLabel("Apelidos");
			GridBagConstraints gbc_lblApelidos = new GridBagConstraints();
			gbc_lblApelidos.anchor = GridBagConstraints.EAST;
			gbc_lblApelidos.insets = new Insets(0, 0, 5, 5);
			gbc_lblApelidos.gridx = 0;
			gbc_lblApelidos.gridy = 1;
			contentPanel.add(lblApelidos, gbc_lblApelidos);
		}
		{
			textApelidos = new JTextField();
			GridBagConstraints gbc_textApelidos = new GridBagConstraints();
			gbc_textApelidos.gridwidth = 2;
			gbc_textApelidos.insets = new Insets(0, 0, 5, 5);
			gbc_textApelidos.fill = GridBagConstraints.HORIZONTAL;
			gbc_textApelidos.gridx = 1;
			gbc_textApelidos.gridy = 1;
			contentPanel.add(textApelidos, gbc_textApelidos);
			textApelidos.setColumns(10);
		}
		{
			JLabel lblProvincia = new JLabel("Provincia");
			GridBagConstraints gbc_lblProvincia = new GridBagConstraints();
			gbc_lblProvincia.anchor = GridBagConstraints.EAST;
			gbc_lblProvincia.insets = new Insets(0, 0, 0, 5);
			gbc_lblProvincia.gridx = 0;
			gbc_lblProvincia.gridy = 2;
			contentPanel.add(lblProvincia, gbc_lblProvincia);
		}
		{
			textProvincia = new JTextField();
			GridBagConstraints gbc_textProvincia = new GridBagConstraints();
			gbc_textProvincia.insets = new Insets(0, 0, 0, 5);
			gbc_textProvincia.fill = GridBagConstraints.HORIZONTAL;
			gbc_textProvincia.gridx = 1;
			gbc_textProvincia.gridy = 2;
			contentPanel.add(textProvincia, gbc_textProvincia);
			textProvincia.setColumns(10);
		}
		{
			JButton btnSeleccionar = new JButton("Seleccionar");
			GridBagConstraints gbc_btnSeleccionar = new GridBagConstraints();
			gbc_btnSeleccionar.gridx = 2;
			gbc_btnSeleccionar.gridy = 2;
			contentPanel.add(btnSeleccionar, gbc_btnSeleccionar);
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

	public void establecerProvincia(String provincia) {
		textProvincia.setText(provincia);
	}
}
