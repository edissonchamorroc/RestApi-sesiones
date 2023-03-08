package com.springboot.apisesiones.utility;

import com.springboot.apisesiones.model.SesionEntity;
import com.springboot.apisesiones.enums.DescriptionsResponse;
import com.springboot.apisesiones.enums.ParametersResponse;
import com.springboot.apisesiones.enums.ResponseCode;
import com.springboot.apisesiones.model.Response;
import com.springboot.apisesiones.model.ResponseBadParameter;
import com.springboot.apisesiones.model.ResponseOk;

public class BodyResponse {

    public static ResponseBadParameter error(String tipo) {

        ResponseBadParameter badParameter = null;

        switch (tipo) {
            case "ip-obligatorio": {

                badParameter = new ResponseBadParameter(
                        ResponseCode.IP_OBLIGATORIO.getMsjCode(),
                        ParametersResponse.IP_PARAMETER.getParameter(),
                        DescriptionsResponse.MSJ_DESCRIPCION_IP_OBLIGATORIA.getDescription()
                );

                break;
            }
            case "ip-incorrecto": {

                badParameter = new ResponseBadParameter(
                        ResponseCode.IP_INCORRECTO.getMsjCode(),
                        ParametersResponse.IP_PARAMETER.getParameter(),
                        DescriptionsResponse.MSJ_DESCRIPCION_IP_INCORRECTO.getDescription()
                );

                break;
            }
            case "cc-obligatorio": {

                badParameter = new ResponseBadParameter(
                        ResponseCode.CC_OBLIGATORIO.getMsjCode(),
                        ParametersResponse.CC_PARAMETER.getParameter(),
                        DescriptionsResponse.MSJ_DESCRIPCION_CC_OBLIGATORIA.getDescription()
                );

                break;
            }
            case "cc-incorrecto": {

                badParameter = new ResponseBadParameter(
                        ResponseCode.CC_INCORRECTO.getMsjCode(),
                        ParametersResponse.CC_PARAMETER.getParameter(),
                        DescriptionsResponse.MSJ_DESCRIPCION_CC_INCORRECTO.getDescription()
                );

                break;
            }
            case "cc-incompleto": {

                badParameter = new ResponseBadParameter(
                        ResponseCode.CC_INCOMPLETO.getMsjCode(),
                        ParametersResponse.CC_PARAMETER.getParameter(),
                        DescriptionsResponse.MSJ_DESCRIPCION_CC_INCOMPLETO.getDescription()
                );

                break;
            }
            case "fh-obligatorio": {

                badParameter = new ResponseBadParameter(
                        ResponseCode.FH_OBLIGATORIO.getMsjCode(),
                        ParametersResponse.FH_PARAMETER.getParameter(),
                        DescriptionsResponse.MSJ_DESCRIPCION_FH_OBLIGATORIA.getDescription()
                );

                break;
            }
            case "fh-incorrecto": {

                badParameter = new ResponseBadParameter(
                        ResponseCode.FH_INCORRECTO.getMsjCode(),
                        ParametersResponse.FH_PARAMETER.getParameter(),
                        DescriptionsResponse.MSJ_DESCRIPCION_FH_INCORRECTO.getDescription()
                );

                break;
            }
            case "hi-obligatorio": {

                badParameter = new ResponseBadParameter(
                        ResponseCode.HI_OBLIGATORIO.getMsjCode(),
                        ParametersResponse.HI_PARAMETER.getParameter(),
                        DescriptionsResponse.MSJ_DESCRIPCION_HI_OBLIGATORIA.getDescription()
                );

                break;
            }
            case "hi-incorrecto": {

                badParameter = new ResponseBadParameter(
                        ResponseCode.HI_INCORRECTO.getMsjCode(),
                        ParametersResponse.HI_PARAMETER.getParameter(),
                        DescriptionsResponse.MSJ_DESCRIPCION_HI_INCORRECTO.getDescription()
                );

                break;
            }
            case "jwt-obligatorio": {

                badParameter = new ResponseBadParameter(
                        ResponseCode.JWT_OBLIGATORIO.getMsjCode(),
                        ParametersResponse.JWT.getParameter(),
                        DescriptionsResponse.MSJ_DESCRIPCION_JWT_OBLIGATORIA.getDescription()
                );

                break;
            }
            case "jwt-incorrecto": {

                badParameter = new ResponseBadParameter(
                        ResponseCode.JWT_INCORRECTO.getMsjCode(),
                        ParametersResponse.JWT.getParameter(),
                        DescriptionsResponse.MSJ_DESCRIPCION_JWT_INCORRECTO.getDescription()
                );

                break;
            }
            default: {
                break;
            }
        }
        return badParameter;
    }

    public static Response correcta(String tipo, SesionEntity createSesion) {

        Response response = null;

        switch (tipo) {
            case "creacion": {
                response = new ResponseOk(
                        ResponseCode.CREACION_EXITOSA.getMsjCode(),
                        DescriptionsResponse.MSJ_DESCRIPCION_CREACION_EXITOSA.getDescription(),
                        JwtToken.getJWTToken(createSesion),
                        DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription()
                );
                break;
            }
            case "validacion": {
                response = new ResponseOk(
                        ResponseCode.VALIDACION_EXITOSA.getMsjCode(),
                        DescriptionsResponse.MSJ_DESCRIPCION_VALIDACION_EXITOSA.getDescription(),
                        DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription()
                );
                break;
            }
            case "eliminacion": {
                response = new ResponseOk(
                        ResponseCode.ELIMINACION_EXITOSA.getMsjCode(),
                        DescriptionsResponse.MSJ_DESCRIPCION_ELIMINACION_EXITOSA.getDescription(),
                        DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription()
                );
                break;
            }
            case "no-existe": {
                response = new ResponseOk(
                        ResponseCode.SESION_NO_EXISTE.getMsjCode(),
                        DescriptionsResponse.MSJ_DESCRIPCION_SESION_NO_EXISTE.getDescription(),
                        DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription()
                );
                break;
            }
            default: {
                break;
            }
        }

        return response;
    }
}
