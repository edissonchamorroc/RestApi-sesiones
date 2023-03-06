package com.springboot.apisesiones.utility;

import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.entity.ValidateDeleteSesion;
import com.springboot.apisesiones.enums.DescriptionsResponse;
import com.springboot.apisesiones.enums.ParametersResponse;
import com.springboot.apisesiones.enums.ResponseCode;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.springboot.apisesiones.utility.Logica.*;

public class Validate {

    private static final String SPLITER_DOT = "\\.";
    private static List<Map<String, Object>> listResponse;


    public static Map<String, Object> parametersCreation(CreateSesion createSesion) {

        if (validateIp(createSesion)
                || validateCedula(createSesion)
                || validateFechaInicio(createSesion)
                || validateHoraInicio(createSesion)) {

            Map<String, Object> response = new HashMap<>();
            response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.IP_OBLIGATORIO.getMsjCode());
            response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_ERROR_PARAMETRO.getDescription());
            response.put(ParametersResponse.DETALLES.getParameter(), listResponse);

            return response;

        }

        return null;
    }

    public static Map<String, Object> parametersValidation(ValidateDeleteSesion validateCreateSesion, CreateSesion cratedCreateSesion) {

        if (validateIp(validateCreateSesion)
                || validateCedula(validateCreateSesion)
                || validateFechaInicio(validateCreateSesion)
                || validateHoraInicio(validateCreateSesion)
                || validateJwt(validateCreateSesion, cratedCreateSesion)) {

            Map<String, Object> response = new HashMap<>();
            response.put(ParametersResponse.CODIGO.getParameter(), ResponseCode.IP_OBLIGATORIO.getMsjCode());
            response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_ERROR_PARAMETRO.getDescription());
            response.put(ParametersResponse.DETALLES.getParameter(), listResponse);

            return response;

        }

        return null;
    }

    public static boolean validateJwt(ValidateDeleteSesion validateCreateSesion, CreateSesion cratedCreateSesion) {

        if (JwtToken.getJWTToken(cratedCreateSesion).equals(validateCreateSesion.getJwt())) return false;
        else return true;
    }

    public static boolean validateHoraInicio(CreateSesion createSesion) {

        if (createSesion.getHoraInicio() == null) {

            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("hi-obligatorio"));

            return true;

        } else if (contieneCaracterEspecial(createSesion.getHoraInicio(), ":")
                || contieneLetras(createSesion.getHoraInicio())
                || malRangoTiempo(createSesion.getHoraInicio())) {


            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("hi-incorrecto"));

            return true;

        } else return false;

    }


    public static boolean validateFechaInicio(CreateSesion createSesion) {

        String fechaHoy = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));

        if (createSesion.getFechaInicio() == null) {

            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("fh-obligatorio"));

            return true;

        } else if (contieneCaracterEspecial(createSesion.getFechaInicio(), "/")
                || !createSesion.getFechaInicio().equals(fechaHoy)
        ) {


            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("fh-incorrecto"));

            return true;

        } else return false;


    }

    public static boolean validateCedula(CreateSesion createSesion) {

        if (createSesion.getCedula() == null) {

            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("cc-obligatorio"));

            return true;

        } else if (contieneCaracterEspecial(createSesion.getCedula(), "")) {


            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("cc-incorrecto"));

            return true;

        } else if (cantidadDigitos(createSesion.getCedula())) {

            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("cc-incompleto"));

            return true;

        } else return false;

    }

    public static boolean validateIp(CreateSesion createSesion) {

        if (createSesion.getIp() == null) {

            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("ip-obligatorio"));

            return true;

        } else if (camposEnIP(createSesion.getIp()) < 4 || camposEnIP(createSesion.getIp()) > 4
                || !rangosEnCampo(createSesion.getIp(), SPLITER_DOT, 0, 255)) {


            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("ip-incorrecto"));

            return true;

        } else return false;

    }


}
