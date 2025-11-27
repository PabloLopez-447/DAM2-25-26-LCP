package UD1.examen.parte2.persistencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//Pablo López Couso DNI:77550221V

public class UtilidadesFechas {

    // Formato: dd-MMMM-yyyy -> ejemplo: 05-Agosto-2025
    private static final DateTimeFormatter FORMATO_LARGO =
            DateTimeFormatter.ofPattern("dd-MMMM-yyyy", new Locale("es", "ES"));

    // Formato: dd/MM/yyyy -> ejemplo: 05/08/2025
    private static final DateTimeFormatter FORMATO_CORTO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");


    // --------------- Conversión LocalDate <-> long ----------------

    public static long toLongFecha(LocalDate fecha) {
        return (fecha == null) ? Long.MIN_VALUE : fecha.toEpochDay();
    }

    public static LocalDate fromLongFecha(long valor) {
        return (valor == Long.MIN_VALUE) ? null : LocalDate.ofEpochDay(valor);
    }
}

