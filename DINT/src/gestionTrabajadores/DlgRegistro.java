package gestionTrabajadores;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import supabase.SupabaseAuth;
import org.json.JSONObject;

public class DlgRegistro extends JDialog {

    private JTextField txtEmail;
    private JPasswordField txtPass1, txtPass2;
    private JCheckBox chkAcepto;
    private JButton btnCrear;

    public DlgRegistro(JFrame parent){
        super(parent,"Registrar usuario",true);
        setSize(420,330);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        GridBagConstraints c=new GridBagConstraints();
        c.insets=new Insets(8,8,8,8);
        c.fill=GridBagConstraints.HORIZONTAL;

        c.gridx=0;c.gridy=0; add(new JLabel("Email:"),c);
        txtEmail=new JTextField(); c.gridx=1; add(txtEmail,c);

        c.gridx=0;c.gridy=1; add(new JLabel("Contraseña:"),c);
        txtPass1=new JPasswordField(); c.gridx=1; add(txtPass1,c);

        c.gridx=0;c.gridy=2; add(new JLabel("Repetir contraseña:"),c);
        txtPass2=new JPasswordField(); c.gridx=1; add(txtPass2,c);

        chkAcepto=new JCheckBox("Acepto los términos y condiciones");
        c.gridx=1;c.gridy=3; add(chkAcepto,c);

        btnCrear=new JButton("Crear cuenta");
        btnCrear.setEnabled(false);
        c.gridx=1;c.gridy=4; add(btnCrear,c);

        // Habilitación dinámica -----------------------------
        DocumentListener doc=new DocumentListener(){
            public void insertUpdate(DocumentEvent e){check();}
            public void removeUpdate(DocumentEvent e){check();}
            public void changedUpdate(DocumentEvent e){check();}
        };

        txtEmail.getDocument().addDocumentListener(doc);
        txtPass1.getDocument().addDocumentListener(doc);
        txtPass2.getDocument().addDocumentListener(doc);
        chkAcepto.addActionListener(e->check());

        btnCrear.addActionListener(e->registrar(parent));
    }

    private void registrar(JFrame parent){
        String mail=txtEmail.getText();
        String p1=new String(txtPass1.getPassword());
        String p2=new String(txtPass2.getPassword());

        if(!p1.equals(p2)){
            JOptionPane.showMessageDialog(this,"Las contraseñas no coinciden");
            return;
        }

        try{
            String json=SupabaseAuth.register(mail,p1);
            JSONObject res=new JSONObject(json);

            if(res.has("access_token")){
                JOptionPane.showMessageDialog(this,"Registro completo, inicia sesión.");
                dispose();
                new DlgLogin(parent).setVisible(true);
            }else{
                JOptionPane.showMessageDialog(this,"No se pudo registrar:\n"+json);
            }

        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Error:\n"+ex.getMessage());
        }
    }

    private void check(){
        boolean ok = !txtEmail.getText().isEmpty()
                && txtPass1.getPassword().length>0
                && txtPass2.getPassword().length>0
                && chkAcepto.isSelected();
        btnCrear.setEnabled(ok);
    }
}
