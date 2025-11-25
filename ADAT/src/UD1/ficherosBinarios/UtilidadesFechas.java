package UD1.ficherosBinarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UtilidadesFechas {

    // Formato: dd-MMMM-yyyy -> ejemplo: 05-Agosto-2025
    private static final DateTimeFormatter FORMATO_LARGO =
            DateTimeFormatter.ofPattern("dd-MMMM-yyyy", new Locale("es", "ES"));

    // Formato: dd/MM/yyyy -> ejemplo: 05/08/2025
    private static final DateTimeFormatter FORMATO_CORTO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Devuelve la fecha en formato largo, con mes en texto y en español.
     * @param fecha LocalDate a formatear
     * @return String con formato "05-Agosto-2025"
     */
    public static String formatearLargo(LocalDate fecha) {
        if (fecha == null) return "";
        return fecha.format(FORMATO_LARGO);
    }

    /**
     * Devuelve la fecha en formato corto clásico.
     * @param fecha LocalDate a formatear
     * @return String con formato "05/08/2025"
     */
    public static String formatearCorto(LocalDate fecha) {
        if (fecha == null) return "";
        return fecha.format(FORMATO_CORTO);
    }

    // --------------- Conversión LocalDate <-> long ----------------

    /**
     * Convierte LocalDate a long usando epochDay. Si la fecha es null, devuelve Long.MIN_VALUE.
     * @param fecha
     * @return
     */
    public static long toLongFecha(LocalDate fecha) {
        return (fecha == null) ? Long.MIN_VALUE : fecha.toEpochDay();
    }

    /**
     * Convierte un long guardado en fichero a LocalDate. Si es Long.MIN_VALUE devuelve null.
     * @param valor
     * @return
     */
    public static LocalDate fromLongFecha(long valor) {
        return (valor == Long.MIN_VALUE) ? null : LocalDate.ofEpochDay(valor);
    }
}

