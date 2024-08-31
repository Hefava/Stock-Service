package com.bootcamp.stock.ports.persistency.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "articulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticuloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articuloID;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 150)
    private String descripcion;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "articulo_categoria",
            joinColumns = @JoinColumn(name = "articuloID"),
            inverseJoinColumns = @JoinColumn(name = "categoryId")
    )
    private Set<CategoryEntity> categorias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marcaID")
    private MarcaEntity marca;
}
