package com.bootcamp.stock.articulo.ports.aplication.http.dto;

import java.util.Set;

public class ArticuloRequest {
    private String nombre;
    private String descripcion;
    private Long cantidad;
    private Double precio;
    private Long marcaID;
    private Set<Long> categoriaIDs;

    public ArticuloRequest() {
    }

    public ArticuloRequest(String nombre, String descripcion, Long cantidad, Double precio, Long marcaID, Set<Long> categoriaIDs) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.marcaID = marcaID;
        this.categoriaIDs = categoriaIDs;
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

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getMarcaID() {
        return marcaID;
    }

    public void setMarcaID(Long marcaID) {
        this.marcaID = marcaID;
    }

    public Set<Long> getCategoriaIDs() {
        return categoriaIDs;
    }

    public void setCategoriaIDs(Set<Long> categoriaIDs) {
        this.categoriaIDs = categoriaIDs;
    }
}
