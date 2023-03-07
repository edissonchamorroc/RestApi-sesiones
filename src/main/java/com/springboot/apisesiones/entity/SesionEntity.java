package com.springboot.apisesiones.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public abstract class SesionEntity {

    @Getter
    @Setter
    private String ip;

    @Getter
    @Setter
    private String cedula;

    @Getter
    @Setter
    private String fechaInicio;

    @Getter
    @Setter
    private String navegador;

    @Getter
    @Setter
    private String horaInicio;
}
