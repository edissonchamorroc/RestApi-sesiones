package com.springboot.apisesiones.enums;

public enum ResponseCode {

    CREACION_EXITOSA("1100"),
    VALIDACION_EXITOSA("1101"),
    ELIMINACION_EXITOSA("1102"),
    IP_OBLIGATORIO("1200"),
    IP_INCORRECTO("1201"),
    CC_OBLIGATORIO("1202"),
    CC_INCORRECTO("1203"),
    CC_INCOMPLETO("1204"),
    FH_OBLIGATORIO("1205"),
    FH_INCORRECTO("1206"),
    HI_OBLIGATORIO("1207"),
    HI_INCORRECTO("1208"),
    SESION_NO_EXISTE("1300")

    ;

    private String msjCode;

    ResponseCode(String msjCode) {
        this.msjCode = msjCode;
    }

    public String getMsjCode() {
        return this.msjCode;
    }

}
