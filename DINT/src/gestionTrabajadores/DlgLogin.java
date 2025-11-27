package gestionTrabajadores;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.json.JSONObject;
import supabase.SupabaseAuth;

public class DlgLogin extends JDialog {

    private JTextField txtEmail;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JButton btnRegistro;

    private String accessToken = null;
    private String emailSesion = null;

    public DlgLogin(JFrame parent) {
        super(parent, "Iniciar sesión", true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblEmail = new JLabel("Email:");
        c.gridx = 0; c.gridy = 0;
        add(lblEmail,c);

        txtEmail = new JTextField();
        c.gridx = 1; c.gridy = 0;
        add(txtEmail,c);

        JLabel lblPass = new JLabel("Contraseña:");
        c.gridx = 0; c.gridy = 1;
        add(lblPass,c);

        txtPass = new JPasswordField();
        c.gridx = 1; c.gridy = 1;
        add(txtPass,c);

        btnLogin = new JButton("Acceder");
        btnLogin.setEnabled(false);
        c.gridx = 1; c.gridy = 2;
        add(btnLogin,c);

        btnRegistro = new JButton("Registrarse");
        c.gridx = 1; c.gridy = 3;
        add(btnRegistro,c);

        // Habilitación del botón solo si ambos campos tienen texto
        DocumentListener doc = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { check(); }
            public void removeUpdate(DocumentEvent e) { check(); }
            public void changedUpdate(DocumentEvent e){ check(); }
        };

        txtEmail.getDocument().addDocumentListener(doc);
        txtPass.getDocument().addDocumentListener(doc);

        // --- LOGIN -----------------------------------------------------------------
        btnLogin.addActionListener(e -> {
            try {
                String json = SupabaseAuth.login(txtEmail.getText(), String.valueOf(txtPass.getPassword()));
                JSONObject obj = new JSONObject(json);

                if (!obj.has("access_token")) {
                    JOptionPane.showMessageDialog(this,"Credenciales incorrectas o Supabase no respondió.\n"+ json);
                    return;
                }

                accessToken = obj.getString("access_token");
                emailSesion = txtEmail.getText();
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Error al conectar con Supabase:\n"+ex.getMessage());
            }
        });

        // IR A REGISTRO -------------------------------------------------------------
        btnRegistro.addActionListener(e -> {
            dispose();
            new DlgRegistro(parent).setVisible(true);
        });
    }

    private void check(){
        btnLogin.setEnabled(!txtEmail.getText().isEmpty() && txtPass.getPassword().length>0);
    }

    public String getToken(){ return accessToken; }
    public String getEmail(){ return emailSesion; }
}
