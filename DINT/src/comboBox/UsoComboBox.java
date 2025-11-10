package comboBox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class UsoComboBox extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textModelo;
	private JTextField textCor;
	private JTextField textAncho;
	private JTextField textAlto;
	
	
	private DefaultComboBoxModel<Alfombra> modeloAlfombras;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsoComboBox frame = new UsoComboBox();
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
	public UsoComboBox() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 206);
		modeloAlfombras = new DefaultComboBoxModel<Alfombra>();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Nova alfombra", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{91, 34, 86, 69, 59, 56, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{23, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblModelo = new JLabel("Modelo");
		GridBagConstraints gbc_lblModelo = new GridBagConstraints();
		gbc_lblModelo.anchor = GridBagConstraints.WEST;
		gbc_lblModelo.insets = new Insets(0, 0, 5, 5);
		gbc_lblModelo.gridx = 0;
		gbc_lblModelo.gridy = 0;
		panel.add(lblModelo, gbc_lblModelo);
		
		textModelo = new JTextField();
		GridBagConstraints gbc_textModelo = new GridBagConstraints();
		gbc_textModelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textModelo.gridwidth = 7;
		gbc_textModelo.insets = new Insets(0, 0, 5, 5);
		gbc_textModelo.gridx = 1;
		gbc_textModelo.gridy = 0;
		panel.add(textModelo, gbc_textModelo);
		textModelo.setColumns(10);
		
		JButton btnEngadir = new JButton("Engadir");
		btnEngadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLista();
			}
		});
		GridBagConstraints gbc_btnEngadir = new GridBagConstraints();
		gbc_btnEngadir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEngadir.insets = new Insets(0, 0, 5, 0);
		gbc_btnEngadir.anchor = GridBagConstraints.NORTH;
		gbc_btnEngadir.gridx = 8;
		gbc_btnEngadir.gridy = 0;
		panel.add(btnEngadir, gbc_btnEngadir);
		
		JLabel lblCor = new JLabel("Cor");
		GridBagConstraints gbc_lblCor = new GridBagConstraints();
		gbc_lblCor.anchor = GridBagConstraints.WEST;
		gbc_lblCor.insets = new Insets(0, 0, 0, 5);
		gbc_lblCor.gridx = 0;
		gbc_lblCor.gridy = 1;
		panel.add(lblCor, gbc_lblCor);
		
		textCor = new JTextField();
		GridBagConstraints gbc_textCor = new GridBagConstraints();
		gbc_textCor.insets = new Insets(0, 0, 0, 5);
		gbc_textCor.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCor.gridx = 1;
		gbc_textCor.gridy = 1;
		panel.add(textCor, gbc_textCor);
		textCor.setColumns(10);
		
		JLabel lblAncho = new JLabel("Ancho");
		GridBagConstraints gbc_lblAncho = new GridBagConstraints();
		gbc_lblAncho.anchor = GridBagConstraints.WEST;
		gbc_lblAncho.insets = new Insets(0, 0, 0, 5);
		gbc_lblAncho.gridx = 2;
		gbc_lblAncho.gridy = 1;
		panel.add(lblAncho, gbc_lblAncho);
		
		textAncho = new JTextField();
		GridBagConstraints gbc_textAncho = new GridBagConstraints();
		gbc_textAncho.insets = new Insets(0, 0, 0, 5);
		gbc_textAncho.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAncho.gridx = 3;
		gbc_textAncho.gridy = 1;
		panel.add(textAncho, gbc_textAncho);
		textAncho.setColumns(10);
		
		JLabel lblCm = new JLabel("(cm)");
		GridBagConstraints gbc_lblCm = new GridBagConstraints();
		gbc_lblCm.anchor = GridBagConstraints.WEST;
		gbc_lblCm.insets = new Insets(0, 0, 0, 5);
		gbc_lblCm.gridx = 4;
		gbc_lblCm.gridy = 1;
		panel.add(lblCm, gbc_lblCm);
		
		JLabel lblAlto = new JLabel("Alto");
		GridBagConstraints gbc_lblAlto = new GridBagConstraints();
		gbc_lblAlto.anchor = GridBagConstraints.WEST;
		gbc_lblAlto.insets = new Insets(0, 0, 0, 5);
		gbc_lblAlto.gridx = 5;
		gbc_lblAlto.gridy = 1;
		panel.add(lblAlto, gbc_lblAlto);
		
		textAlto = new JTextField();
		GridBagConstraints gbc_textAlto = new GridBagConstraints();
		gbc_textAlto.insets = new Insets(0, 0, 0, 5);
		gbc_textAlto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAlto.gridx = 6;
		gbc_textAlto.gridy = 1;
		panel.add(textAlto, gbc_textAlto);
		textAlto.setColumns(10);
		
		JLabel lblcm2 = new JLabel("(cm)");
		GridBagConstraints gbc_lblcm2 = new GridBagConstraints();
		gbc_lblcm2.anchor = GridBagConstraints.WEST;
		gbc_lblcm2.insets = new Insets(0, 0, 0, 5);
		gbc_lblcm2.gridx = 7;
		gbc_lblcm2.gridy = 1;
		panel.add(lblcm2, gbc_lblcm2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Alfombras dispo\u00F1ibles", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{237, 170, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnEliminarTodas = new JButton("Eliminar todas");
		btnEliminarTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloAlfombras.removeAllElements();
			}
		});
		

		
		JComboBox<Alfombra> cmbAlfombras = new JComboBox<Alfombra>();
		GridBagConstraints gbc_cmbAlfombras = new GridBagConstraints();
		gbc_cmbAlfombras.gridwidth = 3;
		gbc_cmbAlfombras.insets = new Insets(0, 0, 5, 5);
		gbc_cmbAlfombras.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbAlfombras.gridx = 0;
		gbc_cmbAlfombras.gridy = 0;
		panel_1.add(cmbAlfombras, gbc_cmbAlfombras);

		GridBagConstraints gbc_btnEliminarTodas = new GridBagConstraints();
		gbc_btnEliminarTodas.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminarTodas.insets = new Insets(0, 0, 0, 5);
		gbc_btnEliminarTodas.gridx = 1;
		gbc_btnEliminarTodas.gridy = 1;
		cmbAlfombras.setModel(modeloAlfombras);
		panel_1.add(btnEliminarTodas, gbc_btnEliminarTodas);
		
		JButton btnEliminar = new JButton("Eliminar alfombra");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloAlfombras.removeElementAt(cmbAlfombras.getSelectedIndex());

			}
		});
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 1;
		panel_1.add(btnEliminar, gbc_btnEliminar);
		
		JButton btnInfo = new JButton("Información das alfombras");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modeloAlfombras.getSize()==0)
				 {
				 JOptionPane.showMessageDialog(btnInfo, "Non hai alfombras dispoñibles");
				 return;
				 }

				 if(cmbAlfombras.getSelectedIndex()==-1)
				 {
				 JOptionPane.showMessageDialog(btnInfo, "Debe seleccionar ao menos unha alfombra");
				 return;
				 }


				 Alfombra alfombra=(Alfombra)modeloAlfombras.getElementAt(cmbAlfombras.getSelectedIndex());
				 JOptionPane.showMessageDialog(btnInfo, "MODELO: "+alfombra.getModelo()+"\nCOR: "+
				 alfombra.getCor()+ "\nANCHO: "+alfombra.getAncho()+" cm\nALTO: "+alfombra.getAlto()+"cm"); 
			}
			
		});
		GridBagConstraints gbc_btnInfo = new GridBagConstraints();
		gbc_btnInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInfo.insets = new Insets(0, 0, 0, 5);
		gbc_btnInfo.gridx = 0;
		gbc_btnInfo.gridy = 1;
		panel_1.add(btnInfo, gbc_btnInfo);
	}
	
	

	public void addLista() {
		String modelo, cor;
		int ancho, alto;
		
		modelo = textModelo.getText();
		cor = textCor.getText();
		ancho = Integer.valueOf(textAncho.getText());
		alto = Integer.valueOf(textAlto.getText());
		
		Alfombra alfombra = new Alfombra(modelo, cor, ancho, alto);
		modeloAlfombras.addElement(alfombra);

	}
	
	}

