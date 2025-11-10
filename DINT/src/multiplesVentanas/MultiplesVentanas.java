package multiplesVentanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class MultiplesVentanas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultiplesVentanas frame = new MultiplesVentanas();
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
	public MultiplesVentanas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnDatosPersoais = new JButton("Datos Persoais");
		btnDatosPersoais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (XestorDeXanelas.abrirXanelasDatosPersoais()) {
					DatosPersoais dlgDatosPersoais = new DatosPersoais();
					XestorDeXanelas.engadirXanelaDatosPersoais(dlgDatosPersoais);
					dlgDatosPersoais.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(btnDatosPersoais, "Non é posible abrir máis xanelas deste tipo");
					return;
				}
			}
		});
		contentPane.add(btnDatosPersoais);

		JButton btnDatosAcademicos = new JButton("Datos Academicos");
		btnDatosAcademicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (XestorDeXanelas.abrirXanelasDatosAcademicos()) {
					DatosAcademicos dlgDatosAcademicos = new DatosAcademicos();
					dlgDatosAcademicos.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(btnDatosAcademicos, "Non é posible abrir máis xanelas deste tipo");
					return;
				}
			}
		});

		JButton btnCascada = new JButton("Cascada");
		btnCascada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xanelasDatosPersoaisEnCascada();
			}
		});
		contentPane.add(btnCascada);
		contentPane.add(btnDatosAcademicos);

		JButton btnSair = new JButton("Saír");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		contentPane.add(btnSair);

	}

	public void xanelasDatosPersoaisEnCascada() {
		Vector<?> xanelasDatosPersoais = XestorDeXanelas.recuperarXanelasDatosPersoais();
		int posX = 10, posY = 10, incremento = 50;
		for (int i = 0; i < xanelasDatosPersoais.size(); i++) {
			DatosPersoais xanela = (DatosPersoais) xanelasDatosPersoais.elementAt(i);
			xanela.setLocation(posX, posY);
			posX += incremento;
			posY += incremento;
		}
	}

	public void xestionDeMensaxesDeErro(int numErro) {
		switch (numErro) {
		case 1:
			JOptionPane.showMessageDialog(this, "O nome non pode estar baleiro");
			break;
		case 2:
			JOptionPane.showMessageDialog(this, "Os apelidos non poden estar baleiros");
			break;
		}
	}
}
