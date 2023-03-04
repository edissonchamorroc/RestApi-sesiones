package com.springboot.apisesiones.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@NoArgsConstructor
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter
    private String ip;
    @Getter @Setter
    private String cedula;
    @Getter @Setter
    private String fechaInicio;
    @Getter @Setter
    private String navegador;
    @Getter @Setter
    private String horaInicio;


}
