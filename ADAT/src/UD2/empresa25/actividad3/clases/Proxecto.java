package UD2.empresa25.actividad3.clases;

public class Proxecto {
    private int num;
    private String nome;
    private String lugar;
    private int numDepartamento;

    public Proxecto(int num, String nome, String lugar, int numDepartamento) {
        this.num = num;
        this.nome = nome;
        this.lugar = lugar;
        this.numDepartamento = numDepartamento;
    }

    public int getNum() { return num; }
    public String getNome() { return nome; }
    public String getLugar() { return lugar; }
    public int getNumDepartamento() { return numDepartamento; }
}
