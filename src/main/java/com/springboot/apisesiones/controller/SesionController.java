package com.springboot.apisesiones.controller;

import com.springboot.apisesiones.entity.Sesion;
import com.springboot.apisesiones.service.SesionService;
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

    @PostMapping(path = "/crear",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postEmployee(@RequestBody Sesion newSesion) {

        return new ResponseEntity<Object>(this.sesionService.createSesion(newSesion), HttpStatus.OK);
    }
}
