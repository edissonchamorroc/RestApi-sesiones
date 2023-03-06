package com.springboot.apisesiones.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class CreateSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiModelProperty(position = 0)
    @Getter @Setter
    private String ip;

    @ApiModelProperty(position = 1)
    @Getter @Setter
    private String cedula;

    @ApiModelProperty(position = 2)
    @Getter @Setter
    private String fechaInicio;

    @ApiModelProperty(position = 3)
    @Getter @Setter
    private String navegador;

    @ApiModelProperty(position = 4)
    @Getter @Setter
    private String horaInicio;


}
