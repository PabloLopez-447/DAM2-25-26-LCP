package com.example.eva;

public class Candidato {
    int codCandidato, totVotos;
    String nombre, partido;

    public Candidato(int cod, String nom, String part, int votos) {
        this.codCandidato = cod;
        this.nombre = nom;
        this.partido = part;
        this.totVotos = votos;
    }

    public int getCodCandidato() {
        return codCandidato;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPartido() {
        return partido;
    }

    public int getTotVotos() {
        return totVotos;
    }
}
