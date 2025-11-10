package menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenu mnOpcion1 = new JMenu("Opcion1");
		mnOpciones.add(mnOpcion1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnOpcion1.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_4 = new JMenu("New menu");
		mnOpcion1.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("New menu item");
		mnNewMenu_4.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("New menu item");
		mnNewMenu_4.add(mntmNewMenuItem_6);
		
		JMenuItem mntmOpcion2 = new JMenuItem("Opcion2");
		mnOpciones.add(mntmOpcion2);
		
		JMenuItem mntmOpcion3 = new JMenuItem("Opcion3");
		mnOpciones.add(mntmOpcion3);
		
		JSeparator separator = new JSeparator();
		mnOpciones.add(separator);
		
		
		JMenu Sexo = new JMenu("Sexo");
		menuBar.add(Sexo);
		
		JRadioButtonMenuItem rdbtnmntmHombre = new JRadioButtonMenuItem("Hombre");
		buttonGroup.add(rdbtnmntmHombre);
		Sexo.add(rdbtnmntmHombre);
		
		JRadioButtonMenuItem rdbtnmntmMujer = new JRadioButtonMenuItem("Mujer");
		buttonGroup.add(rdbtnmntmMujer);
		Sexo.add(rdbtnmntmMujer);
		
		JMenu mnIdioma = new JMenu("Idiomas");
		menuBar.add(mnIdioma);
		
		JCheckBoxMenuItem chckbxmntmAleman = new JCheckBoxMenuItem("Aleman");
		mnIdioma.add(chckbxmntmAleman);
		
		JCheckBoxMenuItem chckbxmntmIngles = new JCheckBoxMenuItem("Ingles");
		mnIdioma.add(chckbxmntmIngles);
		
		JCheckBoxMenuItem chckbxmntmFrances = new JCheckBoxMenuItem("Frances");
		mnIdioma.add(chckbxmntmFrances);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JMenuItem mntmResumen = new JMenuItem("Resumen");
		mntmResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String strSex = "Sexo: ";
				if (rdbtnmntmHombre.isSelected()) {
					strSex += "Hombre\n";
				}else if(rdbtnmntmMujer.isSelected()) {
					strSex += "Mujer\n";
				}
				
				String strIdi = "Idiomas:";
				
				if(chckbxmntmAleman.isSelected()) {
					strIdi+=" Alemán";
				}
				if(chckbxmntmIngles.isSelected()) {
					strIdi+=" Inglés";
				}
				if(chckbxmntmFrances.isSelected()) {
					strIdi+=" Francés";
				}
				
				JOptionPane.showMessageDialog(mntmResumen, strSex + strIdi);
			}
		});
		mnOpciones.add(mntmResumen);

	}

}
