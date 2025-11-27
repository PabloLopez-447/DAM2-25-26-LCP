package UD1.examen.parte2.clases;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

//Pablo López Couso DNI:77550221V

public class Fotografia {
    private String titulo;
    private LocalDate fechaToma;
    private String tipo;
    private double tamanoMB;

    public Fotografia(String titulo, LocalDate fechaToma, String tipo, double tamanoMB) {
        this.titulo = titulo;
        this.fechaToma = fechaToma;
        this.tipo = tipo;
        this.tamanoMB = tamanoMB;
    }

    public int bytesSerializacionPatrocinador() {
        int contenidoUtf = 0;

        if (this.titulo != null && !this.titulo.isEmpty()) {
            // StandardCharsets.UTF_8 garantiza que no se lanza UnsupportedEncodingException
            contenidoUtf += this.titulo.getBytes(StandardCharsets.UTF_8).length;

            if (this.tipo != null && !this.tipo.isEmpty()) {
                // StandardCharsets.UTF_8 garantiza que no se lanza UnsupportedEncodingException
                contenidoUtf += this.tipo.getBytes(StandardCharsets.UTF_8).length;
            }

            // Si se supera el límite de writeUTF (65535 bytes de contenido) → error de validación unchecked
            if (contenidoUtf > 0xFFFF) {
                throw new IllegalArgumentException("Nombre demasiado largo para writeUTF: " + contenidoUtf);
            }
        }

        // aproximamos writeUTF como 2 bytes de prefijo + longitud en bytes UTF-8
        int nombreTotal = 2 + contenidoUtf;

        int tamanoBytes = Double.BYTES;
        int fechaBytes    = Long.BYTES;       // 8 (epochDay)

        return nombreTotal + tamanoBytes + fechaBytes;
    }

    public Fotografia() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaToma() {
        return fechaToma;
    }

    public void setFechaToma(LocalDate fechaToma) {
        this.fechaToma = fechaToma;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTamanoMB() {
        return tamanoMB;
    }

    public void setTamanoMB(double tamanoMB) {
        this.tamanoMB = tamanoMB;
    }

    @Override
    public String toString() {
        return this.titulo + ", " + this.fechaToma.toString() + ", " + this.tipo + ", " + this.tamanoMB;
    }
}
