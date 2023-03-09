package com.springboot.apisesiones.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseOk implements Response{

    @ApiModelProperty(position = 0)
    @Getter @Setter
    public String codigo;
    @ApiModelProperty(position = 1)
    @Getter @Setter
    public String mensaje;
    @ApiModelProperty(position = 2)
    @Getter @Setter
    public String token;
    @ApiModelProperty(position = 3)
    @Getter @Setter
    public String estado;

    public ResponseOk(String codigo, String mensaje,  String estado){
        this.token = null;
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.estado = estado;
    }

    public ResponseOk(String codigo, String mensaje){
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

}
