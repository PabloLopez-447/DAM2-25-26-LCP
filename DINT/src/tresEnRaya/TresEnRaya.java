package tresEnRaya;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TresEnRaya extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static int turno = 1;
	static JButton[][] botones = new JButton[3][3];


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TresEnRaya frame = new TresEnRaya();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TresEnRaya() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 506);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmReset = new JMenuItem("Reiniciar partida");
		mntmReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(botones);
			}
		});
		mntmReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		mnMenu.add(mntmReset);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		mnMenu.add(mntmSalir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblTurno = new JLabel("Turno del jugador");
		lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTurno, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 3, 0, 0));

		JButton btn1 = new JButton("-");
		btn1.setOpaque(true);
		btn1.setBackground(new Color(240,240,240));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turno(btn1);
				lblTurno.setText("Turno del jugador " + turno);
			}
		});
		panel.add(btn1);
		botones[0][0] = btn1;

		JButton btn2 = new JButton("-");
		btn2.setOpaque(true);
		btn2.setBackground(new Color(240,240,240));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turno(btn2);
				lblTurno.setText("Turno del jugador " + turno);
			}
		});
		panel.add(btn2);
		botones[0][1] = btn2;

		JButton btn3 = new JButton("-");
		btn3.setOpaque(true);
		btn3.setBackground(new Color(240,240,240));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turno(btn3);
				lblTurno.setText("Turno del jugador " + turno);
			}
		});
		panel.add(btn3);
		botones[0][2] = btn3;

		JButton btn4 = new JButton("-");
		btn4.setOpaque(true);
		btn4.setBackground(new Color(240,240,240));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turno(btn4);
				lblTurno.setText("Turno del jugador " + turno);
			}
		});
		panel.add(btn4);
		botones[1][0] = btn4;

		JButton btn5 = new JButton("-");
		btn5.setOpaque(true);
		btn5.setBackground(new Color(240,240,240));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turno(btn5);
				lblTurno.setText("Turno del jugador " + turno);
			}
		});
		panel.add(btn5);
		botones[1][1] = btn5;;

		JButton btn6 = new JButton("-");
		btn6.setOpaque(true);
		btn6.setBackground(new Color(240,240,240));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turno(btn6);
				lblTurno.setText("Turno del jugador " + turno);
			}
		});
		panel.add(btn6);
		botones[1][2] = btn6;

		JButton btn7 = new JButton("-");
		btn7.setOpaque(true);
		btn7.setBackground(new Color(240,240,240));
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turno(btn7);
				lblTurno.setText("Turno del jugador " + turno);
			}
		});
		panel.add(btn7);
		botones[2][0] = btn7;

		JButton btn8 = new JButton("-");
		btn8.setOpaque(true);
		btn8.setBackground(new Color(240,240,240));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turno(btn8);
				lblTurno.setText("Turno del jugador " + turno);
			}
		});
		panel.add(btn8);
		botones[2][1] = btn8;

		JButton btn9 = new JButton("-");
		btn9.setOpaque(true);
		btn9.setBackground(new Color(240,240,240));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turno(btn9);
				lblTurno.setText("Turno del jugador " + turno);
			}
		});
		panel.add(btn9);
		botones[2][2] = btn9;

	}
	
	public void reset(JButton[][] botones) {
		for (int i = 0; i< botones.length;i++) {
			for (int j = 0; j< botones.length;j++) {
				botones[i][j].setText("-");
				botones[i][j].setBackground(new Color(240,240,240));
				turno = 1;
			}
			
		}
	}

	public void turno(JButton btn) {
        if (btn.getText().equals("-")) {
            btn.setText(String.valueOf(turno));
            if (turno == 1) {
                btn.setBackground(new Color(0,255,0));
            } else {
                btn.setBackground(new Color(255,0,0));
            }

            if (verificarGanador()) {
                String ganador = "Jugador " + turno + " ha ganado!";
                JOptionPane.showMessageDialog(this, ganador, "Fin de la partida", JOptionPane.INFORMATION_MESSAGE);
                reset(botones);
                return;
            } else if (tableroCompleto()) {
                JOptionPane.showMessageDialog(this, "Empate!", "Fin de la partida", JOptionPane.INFORMATION_MESSAGE);
                reset(botones);
                return;
            }
            

            turno = (turno == 1) ? 2 : 1;
        }
    }

    private boolean tableroCompleto() {
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                if (botones[i][j].getText().equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }
	
	public boolean verificarGanador() {
	    for (int i = 0; i < 3; i++) {
	        if (!botones[i][0].getText().equals("-") &&
	            botones[i][0].getText().equals(botones[i][1].getText()) &&
	            botones[i][1].getText().equals(botones[i][2].getText())) {
	            return true;
	        }
	        if (!botones[0][i].getText().equals("-") &&
	            botones[0][i].getText().equals(botones[1][i].getText()) &&
	            botones[1][i].getText().equals(botones[2][i].getText())) {
	            return true;
	        }
	    }

	    if (!botones[1][1].getText().equals("-") &&
	        ((botones[0][0].getText().equals(botones[1][1].getText()) &&
	          botones[1][1].getText().equals(botones[2][2].getText())) ||
	         (botones[0][2].getText().equals(botones[1][1].getText()) &&
	          botones[1][1].getText().equals(botones[2][0].getText())))) {
	        return true;
	    }

	    return false;
	}


}