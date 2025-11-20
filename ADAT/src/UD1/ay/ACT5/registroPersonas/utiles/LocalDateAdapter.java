package UD1.ay.ACT5.registroPersonas.utiles;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");

    @Override
    public LocalDate unmarshal(String s) throws Exception {
        if (s == null || s.isEmpty()) {
            return null;
        }

        return LocalDate.parse(s, formatter);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        //|| localDate.isBefore(LocalDate.now())
        if (localDate == null ) {
            return null;
        }

        return localDate.format(formatter);
    }
}
