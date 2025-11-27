package UD1.examen.parte2.clases;

import java.nio.charset.StandardCharsets;
import java.util.List;

//Pablo López Couso DNI:77550221V

public class Fotografo {
    private boolean borrado;
    private int codigo;
    private String numLicencia;
    private String nombre;
    private String estudio;
    private int numFotografia;
    private List<Fotografia> fotografias;

    public Fotografo(boolean borrado, int codigo, String numLicencia, String nombre, String estudio, int numFotografia, List<Fotografia> fotografias) {
        this.borrado = borrado;
        this.codigo = codigo;
        this.numLicencia = numLicencia;
        this.nombre = nombre;
        this.estudio = estudio;
        this.numFotografia = numFotografia;
        this.fotografias = fotografias;
    }

    public int bytesSerializacionEquipo() {

        int CamposEnterosBytes = Integer.BYTES * 2; // id y numPatrocinadores
        int borradoBytes = 1; // boolean
        int contenidoUtf = 0;

        if (this.numLicencia != null && !this.numLicencia.isEmpty()) {
            // StandardCharsets.UTF_8 garantiza que no se lanza UnsupportedEncodingException
            contenidoUtf += this.numLicencia.getBytes(StandardCharsets.UTF_8).length;
        }
        if (this.nombre != null && !this.nombre.isEmpty()) {
            // StandardCharsets.UTF_8 garantiza que no se lanza UnsupportedEncodingException
            contenidoUtf += this.nombre.getBytes(StandardCharsets.UTF_8).length;
        }
        if (this.estudio != null && !this.estudio.isEmpty()) {
            // StandardCharsets.UTF_8 garantiza que no se lanza UnsupportedEncodingException
            contenidoUtf += this.estudio.getBytes(StandardCharsets.UTF_8).length;
        }

        /* Si se supera el límite de writeUTF (65535 bytes de contenido) → error */
        if (contenidoUtf > 0xFFFF) {
            throw new IllegalArgumentException("Nombre demasiado largo para writeUTF: " + contenidoUtf + " bytes");
        }

        // Aproximamos writeUTF como 2 bytes de prefijo + longitud en bytes UTF-8
        int nombreTotal = 2 + contenidoUtf;
        int bytesFotografias = 0;
        numFotografia = 0;

        // Sumar bytes de patrocinadores; protegemos contra patrocinadores == null
        if (fotografias != null) {
            for (Fotografia f : fotografias) {
                bytesFotografias += f.bytesSerializacionPatrocinador();
            }
        }

        return nombreTotal + CamposEnterosBytes + borradoBytes + bytesFotografias;
    }

    public Fotografo() {
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNumLicencia() {
        return numLicencia;
    }

    public void setNumLicencia(String numLicencia) {
        this.numLicencia = numLicencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public int getNumFotografia() {
        return numFotografia;
    }

    public void setNumFotografia(int numFotografia) {
        this.numFotografia = numFotografia;
    }

    public List<Fotografia> getFotografias() {
        return fotografias;
    }

    public void setFotografias(List<Fotografia> fotografias) {
        this.fotografias = fotografias;
    }

    @Override
    public String toString() {
        String fotografo = "Codigo " +  this.codigo + " Licencia: " +  this.numLicencia + "Nombre: " + this.nombre +
                "Estudio: " + this.estudio + "Número de fotografias: " + this.numFotografia + "\n" + "Fotografias: ";

        String fotografiasString = "";
        for (Fotografia f : fotografias) {
            fotografiasString += "\n" + f.toString();
        }

        return fotografo +  fotografiasString;
    }
}
