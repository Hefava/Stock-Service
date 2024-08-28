package com.bootcamp.stock.articulo.ports.aplication.http.dto;

import java.util.Set;

public class ArticuloResponse {
    private Long articuloID;
    private String nombre;
    private String descripcion;
    private Long cantidad;
    private Double precio;
    private String marcaNombre;
    private Set<String> categoriaNombres;

    public ArticuloResponse() {
    }

    public ArticuloResponse(Long articuloID, String nombre, String descripcion, Long cantidad, Double precio, String marcaNombre, Set<String> categoriaNombres) {
        this.articuloID = articuloID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.marcaNombre = marcaNombre;
        this.categoriaNombres = categoriaNombres;
    }

    public void setArticuloID(Long articuloID) {
        this.articuloID = articuloID;
    }

    public Long getArticuloID() {
        return articuloID;
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

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }

    public Set<String> getCategoriaNombres() {
        return categoriaNombres;
    }

    public void setCategoriaNombres(Set<String> categoriaNombres) {
        this.categoriaNombres = categoriaNombres;
    }
}
