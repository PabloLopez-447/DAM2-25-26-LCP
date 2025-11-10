package dialogosPredefinidos3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btn1 = new JButton("Boton1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = JOptionPane.showOptionDialog(FrmPrincipal.this, "Selecciona unha opción", "Exemplo 1",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
				String mensaxe = "Xanela pechada polo usuario";
				switch (seleccion) {
				case JOptionPane.YES_OPTION:
					mensaxe = "Pulsado o botón Sí";
					break;
				case JOptionPane.NO_OPTION:
					mensaxe = "Pulsado o botón Non";
					break;
				}

				JOptionPane.showMessageDialog(FrmPrincipal.this, mensaxe, "Resultado", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(btn1);

		JButton btn2 = new JButton("Boton2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = JOptionPane.showOptionDialog(FrmPrincipal.this, "Selecciona unha opción", "Exemplo 2",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				String mensaxe = "Xanela pechada polo usuario";
				switch (seleccion) {
				case JOptionPane.YES_OPTION:
					mensaxe = "Pulsado o botón Sí";
					break;
				case JOptionPane.NO_OPTION:
					mensaxe = "Pulsado o botón Non";
					break;
				case JOptionPane.CANCEL_OPTION:
					mensaxe = "Pulsado o botón Cancelar";
					break;

				}

				JOptionPane.showMessageDialog(FrmPrincipal.this, mensaxe, "Resultado", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(btn2);

		JButton btn3 = new JButton("Boton3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icona = Icons.convertImage("cuadrao.png", 64, 64);
				int seleccion = JOptionPane.showOptionDialog(FrmPrincipal.this, "Selecciona unha opción", "Exemplo 3",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icona, null, null);
				String mensaxe = "Xanela pechada polo usuario";
				switch (seleccion) {
				case JOptionPane.YES_OPTION:
					mensaxe = "Pulsado o botón Sí";
					break;
				case JOptionPane.NO_OPTION:
					mensaxe = "Pulsado o botón Non";
					break;
				case JOptionPane.CANCEL_OPTION:
					mensaxe = "Pulsado o botón Cancelar";
					break;

				}

				JOptionPane.showMessageDialog(FrmPrincipal.this, mensaxe, "Resultado", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(btn3);

		JButton btn4 = new JButton("Boton4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String botons[] = { "Chove", "Non chove", "Poidera ser" };
				int seleccion = JOptionPane.showOptionDialog(FrmPrincipal.this, "¿Choverá pola tarde?", "Exemplo 4",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botons, null);
				String mensaxe = "Xanela pechada polo usuario";
				switch (seleccion) {
				case 0:
					mensaxe = "Vai chover";
					break;
				case 1:
					mensaxe = "Non vai chover";
					break;
				case 2:
					mensaxe = "Poidera ser que chova. Xa veremos";
					break;

				}

				JOptionPane.showMessageDialog(FrmPrincipal.this, mensaxe, "Resultado", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(btn4);

		JButton btn5 = new JButton("Boton5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String botons[] = { "Chove", "Non chove", "Poidera ser" };
				int seleccion = JOptionPane.showOptionDialog(FrmPrincipal.this, "¿Choverá pola tarde?", "Exemplo 4",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, botons, botons[2]);
				String mensaxe = "Xanela pechada polo usuario";
				switch (seleccion) {
				case 0:
					mensaxe = "Vai chover";
					break;
				case 1:
					mensaxe = "Non vai chover";
					break;
				case 2:
					mensaxe = "Poidera ser que chova. Xa veremos";
					break;

				}

				JOptionPane.showMessageDialog(FrmPrincipal.this, mensaxe, "Resultado", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(btn5);

	}

}
