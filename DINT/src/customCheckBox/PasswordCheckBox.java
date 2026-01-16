package customCheckBox;

import javax.swing.*;

public class PasswordCheckBox extends JCheckBox {

	private static final long serialVersionUID = 1L;
	// Propiedad propia (el String de la contraseña)
    private String password = "admin";

    public PasswordCheckBox(String text) {
        super(text);
        initBehavior();
    }

    private void initBehavior() {
        addActionListener(e -> {
            // Solo pedimos contraseña cuando intentan marcarlo
            if (isSelected()) {
                JPasswordField field = new JPasswordField();
                int result = JOptionPane.showConfirmDialog(
                        this,
                        field,
                        "Introduce la contraseña",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result != JOptionPane.OK_OPTION ||
                        !password.equals(new String(field.getPassword()))) {

                    // Contraseña incorrecta o cancelado
                    setSelected(false);
                }
            }
        });
    }

    // Getter / Setter de la propiedad propia
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
