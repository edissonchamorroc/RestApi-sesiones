package com.springboot.apisesiones.controller;

import com.google.gson.Gson;
import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.enums.DescriptionsResponse;
import com.springboot.apisesiones.enums.ResponseCode;
import com.springboot.apisesiones.model.ResponseOk;
import com.springboot.apisesiones.utility.JwtToken;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SesionControllerRestTemplateTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Order(1)
    @Test
    void crearSesionTest() {
        CreateSesion crearSesionBodyOk = CreateSesion.builder()
                .cedula("1234567106")
                .fechaInicio("14/03/2023")
                .ip("192.12.122.235")
                .horaInicio("23:49:58")
                .navegador("firefox")
                .id(343L)
                .build();

        ResponseEntity<Object> response = testRestTemplate.postForEntity(
                "http://localhost:8080/api/sesiones/crear",
                crearSesionBodyOk,
                Object.class);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new Gson().toJson(
                        new ResponseOk(
                                ResponseCode.CREACION_EXITOSA.getMsjCode(),
                                DescriptionsResponse.MSJ_DESCRIPCION_CREACION_EXITOSA.getDescription(),
                                JwtToken.getJWTToken(crearSesionBodyOk.getCedula(), crearSesionBodyOk.getIp()),
                                DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription()

                        )
                )
                ,
                new Gson().toJson(response.getBody())
        );
    }


}
