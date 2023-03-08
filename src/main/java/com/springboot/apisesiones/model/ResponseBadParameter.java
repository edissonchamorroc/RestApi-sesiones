package com.springboot.apisesiones.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseBadParameter {

    @ApiModelProperty(position = 0)
    @Getter
    @Setter
    private String codigo;
    @ApiModelProperty(position = 1)
    @Getter
    @Setter
    private String parametro;
    @ApiModelProperty(position = 2)
    @Getter
    @Setter
    private String descripcion;

    public ResponseBadParameter(String parametro){
        this.parametro=parametro;
    }
}
