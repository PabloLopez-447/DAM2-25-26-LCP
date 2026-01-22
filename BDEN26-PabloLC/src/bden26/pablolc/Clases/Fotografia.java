package bden26.pablolc.Clases;

import java.sql.Date;
//Pablo López Couso DNI: 77550221V

public class Fotografia {
    int codigo,conFotografo, codExposicion;
    String nome, medidas, color;
    Date data;

    public Fotografia(int codigo, int conFotografo, int codExposicion, String nome, String medidas, String color, Date data) {
        this.codigo = codigo;
        this.conFotografo = conFotografo;
        this.codExposicion = codExposicion;
        this.nome = nome;
        this.medidas = medidas;
        this.color = color;
        this.data = data;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getConFotografo() {
        return conFotografo;
    }

    public void setConFotografo(int conFotografo) {
        this.conFotografo = conFotografo;
    }

    public int getCodExposicion() {
        return codExposicion;
    }

    public void setCodExposicion(int codExposicion) {
        this.codExposicion = codExposicion;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
