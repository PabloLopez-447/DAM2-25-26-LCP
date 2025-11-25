package UD1.ficherosBinarios.clases;

import java.util.HashSet;
import java.util.Set;

public class Equipo {
    private int idEquipo, numPatrocinadores;
    private String nombre;
    private boolean borrado = false;
    private Set<Patrocinador> patrocinadores = new HashSet<>();
}
