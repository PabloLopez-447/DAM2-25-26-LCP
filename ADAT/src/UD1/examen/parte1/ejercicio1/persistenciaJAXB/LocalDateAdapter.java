package UD1.examen.parte1.ejercicio1.persistenciaJAXB;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Pablo López Couso DNI:77550221V
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Override
    public LocalDate unmarshal(String s) throws Exception {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return LocalDate.parse(s);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        if (localDate == null) {
            return null;
        }
        return localDate.format(formatter);
    }
}
