package POJOS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 Código para el examen de HB

 nombre del alumno:
 DNI:

 */
public class Cocinero implements java.io.Serializable {

    private int codigo;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Character sexo;
    private String apodo;

    private Set<Premio> premios = new HashSet<>();

    private Set<Receta> recetas = new HashSet<>();

    private Contactococinero contactococinero;

    private List<Restaurante> restaurantes = new ArrayList<>();
  
    public Cocinero() {
    }

    public Cocinero(String nome, String apellido1, String apellido2, Character sexo, String apodo) {
        this.nombre = nome;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.sexo = sexo;
        this.apodo = apodo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }
  

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Set<Premio> getPremios() {
        return premios;
    }

    public void setPremios(Set<Premio> premios) {
        this.premios = premios;
    }

    public Set<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(Set<Receta> recetas) {
        this.recetas = recetas;
    }

    public Contactococinero getContactococinero() {
        return contactococinero;
    }

    public void setContactococinero(Contactococinero contactococinero) {
        this.contactococinero = contactococinero;
    }

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }
}
