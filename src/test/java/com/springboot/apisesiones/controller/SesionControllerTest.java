package com.springboot.apisesiones.controller;

import com.google.gson.Gson;
import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.enums.DescriptionsResponse;
import com.springboot.apisesiones.enums.ResponseCode;
import com.springboot.apisesiones.model.ResponseOk;
import com.springboot.apisesiones.model.SesionEntity;
import com.springboot.apisesiones.service.SesionService;
import com.springboot.apisesiones.utility.JwtToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SesionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SesionService sesionService;

    private CreateSesion crearSesionBodyOk;

    private String bodyRequest;

    @BeforeEach
    void setup() {
        // Antes de ejecutar las pruebas, cambiar fechaInicio con la fecha actual
        crearSesionBodyOk = CreateSesion.builder()
                .cedula("1234567106")
                .fechaInicio("13/03/2023")
                .ip("192.12.122.235")
                .horaInicio("23:49:58")
                .navegador("firefox")
                .id(343L)
                .build();


        bodyRequest = new Gson().toJson(crearSesionBodyOk);

    }

    @DisplayName("Test en controlador crear sesión")
    @Test
    void crearSesionTest() throws Exception {


        when(sesionService.createSesion(any(CreateSesion.class))).thenReturn(
                new ResponseEntity<Object>(new ResponseOk(
                        ResponseCode.CREACION_EXITOSA.getMsjCode(),
                        DescriptionsResponse.MSJ_DESCRIPCION_CREACION_EXITOSA.getDescription(),
                        JwtToken.getJWTToken(crearSesionBodyOk.getCedula(), crearSesionBodyOk.getIp()),
                        DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription())
                        , HttpStatus.OK
                )
        );

        ResultActions response = mockMvc.perform(
                post("/api/sesiones/crear")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequest)

        );

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje", is(DescriptionsResponse.MSJ_DESCRIPCION_CREACION_EXITOSA.getDescription())))
                .andExpect(jsonPath("$.codigo", is(ResponseCode.CREACION_EXITOSA.getMsjCode())))
                .andExpect(jsonPath("$.estado", is(DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription())))
                .andExpect(jsonPath("$.token", is(JwtToken.getJWTToken(crearSesionBodyOk.getCedula(), crearSesionBodyOk.getIp()))))
        ;
    }
}
