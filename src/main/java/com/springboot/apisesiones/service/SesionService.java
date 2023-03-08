package com.springboot.apisesiones.service;

import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.model.SesionEntity;
import com.springboot.apisesiones.model.ValidateDeleteSesion;
import com.springboot.apisesiones.enums.ParametersResponse;
import com.springboot.apisesiones.model.Response;
import com.springboot.apisesiones.repository.SesionRepository;
import com.springboot.apisesiones.utility.BodyResponse;
import com.springboot.apisesiones.utility.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    public ResponseEntity<Object> createSesion(CreateSesion newSesion) {

        Response response = Validate.parametersCreation(newSesion);//validacion de parámetro en body enviado por petición

        eliminarSesionDuplicada(newSesion);//Elimina sesión si está duplicada

        if (response == null) {

            sesionRepository.save(newSesion);

            return new ResponseEntity<Object>(BodyResponse.correcta(ParametersResponse.CREACION.getParameter(), newSesion), HttpStatus.OK);

        } else return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Object> validateSesion(ValidateDeleteSesion sesion) {

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

                return new ResponseEntity<Object>(BodyResponse.correcta("validacion", sesionEnBD), HttpStatus.OK);
            }

            return new ResponseEntity<Object>(BodyResponse.correcta("no-existe", sesionEnBD), HttpStatus.ACCEPTED);

        } else return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Object> deleteSesion(ValidateDeleteSesion sesion) {

        SesionEntity sesionEnBD = sesionRepository.findByCedula(sesion.getCedula());

        if (sesionEnBD != null) {

            Response response = Validate.parametersValidation(
                    sesion,
                    (CreateSesion) sesionEnBD
            );
            //validacion de parámetro en body enviado por petición


            if (response == null) {

                this.sesionRepository.delete((CreateSesion) sesionEnBD);

                return new ResponseEntity<Object>(BodyResponse.correcta("eliminacion", sesionEnBD), HttpStatus.OK);

            } else return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<Object>(BodyResponse.correcta("no-existe", sesionEnBD), HttpStatus.ACCEPTED);

    }

    public void eliminarSesionDuplicada(CreateSesion sesion) {

        SesionEntity sesionDB = sesionRepository.findByCedula(sesion.getCedula());

        if (sesionDB != null) {

            this.sesionRepository.delete((CreateSesion) sesionDB);

        }

    }


}
