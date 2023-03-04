package com.springboot.apisesiones.utility;

import com.springboot.apisesiones.entity.Sesion;
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


    public static Map<String, Object> parameters(Sesion sesion) {

        if (validateIp(sesion)
                || validateCedula(sesion)
                || validateFechaInicio(sesion)
                || validateHoraInicio(sesion)) {

            Map<String, Object> response = new HashMap<>();
            response.put(ParametersResponse.MENSAJE.getParameter(), ResponseCode.IP_OBLIGATORIO.getMsjCode());
            response.put(ParametersResponse.DESCRIPCION.getParameter(), DescriptionsResponse.MSJ_DESCRIPCION_ERROR_PARAMETRO.getDescription());
            response.put(ParametersResponse.DETALLES.getParameter(), listResponse);

            return response;

        }

        return null;
    }

    public static boolean validateHoraInicio(Sesion sesion) {

        if (sesion.getHoraInicio() == null) {

            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("hi-obligatorio"));

            return true;

        } else if (contieneCaracterEspecial(sesion.getHoraInicio(), ":")
                || contieneLetras(sesion.getHoraInicio())
                || malRangoTiempo(sesion.getHoraInicio())) {


            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("hi-incorrecto"));

            return true;

        } else return false;

    }


    public static boolean validateFechaInicio(Sesion sesion) {

        String fechaHoy = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));

        if (sesion.getFechaInicio() == null) {

            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("fh-obligatorio"));

            return true;

        } else if (contieneCaracterEspecial(sesion.getFechaInicio(), "/")
                || !sesion.getFechaInicio().equals(fechaHoy)
        ) {


            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("fh-incorrecto"));

            return true;

        } else return false;


    }

    public static boolean validateCedula(Sesion sesion) {

        if (sesion.getCedula() == null) {

            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("cc-obligatorio"));

            return true;

        } else if (contieneCaracterEspecial(sesion.getCedula(), "")) {


            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("cc-incorrecto"));

            return true;

        } else if (cantidadDigitos(sesion.getCedula())) {

            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("cc-incompleto"));

            return true;

        } else return false;

    }

    public static boolean validateIp(Sesion sesion) {

        if (sesion.getIp() == null) {

            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("ip-obligatorio"));

            return true;

        } else if (camposEnIP(sesion.getIp()) < 4 || camposEnIP(sesion.getIp()) > 4
                || !rangosEnCampo(sesion.getIp(), SPLITER_DOT, 0, 255)) {


            listResponse = new ArrayList<>();
            listResponse.add(BodyResponse.error("ip-incorrecto"));

            return true;

        } else return false;

    }


}
