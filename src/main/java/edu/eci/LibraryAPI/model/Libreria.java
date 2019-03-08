package edu.eci.LibraryAPI.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author estudiante
 */
public class Libreria {

    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private Map<Integer, Libro> libros;

    public Libreria() {
    }

    public Libreria(int id, String nombre, String direccion, String telefono, Map<Integer, Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.libros = libros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Map<Integer, Libro> getLibros() {
        return libros;
    }

    public void setLibros(Map<Integer, Libro> libros) {
        this.libros = libros;
    }

}
