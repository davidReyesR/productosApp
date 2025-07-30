package org.curso.jakarta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 100, unique = true)
    private String nombre;
    private String descripcion;
    private Integer existencia;

    @ManyToOne(/*cascade = CascadeType.PERSIST,*/ optional = false)
    @JoinColumn(name = "cat_id", nullable = false)
    private Categoria categoria;

    public Producto() {

    }

    public Producto(String nombre, String descripcion, Integer existencia, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.categoria = categoria;
    }
}
