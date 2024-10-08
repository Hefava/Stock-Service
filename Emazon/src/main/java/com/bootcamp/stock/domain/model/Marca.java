package com.bootcamp.stock.domain.model;

public class Marca {

    private Long marcaID;
    private String nombre;
    private String descripcion;

    public Marca() {
    }

    public Marca(Long marcaID, String nombre, String descripcion) {
        this.marcaID = marcaID;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getMarcaID() {
        return marcaID;
    }

    public void setMarcaID(Long marcaID) {
        this.marcaID = marcaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
