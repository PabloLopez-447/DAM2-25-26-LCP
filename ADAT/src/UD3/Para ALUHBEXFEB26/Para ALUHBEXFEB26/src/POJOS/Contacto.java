package POJOS;

import java.io.Serializable;

// Pablo López Couso DNI:77550221V

public class Contacto implements Serializable {
    private String telefonofijo;
    private String telefonomovil;
    private String email;

    public Contacto() {
    }

    public String getTelefonofijo() {
        return telefonofijo;
    }

    public void setTelefonofijo(String telefonofijo) {
        this.telefonofijo = telefonofijo;
    }

    public String getTelefonomovil() {
        return telefonomovil;
    }

    public void setTelefonomovil(String telefonomovil) {
        this.telefonomovil = telefonomovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
