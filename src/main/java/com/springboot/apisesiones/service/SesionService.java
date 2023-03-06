package com.springboot.apisesiones.service;

import com.springboot.apisesiones.entity.Sesion;
import com.springboot.apisesiones.repository.SesionRepository;
import com.springboot.apisesiones.utility.BodyResponse;
import com.springboot.apisesiones.utility.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    public Map<String, Object> createSesion(Sesion newSesion) {

        if (Validate.parametersCreation(newSesion) == null) {

            sesionRepository.save(newSesion);

            return BodyResponse.correcta("creacion", newSesion);

        } else return Validate.parametersCreation(newSesion);

    }

    public Map<String, Object> validateSesion(Sesion sesion) {

        if (Validate.parametersValidation(sesion) == null &&
                sesionRepository.findByCedula(sesion.getCedula()) != null) {

            return BodyResponse.correcta("validacion", sesion);

        } else return Validate.parametersValidation(sesion);
    }

}
