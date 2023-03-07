package com.springboot.apisesiones.service;

import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.entity.SesionEntity;
import com.springboot.apisesiones.entity.ValidateDeleteSesion;
import com.springboot.apisesiones.enums.ParametersResponse;
import com.springboot.apisesiones.model.Response;
import com.springboot.apisesiones.repository.SesionRepository;
import com.springboot.apisesiones.utility.BodyResponse;
import com.springboot.apisesiones.utility.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    public Response createSesion(CreateSesion newSesion) {

        Response response = Validate.parametersCreation(newSesion);//validacion de parámetro en body enviado por petición

        eliminarSesionDuplicada(newSesion);//Elimina sesión si está duplicada

        if (response == null) {

            sesionRepository.save(newSesion);

            return BodyResponse.correcta(ParametersResponse.CREACION.getParameter(), newSesion);

        } else return response;

    }

    public Response validateSesion(ValidateDeleteSesion sesion) {

        SesionEntity sesionEnBD = sesionRepository.findByCedula(sesion.getCedula());

        Response response = Validate.parametersValidation(
                sesion,
                (CreateSesion) sesionEnBD
        );//validacion de parámetro en body enviado por petición


        if (response == null) {

            if (sesionEnBD != null &&
                    sesionEnBD.getCedula().equals(sesion.getCedula()) &&
                    sesionEnBD.getIp().equals(sesion.getIp())
            ) {

                return BodyResponse.correcta("validacion", sesionEnBD);
            }

            return BodyResponse.correcta("no-existe", sesionEnBD);

        } else return response;
    }

    public Response deleteSesion(ValidateDeleteSesion sesion) {

        SesionEntity sesionEnBD = sesionRepository.findByCedula(sesion.getCedula());

        Response response = Validate.parametersValidation(
                sesion,
                (CreateSesion) sesionEnBD
        );//validacion de parámetro en body enviado por petición


        if (response == null) {

            if (sesionEnBD != null &&
                    sesionEnBD.getCedula().equals(sesion.getCedula()) &&
                    sesionEnBD.getIp().equals(sesion.getIp())
            ) {
                this.sesionRepository.delete(sesion);
                return BodyResponse.correcta("eliminacion", sesionEnBD);
            }

            return BodyResponse.correcta("no-existe", sesionEnBD);

        } else return response;
    }

    public void eliminarSesionDuplicada(CreateSesion sesion) {

        SesionEntity sesionDB = sesionRepository.findByCedula(sesion.getCedula());

        if (sesionDB != null) {

            this.sesionRepository.delete((CreateSesion) sesionDB);

        }

    }


}
