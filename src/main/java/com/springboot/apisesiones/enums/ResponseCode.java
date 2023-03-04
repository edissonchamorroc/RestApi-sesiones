package com.springboot.apisesiones.enums;

public enum ResponseCode {

    CREACION_EXITOSA("1000"),
    IP_OBLIGATORIO("1100"),
    IP_INCORRECTO("1101"),
    CC_OBLIGATORIO("1102"),
    CC_INCORRECTO("1103"),
    CC_INCOMPLETO("1104"),
    FH_OBLIGATORIO("1105"),
    FH_INCORRECTO("1106"),
    HI_OBLIGATORIO("1107"),
    HI_INCORRECTO("1108"),
    ;

    private String msjCode;

    ResponseCode(String msjCode){
        this.msjCode = msjCode;
    }

    public String getMsjCode(){return this.msjCode;}

}
