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

        Response response = Validate.parametersValidation(
                sesion
        );//validacion de parámetro en body enviado por petición

        if (response == null) {

            if (validarSesionEnBD(sesion) &&
                    sesionRepository.findByCedula(sesion.getCedula()).getIp().equals(sesion.getIp())) {

                return new ResponseEntity<Object>(BodyResponse.correcta("validacion", null), HttpStatus.OK);
            }

            return new ResponseEntity<Object>(BodyResponse.correcta("no-existe", null), HttpStatus.ACCEPTED);

        } else return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Object> deleteSesion(ValidateDeleteSesion sesion) {

        if (validarSesionEnBD(sesion)) {

            Response response = Validate.parametersValidation(
                    sesion
            );
            //validacion de parámetro en body enviado por petición


            if (response == null) {

                this.sesionRepository.delete(this.sesionRepository.findByCedula(sesion.getCedula()));

                return new ResponseEntity<Object>(BodyResponse.correcta("eliminacion", null), HttpStatus.OK);

            } else return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<Object>(BodyResponse.correcta("no-existe", null), HttpStatus.ACCEPTED);

    }

    public void eliminarSesionDuplicada(CreateSesion sesion) {

        SesionEntity sesionDB = sesionRepository.findByCedula(sesion.getCedula());

        if (sesionDB != null) {

            this.sesionRepository.delete((CreateSesion) sesionDB);

        }

    }

    public boolean validarSesionEnBD(ValidateDeleteSesion sesion) {

        return (this.sesionRepository.findByCedula(sesion.getCedula()) != null) ? true : false;
    }


}
