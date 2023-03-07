package com.springboot.apisesiones.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseBad implements Response {

    @ApiModelProperty(position = 0)
    @Getter
    @Setter
    private String codigo;
    @ApiModelProperty(position = 1)
    @Getter
    @Setter
    private String descripcion;
    @ApiModelProperty(position = 2)
    @Getter
    @Setter
    private List<ResponseBadParameter> detalles;

}
