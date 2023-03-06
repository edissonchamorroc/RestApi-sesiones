package com.springboot.apisesiones.utility;

import com.springboot.apisesiones.entity.Sesion;
import com.springboot.apisesiones.enums.DescriptionsResponse;
import com.springboot.apisesiones.enums.ParametersResponse;
import com.springboot.apisesiones.enums.ResponseCode;

import java.util.HashMap;
import java.util.Map;

public class BodyResponse {


    public static Map<String, Object> error(String tipo) {

        Map<String, Object> response = new HashMap<>();

        switch (tipo) {
            case "ip-obligatorio": {
                response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.IP_OBLIGATORIO.getMsjCode());
                response.put(ParametersResponse.PARAMETRO.getParameter(), ParametersResponse.IP_PARAMETER.getParameter());
                response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_IP_OBLIGATORIA.getDescription());
                break;
            }
            case "ip-incorrecto": {
                response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.IP_INCORRECTO.getMsjCode());
                response.put(ParametersResponse.PARAMETRO.getParameter(), ParametersResponse.IP_PARAMETER.getParameter());
                response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_IP_INCORRECTO.getDescription());
                break;
            }
            case "cc-obligatorio": {
                response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.CC_OBLIGATORIO.getMsjCode());
                response.put(ParametersResponse.PARAMETRO.getParameter(), ParametersResponse.CC_PARAMETER.getParameter());
                response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_CC_OBLIGATORIA.getDescription());
                break;
            }
            case "cc-incorrecto": {
                response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.CC_INCORRECTO.getMsjCode());
                response.put(ParametersResponse.PARAMETRO.getParameter(), ParametersResponse.CC_PARAMETER.getParameter());
                response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_CC_INCORRECTO.getDescription());
                break;
            }
            case "cc-incompleto": {
                response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.CC_INCOMPLETO.getMsjCode());
                response.put(ParametersResponse.PARAMETRO.getParameter(), ParametersResponse.CC_PARAMETER.getParameter());
                response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_CC_INCOMPLETO.getDescription());
                break;
            }
            case "fh-obligatorio": {
                response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.FH_OBLIGATORIO.getMsjCode());
                response.put(ParametersResponse.PARAMETRO.getParameter(), ParametersResponse.FH_PARAMETER.getParameter());
                response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_FH_OBLIGATORIA.getDescription());
                break;
            }
            case "fh-incorrecto": {
                response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.FH_INCORRECTO.getMsjCode());
                response.put(ParametersResponse.PARAMETRO.getParameter(), ParametersResponse.FH_PARAMETER.getParameter());
                response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_FH_INCORRECTO.getDescription());
                break;
            }
            case "hi-obligatorio": {
                response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.HI_OBLIGATORIO.getMsjCode());
                response.put(ParametersResponse.PARAMETRO.getParameter(), ParametersResponse.HI_PARAMETER.getParameter());
                response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_HI_OBLIGATORIA.getDescription());
                break;
            }
            case "hi-incorrecto": {
                response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.HI_INCORRECTO.getMsjCode());
                response.put(ParametersResponse.PARAMETRO.getParameter(), ParametersResponse.HI_PARAMETER.getParameter());
                response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_HI_INCORRECTO.getDescription());
                break;
            }
            default: {
                break;
            }
        }
        return response;
    }

    public static  Map<String, Object> correcta(String tipo, Sesion sesion) {

        Map<String, Object> response = new HashMap<>();

        switch (tipo) {
            case "creacion": {
                response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.CREACION_EXITOSA.getMsjCode());
                response.put(ParametersResponse.ESTADO.getParameter(), DescriptionsResponse.MSJ_ESTADO.getDescription());
                response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_CREACION_EXITOSA.getDescription());
                response.put(ParametersResponse.TOKEN.getParameter(), JwtToken.getJWTToken(sesion));
                break;
            }
            case "validacion": {
                System.out.println("se valido");
                break;
            }
            case "eliminacion": {
                System.out.println("se elimino");
                break;
            }
            default: {
                break;
            }
        }

        return response;
    }
}
