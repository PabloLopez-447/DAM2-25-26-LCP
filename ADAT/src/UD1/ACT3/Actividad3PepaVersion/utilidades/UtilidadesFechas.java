package UD1.ACT3.Actividad3PepaVersion.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UtilidadesFechas {
    private static DateTimeFormatter FORMATO_LARGO = DateTimeFormatter.ofPattern("dd-MMMM-yyyy",
            new Locale("es", "ES"));

    public static String formatearLargo(LocalDate fecha){
        if (fecha == null) return "";
        return fecha.format(FORMATO_LARGO);
    }
}