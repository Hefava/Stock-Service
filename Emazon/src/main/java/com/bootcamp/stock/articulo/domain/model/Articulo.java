package com.bootcamp.stock.articulo.domain.model;

import com.bootcamp.stock.categoria.domain.model.Category;
import com.bootcamp.stock.marca.domain.model.Marca;

import java.util.Set;

public class Articulo {

    private Long articuloID;
    private String nombre;
    private String descripcion;
    private Long cantidad;
    private Double precio;
    private Set<Category> categorias;
    private Marca marca;

    public Articulo() {
    }

    public Articulo(Long articuloID, String nombre, String descripcion, Long cantidad, Double precio, Set<Category> categorias, Marca marca) {
        this.articuloID = articuloID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categorias = categorias;
        this.marca = marca;
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

    public Set<Category> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Category> categorias) {
        this.categorias = categorias;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
