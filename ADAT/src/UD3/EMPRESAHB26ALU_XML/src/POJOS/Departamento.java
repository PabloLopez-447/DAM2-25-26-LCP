package POJOS;


import java.util.HashSet;
import java.util.Set;

public class Departamento  implements java.io.Serializable {


     private int numDepartamento;
     private java.lang.String nomeDepartamento;
     private Set<String> funciones = new HashSet<>();
    

    public Departamento() {
    }

    public int getNumDepartamento() {
        return numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        this.numDepartamento = numDepartamento;
    }

    public java.lang.String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(java.lang.String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public Set<String> getFunciones() {
        return funciones;
    }

    public void setFunciones(Set<String> funciones) {
        this.funciones = funciones;
    }
}


