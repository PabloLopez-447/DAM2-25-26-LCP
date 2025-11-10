package manin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import supabase.SupabaseAuth;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textEmail;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 447, 580);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 36, 403, 34, 0 };
		gbl_contentPanel.rowHeights = new int[] { 320, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblIcono = new JLabel("");
			lblIcono.setIcon(new ImageIcon(Login.class.getResource("/manin/user_icon_149851.png")));
			GridBagConstraints gbc_lblIcono = new GridBagConstraints();
			gbc_lblIcono.insets = new Insets(0, 0, 5, 5);
			gbc_lblIcono.gridx = 1;
			gbc_lblIcono.gridy = 0;
			contentPanel.add(lblIcono, gbc_lblIcono);
		}
		{
			JLabel lblEmail = new JLabel("Email");
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.anchor = GridBagConstraints.WEST;
			gbc_lblEmail.gridx = 1;
			gbc_lblEmail.gridy = 1;
			contentPanel.add(lblEmail, gbc_lblEmail);
		}
		{
			textEmail = new JTextField();
			GridBagConstraints gbc_textEmail = new GridBagConstraints();
			gbc_textEmail.insets = new Insets(0, 0, 5, 5);
			gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_textEmail.gridx = 1;
			gbc_textEmail.gridy = 2;
			contentPanel.add(textEmail, gbc_textEmail);
			textEmail.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.anchor = GridBagConstraints.WEST;
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 1;
			gbc_lblPassword.gridy = 3;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 4;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
		}
		{
			JButton btnLogin = new JButton("Iniciar Sesión");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
					String regiter=SupabaseAuth.login(textEmail.getText(), textField_1.getText());
						System.out.println(regiter);
					} catch (Exception excepcion) {

					}
				}
			});
			GridBagConstraints gbc_btnLogin = new GridBagConstraints();
			gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
			gbc_btnLogin.gridx = 1;
			gbc_btnLogin.gridy = 5;
			contentPanel.add(btnLogin, gbc_btnLogin);
		}
		{
			JButton btnCrearCuenta = new JButton("Crear cuenta");
			btnCrearCuenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Register register = new Register();
					register.setVisible(true);
				}
			});
			GridBagConstraints gbc_btnCrearCuenta = new GridBagConstraints();
			gbc_btnCrearCuenta.insets = new Insets(0, 0, 0, 5);
			gbc_btnCrearCuenta.gridx = 1;
			gbc_btnCrearCuenta.gridy = 7;
			contentPanel.add(btnCrearCuenta, gbc_btnCrearCuenta);
		}
	}

}
