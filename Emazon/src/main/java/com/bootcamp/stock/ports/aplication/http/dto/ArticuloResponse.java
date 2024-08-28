package com.bootcamp.stock.ports.aplication.http.dto;

import java.util.List;

public class ArticuloResponse {
    private Long articuloID;
    private String nombre;
    private String descripcion;
    private Long cantidad;
    private Double precio;
    private String marcaNombre;
    private List<CategoriaDto> categorias;

    public ArticuloResponse() {
    }

    public ArticuloResponse(Long articuloID, String nombre, String descripcion, Long cantidad, Double precio, String marcaNombre, List<CategoriaDto> categorias) {
        this.articuloID = articuloID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.marcaNombre = marcaNombre;
        this.categorias = categorias;
    }

    public static class CategoriaDto {
        private Long id;
        private String nombre;

        public CategoriaDto() {
        }

        public CategoriaDto(Long id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }

    public Long getArticuloID() {
        return articuloID;
    }

    public void setArticuloID(Long articuloID) {
        this.articuloID = articuloID;
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

    public List<CategoriaDto> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDto> categorias) {
        this.categorias = categorias;
    }
}
