package UD1.ficherosYDirectorios;

import java.io.File;
import java.io.FilenameFilter;
//o extension como private final 

public class Filtro implements FilenameFilter {
    String extension;
    Filtro(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(extension);
    }
}
