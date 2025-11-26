package UD1.ficherosBinarios.clases;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class Equipo {

    private int idEquipo;
    private String nombre;
    private int numPatrocinadores;   // El número de patrocinadores que hay para facilitar la lectura
    private boolean borrado = false;
    private Set<Patrocinador> patrocinadores = new HashSet<>();

    public Equipo(int id, String nombre) {
        this.idEquipo = id;
        this.nombre = nombre;
    }

    public void addPatrocinador(Patrocinador p) {
        patrocinadores.add(p); // Usa equals/hashCode para evitar duplicidad por nombre
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumPatrocinadores() {
        return numPatrocinadores;
    }

    public void setNumPatrocinadores(int numPatrocinadores) {
        this.numPatrocinadores = numPatrocinadores;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Set<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    public void setPatrocinadores(Set<Patrocinador> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    public int bytesSerializacionEquipo() {

        int CamposEnterosBytes = Integer.BYTES * 2; // id y numPatrocinadores
        int borradoBytes = 1; // boolean
        int contenidoUtf = 0;

        if (this.nombre != null && !this.nombre.isEmpty()) {
            // StandardCharsets.UTF_8 garantiza que no se lanza UnsupportedEncodingException
            contenidoUtf = this.nombre.getBytes(StandardCharsets.UTF_8).length;
        }

        /* Si se supera el límite de writeUTF (65535 bytes de contenido) → error */
        if (contenidoUtf > 0xFFFF) {
            throw new IllegalArgumentException("Nombre demasiado largo para writeUTF: " + contenidoUtf + " bytes");
        }

        // Aproximamos writeUTF como 2 bytes de prefijo + longitud en bytes UTF-8
        int nombreTotal = 2 + contenidoUtf;
        int bytesPatrocinadores = 0;
        numPatrocinadores = 0;

        // Sumar bytes de patrocinadores; protegemos contra patrocinadores == null
        if (patrocinadores != null) {
            for (Patrocinador p : patrocinadores) {
                bytesPatrocinadores += p.bytesSerializacionPatrocinador();
            }
        }

        return nombreTotal + CamposEnterosBytes + borradoBytes + bytesPatrocinadores;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(idEquipo)
                .append(" | Nombre: ").append(nombre)
                .append("\n");

        int numPat = patrocinadores == null ? 0 : patrocinadores.size();

        if (numPat > 0) {
            sb.append(numPat == 1 ? "Patrocinador:\n" : "Patrocinadores:\n");
            for (Patrocinador p : patrocinadores) {
                sb.append(" - ").append(p).append("\n");
            }
        } else {
            sb.append("No tiene patrocinadores.\n");
        }

        return sb.toString();
    }
}

