package jTable;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UsoJTable extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCor;
	private JTextField textAncho;
	private JTextField textAlto;
	private JTextField textFondo;
	private JTable table;
	private DefaultTableModel modeloMobles;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsoJTable frame = new UsoJTable();
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
	public UsoJTable() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Novo moble", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 63, 0, 33, 60, 27, 0, 90, -75, 154, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.WEST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);
		
		textNome = new JTextField();
		GridBagConstraints gbc_textNome = new GridBagConstraints();
		gbc_textNome.gridwidth = 8;
		gbc_textNome.insets = new Insets(0, 0, 5, 5);
		gbc_textNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNome.gridx = 1;
		gbc_textNome.gridy = 0;
		panel.add(textNome, gbc_textNome);
		textNome.setColumns(10);
		
		
		
		JLabel lblCor = new JLabel("Cor");
		GridBagConstraints gbc_lblCor = new GridBagConstraints();
		gbc_lblCor.anchor = GridBagConstraints.WEST;
		gbc_lblCor.insets = new Insets(0, 0, 5, 5);
		gbc_lblCor.gridx = 0;
		gbc_lblCor.gridy = 1;
		panel.add(lblCor, gbc_lblCor);
		
		textCor = new JTextField();
		GridBagConstraints gbc_textCor = new GridBagConstraints();
		gbc_textCor.gridwidth = 2;
		gbc_textCor.insets = new Insets(0, 0, 5, 5);
		gbc_textCor.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCor.gridx = 1;
		gbc_textCor.gridy = 1;
		panel.add(textCor, gbc_textCor);
		textCor.setColumns(10);
		
		JLabel lblMaterial = new JLabel("Material");
		GridBagConstraints gbc_lblMaterial = new GridBagConstraints();
		gbc_lblMaterial.anchor = GridBagConstraints.WEST;
		gbc_lblMaterial.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaterial.gridx = 3;
		gbc_lblMaterial.gridy = 1;
		panel.add(lblMaterial, gbc_lblMaterial);
		
		JComboBox<Object> cmbMobles = new JComboBox<Object>();
		cmbMobles.setModel(new DefaultComboBoxModel<Object>(new String[] {"Madera", "Piedra", "Cemento", "Plastico"}));
		GridBagConstraints gbc_cmbMobles = new GridBagConstraints();
		gbc_cmbMobles.gridwidth = 5;
		gbc_cmbMobles.insets = new Insets(0, 0, 5, 5);
		gbc_cmbMobles.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbMobles.gridx = 4;
		gbc_cmbMobles.gridy = 1;
		panel.add(cmbMobles, gbc_cmbMobles);
		
		JLabel lblAncho = new JLabel("Ancho");
		GridBagConstraints gbc_lblAncho = new GridBagConstraints();
		gbc_lblAncho.anchor = GridBagConstraints.WEST;
		gbc_lblAncho.insets = new Insets(0, 0, 5, 5);
		gbc_lblAncho.gridx = 0;
		gbc_lblAncho.gridy = 2;
		panel.add(lblAncho, gbc_lblAncho);
		
		textAncho = new JTextField();
		GridBagConstraints gbc_textAncho = new GridBagConstraints();
		gbc_textAncho.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAncho.insets = new Insets(0, 0, 5, 5);
		gbc_textAncho.gridx = 1;
		gbc_textAncho.gridy = 2;
		panel.add(textAncho, gbc_textAncho);
		textAncho.setColumns(10);
		
		JLabel lblcm = new JLabel("(cm)");
		GridBagConstraints gbc_lblcm = new GridBagConstraints();
		gbc_lblcm.anchor = GridBagConstraints.WEST;
		gbc_lblcm.insets = new Insets(0, 0, 5, 5);
		gbc_lblcm.gridx = 2;
		gbc_lblcm.gridy = 2;
		panel.add(lblcm, gbc_lblcm);
		
		JLabel lblAlto = new JLabel("Alto");
		GridBagConstraints gbc_lblAlto = new GridBagConstraints();
		gbc_lblAlto.anchor = GridBagConstraints.WEST;
		gbc_lblAlto.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlto.gridx = 3;
		gbc_lblAlto.gridy = 2;
		panel.add(lblAlto, gbc_lblAlto);
		
		textAlto = new JTextField();
		GridBagConstraints gbc_textAlto = new GridBagConstraints();
		gbc_textAlto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAlto.insets = new Insets(0, 0, 5, 5);
		gbc_textAlto.gridx = 4;
		gbc_textAlto.gridy = 2;
		panel.add(textAlto, gbc_textAlto);
		textAlto.setColumns(10);
		
		JLabel lblcm_1 = new JLabel("(cm)");
		GridBagConstraints gbc_lblcm_1 = new GridBagConstraints();
		gbc_lblcm_1.anchor = GridBagConstraints.WEST;
		gbc_lblcm_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblcm_1.gridx = 5;
		gbc_lblcm_1.gridy = 2;
		panel.add(lblcm_1, gbc_lblcm_1);
		
		JLabel lblFondo = new JLabel("Fondo");
		GridBagConstraints gbc_lblFondo = new GridBagConstraints();
		gbc_lblFondo.anchor = GridBagConstraints.WEST;
		gbc_lblFondo.insets = new Insets(0, 0, 5, 5);
		gbc_lblFondo.gridx = 6;
		gbc_lblFondo.gridy = 2;
		panel.add(lblFondo, gbc_lblFondo);
		
		textFondo = new JTextField();
		textFondo.setColumns(10);
		GridBagConstraints gbc_textFondo = new GridBagConstraints();
		gbc_textFondo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFondo.insets = new Insets(0, 0, 5, 5);
		gbc_textFondo.gridx = 7;
		gbc_textFondo.gridy = 2;
		panel.add(textFondo, gbc_textFondo);
		
		JLabel lblcm_1_1 = new JLabel("(cm)");
		GridBagConstraints gbc_lblcm_1_1 = new GridBagConstraints();
		gbc_lblcm_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblcm_1_1.gridx = 8;
		gbc_lblcm_1_1.gridy = 2;
		panel.add(lblcm_1_1, gbc_lblcm_1_1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 3;
		panel.add(verticalStrut, gbc_verticalStrut);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Mobles dispo\u00F1ibles", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 23, 610, 148);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2)
					{
					Moble moble=(Moble)modeloMobles.getValueAt(table.getSelectedRow(), 0);
					String resultado="Nome: "+moble.getNome()+" Cor: "+moble.getCor()+
					" Material: "+moble.getMaterial()+" Ancho: "+moble.getAncho()+
					" Alto: "+moble.getAlto()+" Fondo: "+moble.getFondo();
					JOptionPane.showMessageDialog(table, resultado);
					}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NOME", "COR", "MATERIAL", "MEDIDAS(ANxAxF)"
			}
		));
		scrollPane.setViewportView(table);
		modeloMobles=(DefaultTableModel)table.getModel();
		
		
		JButton btnInforma = new JButton("Informe dos mobles");
		btnInforma.setBounds(25, 225, 176, 23);
		btnInforma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modeloMobles.getRowCount()==0)
				 {
				 JOptionPane.showMessageDialog(btnInforma, "Non hai mobles dispoñibles");
				 return;
				 }

				 if(table.getSelectedRowCount()==0)
				 {
				 JOptionPane.showMessageDialog(btnInforma, "Debe seleccionar ao menos un moble");
				 return;
				 }


				 int posicionesMobles[]=table.getSelectedRows();

				 String resultado="";
				 for(int i=0;i<posicionesMobles.length;i++)
				 {
				 Moble moble=(Moble)modeloMobles.getValueAt(posicionesMobles[i], 0);
				 resultado+="Nome: "+moble.getNome()+" Cor: "+moble.getCor()+
				 " Material: "+moble.getMaterial()+" Ancho: "+moble.getAncho()+
				" Alto: "+moble.getAlto()+" Fondo: "+moble.getFondo()+"\n";
				 }

				 JOptionPane.showMessageDialog(btnInforma, resultado);
			}
		});
		panel_1.add(btnInforma);
		
		JButton btnEliminarMoble = new JButton("Eliminar moble");
		btnEliminarMoble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < modeloMobles.getRowCount(); i++) {
					if (table.getSelectedRow() == i) {
						modeloMobles.removeRow(i);
					}
				}

			}
		});
		btnEliminarMoble.setBounds(226, 225, 176, 23);
		panel_1.add(btnEliminarMoble);
		
		JButton btnNewEliminarAll = new JButton("Eliminar todos");
		btnNewEliminarAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloMobles.setRowCount(0);
			}
		});
		btnNewEliminarAll.setBounds(427, 225, 176, 23);
		panel_1.add(btnNewEliminarAll);
		
		JButton btnEngadir = new JButton("Engadir");
		btnEngadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Moble moble = new Moble(textNome.getText(),textCor.getText(),cmbMobles.getSelectedItem().toString(),Integer.parseInt(textAncho.getText()),Integer.parseInt(textAlto.getText()),Integer.parseInt(textFondo.getText()));
				modeloMobles.setRowCount(modeloMobles.getRowCount()+1);
				 modeloMobles.setValueAt(moble.toString(), modeloMobles.getRowCount()-1 , 0);
				 modeloMobles.setValueAt(moble.getCor(), modeloMobles.getRowCount()-1 , 1);
				 modeloMobles.setValueAt(moble.getMaterial(), modeloMobles.getRowCount()-1 , 2);
				 String medidas=moble.getAncho()+"x"+moble.getAlto()+"x"+moble.getFondo();
				 modeloMobles.setValueAt(medidas, modeloMobles.getRowCount()-1 , 3);
			}
		});
		GridBagConstraints gbc_btnEngadir = new GridBagConstraints();
		gbc_btnEngadir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEngadir.insets = new Insets(0, 0, 5, 0);
		gbc_btnEngadir.gridx = 9;
		gbc_btnEngadir.gridy = 0;
		panel.add(btnEngadir, gbc_btnEngadir);

	}
	

}
