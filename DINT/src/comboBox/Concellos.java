package comboBox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Concellos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultComboBoxModel<String> modeloConcellos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Concellos frame = new Concellos();
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
	public Concellos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		modeloConcellos = new DefaultComboBoxModel<String>();
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{78, 566, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblProvincia = new JLabel("Provincia");
		GridBagConstraints gbc_lblProvincia = new GridBagConstraints();
		gbc_lblProvincia.insets = new Insets(0, 0, 5, 5);
		gbc_lblProvincia.anchor = GridBagConstraints.WEST;
		gbc_lblProvincia.gridx = 0;
		gbc_lblProvincia.gridy = 0;
		contentPane.add(lblProvincia, gbc_lblProvincia);
		
		JComboBox<String> cmbProvincias = new JComboBox<String>();
		cmbProvincias.setModel(new DefaultComboBoxModel<String>(new String[] {"A Coruña", "Lugo ", "Ourense", "Pontevedra"}));
		cmbProvincias.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				 {
				 int posicionProvincia=cmbProvincias.getSelectedIndex();
				 cargarConcellos(posicionProvincia);}
			}
		});
		GridBagConstraints gbc_cmbProvincias = new GridBagConstraints();
		gbc_cmbProvincias.insets = new Insets(0, 0, 5, 0);
		gbc_cmbProvincias.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbProvincias.gridx = 1;
		gbc_cmbProvincias.gridy = 0;
		contentPane.add(cmbProvincias, gbc_cmbProvincias);
		
		JLabel lblConcello = new JLabel("Concello");
		GridBagConstraints gbc_lblConcello = new GridBagConstraints();
		gbc_lblConcello.anchor = GridBagConstraints.WEST;
		gbc_lblConcello.insets = new Insets(0, 0, 5, 5);
		gbc_lblConcello.gridx = 0;
		gbc_lblConcello.gridy = 1;
		contentPane.add(lblConcello, gbc_lblConcello);
		
		
		JLabel lblSelect = new JLabel("");
		GridBagConstraints gbc_lblSelect = new GridBagConstraints();
		gbc_lblSelect.gridwidth = 2;
		gbc_lblSelect.insets = new Insets(0, 0, 0, 5);
		gbc_lblSelect.gridx = 0;
		gbc_lblSelect.gridy = 2;
		contentPane.add(lblSelect, gbc_lblSelect);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(modeloConcellos);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange()==ItemEvent.SELECTED)
				 {
					lblSelect.setText("Concello seleccionado: "
				 +modeloConcellos.getElementAt(comboBox.getSelectedIndex()).toUpperCase());
				 }
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);

	}
	
	private void cargarConcellos(int codProvincia)
    {
        modeloConcellos.removeAllElements();
        switch(codProvincia)
        {
            case 0: for(int i=0;i<concellosCorunha.length;i++)
                    {
                        modeloConcellos.addElement(concellosCorunha[i]);
                    }
                    break;
            case 1: for(int i=0;i<concellosLugo.length;i++)
                    {
                        modeloConcellos.addElement(concellosLugo[i]);
                    }
                    break;
            case 2: for(int i=0;i<concellosOurense.length;i++)
                    {
                        modeloConcellos.addElement(concellosOurense[i]);
                    }
                    break;
            case 3: for(int i=0;i<concellosPontevedra.length;i++)
                    {
                        modeloConcellos.addElement(concellosPontevedra[i]);
                    }
                    break;                
        }
    }

    private String[] concellosCorunha={"Betanzos","Ferrol","Pontedeume"};
    private String[] concellosLugo={"Foz","Quiroga","Triacastela"};
    private String[] concellosOurense={"Bande","Castro Caldelas","Maside"};
    private String[] concellosPontevedra={"Cangas","Bueu","Mar n","Pontevedra","Tomi o"};    


}
