package com.springboot.apisesiones.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class ValidateDeleteSesion extends CreateSesion{

    @ApiModelProperty(position = 5)
    @Getter
    @Setter
    private String jwt;
}
