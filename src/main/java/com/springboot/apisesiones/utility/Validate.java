package com.springboot.apisesiones.utility;

import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.model.ValidateDeleteSesion;
import com.springboot.apisesiones.enums.DescriptionsResponse;
import com.springboot.apisesiones.enums.ResponseCode;
import com.springboot.apisesiones.model.Response;
import com.springboot.apisesiones.model.ResponseBadParameter;
import com.springboot.apisesiones.model.ResponseBad;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.springboot.apisesiones.utility.Logica.*;

public class Validate {

    private static final String SPLITER_DOT = "\\.";
    private static List<ResponseBadParameter> listResponse;

    public static Response parametersCreation(CreateSesion createSesion) {

        listResponse = new ArrayList<>();

        validateIp(createSesion);
        validateCedula(createSesion);
        validateFechaInicio(createSesion);
        validateHoraInicio(createSesion);

        if (!listResponse.isEmpty()) {

            return new ResponseBad(
                    ResponseCode.MULTIPLES_ERRORES.getMsjCode(),
                    DescriptionsResponse.MSJ_DESCRIPCION_ERROR_PARAMETRO.getDescription(),
                    listResponse
            );

        } else return null;
    }

    public static Response parametersValidation(ValidateDeleteSesion validateCreateSesion, CreateSesion cratedCreateSesion) {

        listResponse = new ArrayList<>();

        validateIp(validateCreateSesion);
        validateCedula(validateCreateSesion);
        validateFechaInicio(validateCreateSesion);
        validateHoraInicio(validateCreateSesion);
        validateJwt(validateCreateSesion, cratedCreateSesion);

        if (!listResponse.isEmpty()) {

            return new ResponseBad(
                    ResponseCode.MULTIPLES_ERRORES.getMsjCode(),
                    DescriptionsResponse.MSJ_DESCRIPCION_ERROR_PARAMETRO.getDescription(),
                    listResponse
            );

        } else return null;
    }

    public static void validateJwt(ValidateDeleteSesion validateCreateSesion, CreateSesion cratedCreateSesion) {

        if (validateCreateSesion.getJwt() == null || validateCreateSesion.getJwt().isEmpty()) {

            listResponse.add(BodyResponse.error("jwt-obligatorio"));

        } else if (!JwtToken.getJWTToken(cratedCreateSesion).equals(validateCreateSesion.getJwt())) {

            listResponse.add(BodyResponse.error("jwt-incorrecto"));

        }
    }

    public static void validateHoraInicio(CreateSesion createSesion) {

        if (createSesion.getHoraInicio() == null || createSesion.getHoraInicio().isEmpty()) {

            listResponse.add(BodyResponse.error("hi-obligatorio"));

        } else if (contieneCaracterEspecial(createSesion.getHoraInicio(), ":")
                || contieneLetras(createSesion.getHoraInicio())
                || malRangoTiempo(createSesion.getHoraInicio())) {

            listResponse.add(BodyResponse.error("hi-incorrecto"));

        }

    }


    public static void validateFechaInicio(CreateSesion createSesion) {

        String fechaHoy = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));

        if (createSesion.getFechaInicio() == null || createSesion.getFechaInicio().isEmpty()) {

            listResponse.add(BodyResponse.error("fh-obligatorio"));

        } else if (contieneCaracterEspecial(createSesion.getFechaInicio(), "/")
                || !createSesion.getFechaInicio().equals(fechaHoy)
        ) {

            listResponse.add(BodyResponse.error("fh-incorrecto"));

        }

    }

    public static void validateCedula(CreateSesion createSesion) {

        if (createSesion.getCedula() == null || createSesion.getCedula().isEmpty()) {

            listResponse.add(BodyResponse.error("cc-obligatorio"));

        } else if (contieneCaracterEspecial(createSesion.getCedula(), "")) {

            listResponse.add(BodyResponse.error("cc-incorrecto"));

        } else if (cantidadDigitos(createSesion.getCedula())) {

            listResponse.add(BodyResponse.error("cc-incompleto"));

        }

    }

    public static void validateIp(CreateSesion createSesion) {

        if (createSesion.getIp() == null || createSesion.getIp().isEmpty()) {

            listResponse.add(BodyResponse.error("ip-obligatorio"));

        } else if (camposEnIP(createSesion.getIp()) < 4
                || camposEnIP(createSesion.getIp()) > 4
                || !rangosEnCampo(createSesion.getIp(), SPLITER_DOT, 0, 255)) {

            listResponse.add(BodyResponse.error("ip-incorrecto"));

        }

    }


}
