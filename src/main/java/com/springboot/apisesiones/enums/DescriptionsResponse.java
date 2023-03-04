package com.springboot.apisesiones.enums;

public enum DescriptionsResponse {

    MSJ_ESTADO("Exitoso"),
    MSJ_DESCRIPCION_CREACION_EXITOSA("La sesion ha sido creada exitosamente"),
    MSJ_DESCRIPCION_IP_OBLIGATORIA("El parámetro Ip es obligatorio"),
    MSJ_DESCRIPCION_IP_INCORRECTO("El parámetro Ip es incorrecto"),
    MSJ_DESCRIPCION_CC_OBLIGATORIA("El parámetro Cedula es obligatorio"),
    MSJ_DESCRIPCION_CC_INCORRECTO("El parámetro Cedula es incorrecto"),
    MSJ_DESCRIPCION_CC_INCOMPLETO("El parámetro Cedula no puede contener un valor menor a 5 digitos o mayor a 15 digitos"),
    MSJ_DESCRIPCION_FH_OBLIGATORIA("El parámetro FechaInicio es obligatorio"),
    MSJ_DESCRIPCION_FH_INCORRECTO("El parámetro FechaInicio es incorrecto, la fecha debe ser la actual y el formato de fecha dd/MM/yyyy"),
    MSJ_DESCRIPCION_HI_OBLIGATORIA("El parámetro HoraInicio es obligatorio"),
    MSJ_DESCRIPCION_HI_INCORRECTO("El parámetro HoraInicio es incorrecto, la fecha debe ser en formato de 24H y seguir la estructura hh:mm:ss"),
    MSJ_DESCRIPCION_ERROR_PARAMETRO("Error en parámetro(s)")
    ;


    private String description;

    DescriptionsResponse(String description){
        this.description = description;
    }

    public String getDescription(){return this.description;}
}
