package POJOS;

/*
 Código para el examen de HB

 nombre del alumno:
 DNI:

 */

public class Publicacion  implements java.io.Serializable {


     private int codpublicacion;
     private String titulo;
     private String editorial;
     private Double precio;
     private String tipo;
     private String isbn;
     private Integer numedicion;
     private Integer anoedicion;
     
     
     

    public Publicacion() {
    }

 
   

    public int getCodpublicacion() {
        return codpublicacion;
    }

    public void setCodpublicacion(int codpublicacion) {
        this.codpublicacion = codpublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNumedicion() {
        return numedicion;
    }

    public void setNumedicion(Integer numedicion) {
        this.numedicion = numedicion;
    }

    public Integer getAnoedicion() {
        return anoedicion;
    }

    public void setAnoedicion(Integer anoedicion) {
        this.anoedicion = anoedicion;
    }

    

}


