package org.curso.jakarta.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String calle;
    private String colonia;
    private String numero;

    @ManyToOne(optional = false)
    private Cliente cliente;

    public Direccion(){

    }
}
