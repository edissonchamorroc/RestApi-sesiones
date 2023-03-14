package com.springboot.apisesiones.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
public class ValidateDeleteSesion implements SesionEntity {

    @Getter
    @Setter
    private String ip;

    @ApiModelProperty(position = 1)
    @Getter
    @Setter
    private String cedula;

    @ApiModelProperty(position = 2)
    @Getter
    @Setter
    private String fechaInicio;

    @ApiModelProperty(position = 3)
    @Getter
    @Setter
    private String navegador;

    @ApiModelProperty(position = 4)
    @Getter
    @Setter
    private String horaInicio;

    @ApiModelProperty(position = 5)
    @Getter
    @Setter
    private String jwt;

    public ValidateDeleteSesion(String ip, String cedula, String fechaInicio, String navegador, String horaInicio, String jwt) {
        this.ip = ip;
        this.cedula = cedula;
        this.fechaInicio = fechaInicio;
        this.navegador = navegador;
        this.horaInicio = horaInicio;
        this.jwt = jwt;
    }
}
