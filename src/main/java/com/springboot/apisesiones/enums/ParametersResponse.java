package com.springboot.apisesiones.enums;

public enum ParametersResponse {

    IP_PARAMETER("ip"),
    CC_PARAMETER("Cedula"),
    FH_PARAMETER("FechaInicio"),
    HI_PARAMETER("HoraInicio"),
    CREACION("creacion"),
    VALIDACION("validacion"),
    ELIMINACION("eliminacion"),
    JWT("jwt");



    private String nameParameter;

    ParametersResponse(String nameParameter){
        this.nameParameter = nameParameter;
    }

    public String getParameter(){return this.nameParameter;}
}
