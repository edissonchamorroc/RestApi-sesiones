package com.springboot.apisesiones.enums;

public enum ParametersResponse {

    CODIGO("Codigo"),
    ESTADO("Estado"),
    PARAMETRO("Parametro"),
    DESCRIPCION("Descripcion"),
    IP_PARAMETER("ip"),
    CC_PARAMETER("Cedula"),
    FH_PARAMETER("FechaInicio"),
    HI_PARAMETER("HoraInicio"),
    MENSAJE("Mensaje"),
    DETALLES("Detalles"),
    TOKEN("Token");



    private String nameParameter;

    ParametersResponse(String nameParameter){
        this.nameParameter = nameParameter;
    }

    public String getParameter(){return this.nameParameter;}
}
