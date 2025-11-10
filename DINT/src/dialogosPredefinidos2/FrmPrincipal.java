package dialogosPredefinidos2;

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

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btn1 = new JButton("Boton1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String domicilio = JOptionPane.showInputDialog("Indique o seu domicilio");
				if (domicilio == null) {
					JOptionPane.showMessageDialog(FrmPrincipal.this, "Acción anulada polo usuario", "Atención",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (domicilio.trim().compareTo("") == 0) {
						JOptionPane.showMessageDialog(FrmPrincipal.this, "A caixa de texto está baleira", "Atención",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(FrmPrincipal.this, "O seu domicilio é " + domicilio, "Atención",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		contentPane.add(btn1);

		JButton btn2 = new JButton("Boton2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String domicilio = JOptionPane.showInputDialog("Indique o seu domicilio",
						"Rúa Descoñecida s/n, 15001, A Coruña");
				if (domicilio == null) {
					JOptionPane.showMessageDialog(FrmPrincipal.this, "Acción anulada polo usuario", "Atención",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (domicilio.trim().compareTo("") == 0) {
						JOptionPane.showMessageDialog(FrmPrincipal.this, "A caixa de texto está baleira", "Atención",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(FrmPrincipal.this, "O seu domicilio é " + domicilio, "Atención",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		contentPane.add(btn2);

		JButton btn3 = new JButton("Boton3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String domicilio = JOptionPane.showInputDialog(FrmPrincipal.this, "Indique o seu domicilio");
				if (domicilio == null) {
					JOptionPane.showMessageDialog(FrmPrincipal.this, "Acción anulada polo usuario", "Atención",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (domicilio.trim().compareTo("") == 0) {
						JOptionPane.showMessageDialog(FrmPrincipal.this, "A caixa de texto está baleira", "Atención",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(FrmPrincipal.this, "O seu domicilio é " + domicilio, "Atención",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		contentPane.add(btn3);

		JButton btn4 = new JButton("Boton4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String domicilio = JOptionPane.showInputDialog(FrmPrincipal.this, "Indique o seu domicilio",
						"Rúa Descoñecidas/n, 15001, A Coruña");
				if (domicilio == null) {
					JOptionPane.showMessageDialog(FrmPrincipal.this, "Acción anulada polo usuario", "Atención",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (domicilio.trim().compareTo("") == 0) {
						JOptionPane.showMessageDialog(FrmPrincipal.this, "A caixa de texto está baleira", "Atención",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(FrmPrincipal.this, "O seu domicilio é " + domicilio, "Atención",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		contentPane.add(btn4);

		JButton btn5 = new JButton("Boton5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String domicilio = JOptionPane.showInputDialog(FrmPrincipal.this, "Indique o seudomicilio", "Domicilio",
						JOptionPane.WARNING_MESSAGE);
				if (domicilio == null) {
					JOptionPane.showMessageDialog(FrmPrincipal.this, "Acción anulada polo usuario", "Atención",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (domicilio.trim().compareTo("") == 0) {
						JOptionPane.showMessageDialog(FrmPrincipal.this, "A caixa de texto está baleira", "Atención",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(FrmPrincipal.this, "O seu domicilio é " + domicilio, "Atención",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		contentPane.add(btn5);

		JButton btn6 = new JButton("Boton6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icona = Icons.convertImage("cuadrao.png", 64, 64);
				String provincias[] = { "A Coruña", "Lugo", "Ourense", "Pontevedra" };
				Object seleccion = JOptionPane.showInputDialog(FrmPrincipal.this,
						"Indique o súa orovincia de nacemento", "Nacemento", JOptionPane.QUESTION_MESSAGE, icona,
						provincias, "Lugo");
				if (seleccion == null) {
					JOptionPane.showMessageDialog(FrmPrincipal.this, "Acción anulada polo usuario", "Atención",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(FrmPrincipal.this, "A provincia seleccionada é " + seleccion,
							"Atención", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		contentPane.add(btn6);

	}

}
