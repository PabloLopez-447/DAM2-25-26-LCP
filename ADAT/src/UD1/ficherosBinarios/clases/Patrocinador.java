package UD1.ficherosBinarios.clases;

import UD1.ficherosBinarios.UtilidadesFechas;
import UD1.ficherosYDirectorios.Utilidades;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class Patrocinador {

    private String nombre;
    private float donacion;
    private LocalDate fechaInicio;

    public Patrocinador(String nombre, float donacion, LocalDate fechaInicio) {
        this.nombre = nombre;
        this.donacion = donacion;
        this.fechaInicio = fechaInicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getDonacion() {
        return donacion;
    }

    public void setDonacion(float donacion) {
        this.donacion = donacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    // --------------------------------------------------------------

    public int bytesSerializacionPatrocinador() {
        int contenidoUtf = 0;

        if (this.nombre != null && !this.nombre.isEmpty()) {
            // StandardCharsets.UTF_8 garantiza que no se lanza UnsupportedEncodingException
            contenidoUtf = this.nombre.getBytes(StandardCharsets.UTF_8).length;

            // Si se supera el límite de writeUTF (65535 bytes de contenido) → error de validación unchecked
            if (contenidoUtf > 0xFFFF) {
                throw new IllegalArgumentException("Nombre demasiado largo para writeUTF: " + contenidoUtf);
            }
        }

        // aproximamos writeUTF como 2 bytes de prefijo + longitud en bytes UTF-8
        int nombreTotal = 2 + contenidoUtf;

        int donacionBytes = Float.BYTES;      // 4
        int fechaBytes    = Long.BYTES;       // 8 (epochDay)

        return nombreTotal + donacionBytes + fechaBytes;
    }

    /**
     * equals: define la igualdad de negocio entre dos Patrocinador.
     * - Dos patrocinadores son iguales si sus nombres son iguales ignorando
     *   mayúsculas/minúsculas (case-insensitive).
     * - Se protege contra nombre == null devolviendo false.
     *
     * Efectos prácticos:
     * - Permite que colecciones basadas en hashing (HashSet, LinkedHashSet,
     *   HashMap) detecten duplicados por nombre.
     * - Si se modifica 'nombre' mientras el objeto está en un HashSet/HashMap
     *   puedes romper la colección; por tanto, hay que modificar con cuidado:
     *   remover del set, cambiar nombre, volver a añadir.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Patrocinador)) {
            return false;
        }

        Patrocinador that = (Patrocinador) o;
        return nombre != null && nombre.equalsIgnoreCase(that.nombre);
    }

    @Override
    public int hashCode() {
        return nombre == null ? 0 : nombre.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return String.format(
                "%s | Donación: %.2f | Inicio: %s",
                nombre,
                donacion,
                UtilidadesFechas.formatearCorto(fechaInicio)
        );
    }
}

