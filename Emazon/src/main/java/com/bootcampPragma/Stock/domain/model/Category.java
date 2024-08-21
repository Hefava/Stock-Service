package com.bootcampPragma.Stock.domain.model;

public class Category {

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
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

    public Category(Long categoryID, String nombre, String descripcion) {
        this.categoryID = categoryID;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    private Long categoryID;
    private String nombre;
    private String descripcion;

}
