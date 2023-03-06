package com.springboot.apisesiones.controller;

import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.model.ResponseCreateBad;
import com.springboot.apisesiones.model.ResponseCreateOk;
import com.springboot.apisesiones.service.SesionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/sesiones")
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @ApiOperation(value = "/api/sesiones/crear"
            , notes = "Servicio para consumir el método de crear sesión, ingresando los parámetros solicitados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se crea sesión exitosamente", response = ResponseCreateOk.class),
            @ApiResponse(code = 400, message = "Bad Request, la sesión no pudo ser creada", response = ResponseCreateBad.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping(path = "/crear", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> crearSesion(@RequestBody CreateSesion newCreateSesion) {

        return new ResponseEntity<Object>(this.sesionService.createSesion(newCreateSesion), HttpStatus.OK);
    }
}
