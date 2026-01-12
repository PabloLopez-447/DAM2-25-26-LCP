package UD2.empresa25.actividad3.clases;

public abstract class Vehiculo {
    protected int id;
    protected String matricula;
    protected String marca;
    protected String modelo;
    protected String combustible;

    public Vehiculo(String matricula, String marca, String modelo, String combustible) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.combustible = combustible;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMatricula() { return matricula; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getCombustible() { return combustible; }
}
