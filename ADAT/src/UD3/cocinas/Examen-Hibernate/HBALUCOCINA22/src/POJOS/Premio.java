package POJOS;

import java.util.Objects;

public class Premio implements java.io.Serializable {
    private int idPremio;
    private String nomePremio;
    private int ano;
    private Cocinero cocinero;

    public Premio() {
    }

    public Premio(String nomePremio, int ano) {
        this.nomePremio = nomePremio;
        this.ano = ano;
    }

    public int getIdPremio() {
        return idPremio;
    }

    public void setIdPremio(int idPremio) {
        this.idPremio = idPremio;
    }

    public String getNomePremio() {
        return nomePremio;
    }

    public void setNomePremio(String nomePremio) {
        this.nomePremio = nomePremio;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Cocinero getCocinero() {
        return cocinero;
    }

    public void setCocinero(Cocinero cocinero) {
        this.cocinero = cocinero;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Premio premio)) return false;
        return ano == premio.ano && Objects.equals(nomePremio, premio.nomePremio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomePremio, ano);
    }
}


