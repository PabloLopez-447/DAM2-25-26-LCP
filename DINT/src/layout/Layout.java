package layout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Layout extends JFrame {

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
					Layout frame = new Layout();
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
	public Layout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btn1 = new JButton("Boton1");
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("Boton2");
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("Boton3");
		contentPane.add(btn3);
		
		JButton btn4 = new JButton("Long named button 4");
		contentPane.add(btn4);
		
		JButton btn5 = new JButton("5");
		contentPane.add(btn5);
		
		JRadioButton rdbtnLtR = new JRadioButton("Left to Right");
		buttonGroup.add(rdbtnLtR);
		contentPane.add(rdbtnLtR);
		
		JRadioButton rdbtnRtL = new JRadioButton("Right to Left");
		buttonGroup.add(rdbtnRtL);
		contentPane.add(rdbtnRtL);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnRtL.isSelected()) {
					contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
					contentPane.revalidate();
					contentPane.repaint();
				}
				if(rdbtnLtR.isSelected()) {
					contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					contentPane.revalidate();
					contentPane.repaint();
				}
			}
		});
		contentPane.add(btnApply);

	}

}
