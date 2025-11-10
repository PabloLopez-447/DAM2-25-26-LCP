package gridBag;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class GridBag extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textApellido1;
	private JTextField textApellido2;
	private JTextField textEdad;
	private JTextField textTelF;
	private JTextField textTelM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GridBag frame = new GridBag();
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
	public GridBag() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNombre = new JLabel("Nome");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		contentPane.add(lblNombre, gbc_lblNombre);
		
		textNome = new JTextField();
		GridBagConstraints gbc_textNome = new GridBagConstraints();
		gbc_textNome.insets = new Insets(0, 0, 5, 5);
		gbc_textNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNome.gridx = 1;
		gbc_textNome.gridy = 0;
		contentPane.add(textNome, gbc_textNome);
		textNome.setColumns(10);
		
		JLabel lblApellido1 = new JLabel("Apelido 1");
		GridBagConstraints gbc_lblApellido1 = new GridBagConstraints();
		gbc_lblApellido1.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido1.anchor = GridBagConstraints.WEST;
		gbc_lblApellido1.gridx = 2;
		gbc_lblApellido1.gridy = 0;
		contentPane.add(lblApellido1, gbc_lblApellido1);
		
		textApellido1 = new JTextField();
		GridBagConstraints gbc_textApellido1 = new GridBagConstraints();
		gbc_textApellido1.insets = new Insets(0, 0, 5, 5);
		gbc_textApellido1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellido1.gridx = 3;
		gbc_textApellido1.gridy = 0;
		contentPane.add(textApellido1, gbc_textApellido1);
		textApellido1.setColumns(10);
		
		JLabel lblApellido2 = new JLabel("Apelido 2");
		GridBagConstraints gbc_lblApellido2 = new GridBagConstraints();
		gbc_lblApellido2.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido2.anchor = GridBagConstraints.WEST;
		gbc_lblApellido2.gridx = 4;
		gbc_lblApellido2.gridy = 0;
		contentPane.add(lblApellido2, gbc_lblApellido2);
		
		textApellido2 = new JTextField();
		GridBagConstraints gbc_textApellido2 = new GridBagConstraints();
		gbc_textApellido2.insets = new Insets(0, 0, 5, 5);
		gbc_textApellido2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellido2.gridx = 5;
		gbc_textApellido2.gridy = 0;
		contentPane.add(textApellido2, gbc_textApellido2);
		textApellido2.setColumns(10);
		
		JButton btnGuardar = new JButton("Gardar");
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 5, 0);
		gbc_btnGuardar.gridx = 6;
		gbc_btnGuardar.gridy = 0;
		contentPane.add(btnGuardar, gbc_btnGuardar);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		GridBagConstraints gbc_lblObservaciones = new GridBagConstraints();
		gbc_lblObservaciones.anchor = GridBagConstraints.WEST;
		gbc_lblObservaciones.insets = new Insets(0, 0, 5, 5);
		gbc_lblObservaciones.gridx = 0;
		gbc_lblObservaciones.gridy = 1;
		contentPane.add(lblObservaciones, gbc_lblObservaciones);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 2;
		gbc_textArea.gridwidth = 5;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 1;
		contentPane.add(textArea, gbc_textArea);
		
		JButton btnLimpiar = new JButton("Limpar");
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.anchor = GridBagConstraints.NORTH;
		gbc_btnLimpiar.insets = new Insets(0, 0, 5, 0);
		gbc_btnLimpiar.gridx = 6;
		gbc_btnLimpiar.gridy = 1;
		contentPane.add(btnLimpiar, gbc_btnLimpiar);
		
		JButton btnNewButton = new JButton("Pechar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblIdade = new JLabel("Idade");
		GridBagConstraints gbc_lblIdade = new GridBagConstraints();
		gbc_lblIdade.anchor = GridBagConstraints.WEST;
		gbc_lblIdade.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdade.gridx = 0;
		gbc_lblIdade.gridy = 3;
		contentPane.add(lblIdade, gbc_lblIdade);
		
		textEdad = new JTextField();
		GridBagConstraints gbc_textEdad = new GridBagConstraints();
		gbc_textEdad.insets = new Insets(0, 0, 5, 5);
		gbc_textEdad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEdad.gridx = 1;
		gbc_textEdad.gridy = 3;
		contentPane.add(textEdad, gbc_textEdad);
		textEdad.setColumns(10);
		
		JLabel lblTelF = new JLabel("Telf. fixo");
		GridBagConstraints gbc_lblTelF = new GridBagConstraints();
		gbc_lblTelF.anchor = GridBagConstraints.WEST;
		gbc_lblTelF.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelF.gridx = 0;
		gbc_lblTelF.gridy = 4;
		contentPane.add(lblTelF, gbc_lblTelF);
		
		textTelF = new JTextField();
		GridBagConstraints gbc_textTelF = new GridBagConstraints();
		gbc_textTelF.gridwidth = 5;
		gbc_textTelF.insets = new Insets(0, 0, 5, 5);
		gbc_textTelF.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelF.gridx = 1;
		gbc_textTelF.gridy = 4;
		contentPane.add(textTelF, gbc_textTelF);
		textTelF.setColumns(10);
		
		JLabel lblTelfMobil = new JLabel("Telf. Mobil");
		GridBagConstraints gbc_lblTelfMobil = new GridBagConstraints();
		gbc_lblTelfMobil.anchor = GridBagConstraints.WEST;
		gbc_lblTelfMobil.insets = new Insets(0, 0, 0, 5);
		gbc_lblTelfMobil.gridx = 0;
		gbc_lblTelfMobil.gridy = 5;
		contentPane.add(lblTelfMobil, gbc_lblTelfMobil);
		
		textTelM = new JTextField();
		GridBagConstraints gbc_textTelM = new GridBagConstraints();
		gbc_textTelM.gridwidth = 5;
		gbc_textTelM.insets = new Insets(0, 0, 0, 5);
		gbc_textTelM.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelM.gridx = 1;
		gbc_textTelM.gridy = 5;
		contentPane.add(textTelM, gbc_textTelM);
		textTelM.setColumns(10);

	}

}
