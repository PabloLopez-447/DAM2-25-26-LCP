package calculadora;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculadora extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static String operador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculadora frame = new Calculadora();
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
	public Calculadora() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOperacion = new JLabel("");
		lblOperacion.setBackground(new Color(255, 255, 255));
		lblOperacion.setBounds(10, 27, 314, 69);
		contentPane.add(lblOperacion);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperacion.setText(lblOperacion.getText()+"7");
			}
		});
		btn7.setBounds(18, 116, 61, 61);
		contentPane.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperacion.setText(lblOperacion.getText()+"8");
			}
		});
		btn8.setBounds(97, 116, 61, 61);
		contentPane.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperacion.setText(lblOperacion.getText()+"9");
			}
		});
		btn9.setBounds(176, 116, 61, 61);
		contentPane.add(btn9);
		
		JButton btnMas = new JButton("+");
		btnMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblOperacion.getText().endsWith("+") || lblOperacion.getText().endsWith("-") || lblOperacion.getText().endsWith("/") || lblOperacion.getText().endsWith("*")){
					lblOperacion.setText(lblOperacion.getText().substring(0, lblOperacion.getText().length()-1)+"+");
				}else {
					lblOperacion.setText(lblOperacion.getText()+"+");
				}
				operador="+";
			}
		});
		btnMas.setBounds(255, 116, 61, 61);
		contentPane.add(btnMas);
		
		JButton btnMenos = new JButton("-");
		btnMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblOperacion.getText().endsWith("+") || lblOperacion.getText().endsWith("-") || lblOperacion.getText().endsWith("/") || lblOperacion.getText().endsWith("*")){
					lblOperacion.setText(lblOperacion.getText().substring(0, lblOperacion.getText().length()-1)+"-");
				}else {
					lblOperacion.setText(lblOperacion.getText()+"-");
				}
				operador="-";
			}
		});
		btnMenos.setBounds(255, 208, 61, 61);
		contentPane.add(btnMenos);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperacion.setText(lblOperacion.getText()+"4");
			}
		});
		btn4.setBounds(176, 208, 61, 61);
		contentPane.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperacion.setText(lblOperacion.getText()+"5");
			}
		});
		btn5.setBounds(97, 208, 61, 61);
		contentPane.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperacion.setText(lblOperacion.getText()+"6");
			}
		});
		btn6.setBounds(18, 208, 61, 61);
		contentPane.add(btn6);
		
		JButton btnEntre = new JButton("/");
		btnEntre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblOperacion.getText().endsWith("+") || lblOperacion.getText().endsWith("-") || lblOperacion.getText().endsWith("/") || lblOperacion.getText().endsWith("*")){
					lblOperacion.setText(lblOperacion.getText().substring(0, lblOperacion.getText().length()-1)+"/");
				}else {
					lblOperacion.setText(lblOperacion.getText()+"/");
				}
				operador="/";
			}
		});
		btnEntre.setBounds(255, 294, 61, 61);
		contentPane.add(btnEntre);
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperacion.setText(lblOperacion.getText()+"1");
			}
		});
		btn1.setBounds(176, 294, 61, 61);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperacion.setText(lblOperacion.getText()+"2");
			}
		});
		btn2.setBounds(97, 294, 61, 61);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperacion.setText(lblOperacion.getText()+"3");
			}
		});
		btn3.setBounds(18, 294, 61, 61);
		contentPane.add(btn3);
		
		JButton btnPor = new JButton("x");
		btnPor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblOperacion.getText().endsWith("+") || lblOperacion.getText().endsWith("-") || lblOperacion.getText().endsWith("/") || lblOperacion.getText().endsWith("*")){
					lblOperacion.setText(lblOperacion.getText().substring(0, lblOperacion.getText().length()-1)+"*");
				}else {
					lblOperacion.setText(lblOperacion.getText()+"*");
				}
				operador="*";
			}
		});
		btnPor.setBounds(255, 384, 61, 61);
		contentPane.add(btnPor);
		
		JButton btnIgual = new JButton("=");
		btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexOp = -1;

			    if (lblOperacion.getText().contains("+")) {
			        indexOp = lblOperacion.getText().indexOf("+");
			    } else if (lblOperacion.getText().contains("-")) {
			        indexOp = lblOperacion.getText().indexOf("-");
			    } else if (lblOperacion.getText().contains("*")) {
			        indexOp = lblOperacion.getText().indexOf("*");
			    } else if (lblOperacion.getText().contains("/")) {
			        indexOp = lblOperacion.getText().indexOf("/");
			    }
				
				int num1 = Integer.parseInt(lblOperacion.getText().substring(0, indexOp));
			    int num2 = Integer.parseInt(lblOperacion.getText().substring(indexOp + 1));
				int res = 0;
				
				switch(operador) {
				case "+": res = num1 + num2;break;
				case "-": res = num1 - num2;break;
				case "/": res = num1 / num2;break;
				case "*": res = num1 * num2;break;
				}
				
				lblOperacion.setText(String.valueOf(res));
				operador="";
			}
		});
		btnIgual.setBounds(176, 384, 61, 61);
		contentPane.add(btnIgual);
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperacion.setText(lblOperacion.getText()+"0");
			}
		});
		btn0.setBounds(97, 384, 61, 61);
		contentPane.add(btn0);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperacion.setText("");
				operador="";
			}
		});
		btnC.setBounds(18, 384, 61, 61);
		contentPane.add(btnC);

	}
	
}
