package Ejercicio1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ejercicio1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFirstName;
	private JTextField textTitle;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio1 frame = new Ejercicio1();
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
	public Ejercicio1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(6, 11, 574, 125);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(35, 23, 65, 14);
		panel.add(lblFirstName);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(110, 20, 151, 20);
		panel.add(textFirstName);
		textFirstName.setColumns(10);
		
		textTitle = new JTextField();
		textTitle.setBounds(110, 59, 151, 20);
		panel.add(textTitle);
		textTitle.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(62, 62, 38, 14);
		panel.add(lblTitle);
		
		textField_1 = new JTextField();
		textField_1.setBounds(371, 20, 176, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(296, 23, 65, 14);
		panel.add(lblLastName);
		
		textField_2 = new JTextField();
		textField_2.setBounds(371, 59, 176, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNickName = new JLabel("Nick Name:");
		lblNickName.setBounds(296, 62, 65, 14);
		panel.add(lblNickName);
		
		JComboBox<?> comboDisplay = new JComboBox<>();
		comboDisplay.setBounds(110, 89, 437, 22);
		panel.add(comboDisplay);
		
		JLabel lblNewLabel = new JLabel("Display Format: ");
		lblNewLabel.setBounds(10, 93, 90, 14);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Email", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(6, 147, 574, 230);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEmail = new JLabel("E-mail Address:");
		lblEmail.setBounds(6, 19, 93, 14);
		panel_1.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(109, 16, 356, 20);
		panel_1.add(textEmail);
		textEmail.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(475, 19, 89, 23);
		panel_1.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(475, 69, 89, 23);
		panel_1.add(btnEdit);
		
		JButton btnAdd_1_1 = new JButton("As default");
		btnAdd_1_1.setBounds(475, 169, 89, 23);
		panel_1.add(btnAdd_1_1);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(475, 119, 89, 23);
		panel_1.add(btnRemove);
		
		JList<?> list = new JList<>();
		list.setBounds(16, 44, 449, 125);
		panel_1.add(list);
		
		JLabel lblFormat = new JLabel("Mail Format");
		lblFormat.setBounds(6, 180, 78, 14);
		panel_1.add(lblFormat);
		
		JRadioButton rdbtnHTML = new JRadioButton("HTML");
		rdbtnHTML.setBounds(31, 201, 63, 23);
		panel_1.add(rdbtnHTML);
		
		JRadioButton rdbtnHTML_1 = new JRadioButton("Plain text");
		rdbtnHTML_1.setBounds(96, 201, 74, 23);
		panel_1.add(rdbtnHTML_1);
		
		JRadioButton rdbtnHTML_2 = new JRadioButton("Custom");
		rdbtnHTML_2.setBounds(172, 201, 74, 23);
		panel_1.add(rdbtnHTML_2);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(475, 384, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOk.setBounds(371, 384, 89, 23);
		contentPane.add(btnOk);

	}
}
