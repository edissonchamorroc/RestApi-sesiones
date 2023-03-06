package com.springboot.apisesiones.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ResponseCreateBad {

    @ApiModelProperty(position = 0)
    public String codigo;
    @ApiModelProperty(position = 1)
    public String descripcion;
    @ApiModelProperty(position = 2)
    public List<String> detalles;

}
