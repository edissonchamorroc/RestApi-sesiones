package com.springboot.apisesiones.model;

import io.swagger.annotations.ApiModelProperty;

public class ResponseCreateOk {

    @ApiModelProperty(position = 0)
    public String codigo;
    @ApiModelProperty(position = 1)
    public String descripcion;
    @ApiModelProperty(position = 2)
    public String token;
    @ApiModelProperty(position = 3)
    public String estado;

}
