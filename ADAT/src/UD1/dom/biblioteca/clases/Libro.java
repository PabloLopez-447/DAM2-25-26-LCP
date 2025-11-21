package UD1.dom.biblioteca.clases;

import java.time.LocalDate;
import java.util.List;

public class Libro {
    String id, isbn,titulo;
    LocalDate fechaEdicion;
    float precio;
    List<String> autores;
    List<Copia> copias;

    public Libro(String id, String isbn, String titulo,LocalDate fechaEdicion, float precio, List<String> autores, List<Copia> copias) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.fechaEdicion = fechaEdicion;
        this.precio = precio;
        this.autores = autores;
        this.copias = copias;
    }

    public Libro() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(LocalDate fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public List<Copia> getCopias() {
        return copias;
    }

    public void setCopias(List<Copia> copias) {
        this.copias = copias;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id='" + id + '\'' +
                ", isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", fechaEdicion=" + fechaEdicion +
                ", precio=" + precio +
                ", autores=" + autores +
                ", copias=" + copias +
                '}';
    }
}
