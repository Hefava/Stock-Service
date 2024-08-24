package com.bootcamp.stock.marca.ports.persistency.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "marca")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MarcaEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long marcaID;

        @Column(name = "nombre", length = 50, nullable = false, unique = true)
        private String nombre;

        @Column(name = "descripcion", length = 120, nullable = false)
        private String descripcion;
}