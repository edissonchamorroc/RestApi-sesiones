package com.springboot.apisesiones.controller;

import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.model.ValidateDeleteSesion;
import com.springboot.apisesiones.model.ResponseBad;
import com.springboot.apisesiones.model.ResponseOk;
import com.springboot.apisesiones.service.SesionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sesiones")
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @ApiOperation(value = "/api/sesiones/crear"
            , notes = "Servicio para consumir el método de crear sesión, ingresando los parámetros solicitados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La sesion ha sido creada exitosamente", response = ResponseOk.class),
            @ApiResponse(code = 400, message = "Error en parámetro(s)", response = ResponseBad.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping(path = "/crear", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> crearSesion(@RequestBody CreateSesion newCreateSesion) {

        return this.sesionService.createSesion(newCreateSesion);
    }

    @ApiOperation(value = "/api/sesiones/validar"
            , notes = "Servicio para consumir el método de validar sesión, ingresando los parámetros solicitados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La sesion ha sido validada exitosamente", response = ResponseOk.class),
            @ApiResponse(code = 202, message = "La sesion no ha sido creada", response = ResponseOk.class),
            @ApiResponse(code = 400, message = "Error en parámetro(s)", response = ResponseBad.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping(path = "/validar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> validarSesion(@RequestBody ValidateDeleteSesion validateSesion) {

        return this.sesionService.validateSesion(validateSesion);
    }

    @ApiOperation(value = "/api/sesiones/eliminar"
            , notes = "Servicio para consumir el método de validar sesión, ingresando los parámetros solicitados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La sesion ha sido eliminada exitosamente", response = ResponseOk.class),
            @ApiResponse(code = 202, message = "La sesion no ha sido creada", response = ResponseOk.class),
            @ApiResponse(code = 400, message = "Error en parámetro(s)", response = ResponseBad.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @DeleteMapping(path = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminarSesion(@RequestBody ValidateDeleteSesion validateSesion) {

        return this.sesionService.deleteSesion(validateSesion);
    }
}
