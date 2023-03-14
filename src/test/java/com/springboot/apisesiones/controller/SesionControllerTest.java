package com.springboot.apisesiones.controller;

import com.google.gson.Gson;
import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.enums.DescriptionsResponse;
import com.springboot.apisesiones.enums.ResponseCode;
import com.springboot.apisesiones.model.ResponseOk;
import com.springboot.apisesiones.model.ValidateDeleteSesion;
import com.springboot.apisesiones.service.SesionService;
import com.springboot.apisesiones.utility.JwtToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

    private ValidateDeleteSesion validarEliminarSesionBodyOk;

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

        validarEliminarSesionBodyOk = ValidateDeleteSesion.builder()
                .cedula("1234567106")
                .fechaInicio("13/03/2023")
                .ip("192.12.122.235")
                .horaInicio("23:49:58")
                .navegador("firefox")
                .jwt(JwtToken.getJWTToken("1234567106","192.12.122.235"))
                .build();


    }

    @DisplayName("Test en controlador crear sesión con parámetros validos")
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
                        .content(new Gson().toJson(crearSesionBodyOk))

        );

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje", is(DescriptionsResponse.MSJ_DESCRIPCION_CREACION_EXITOSA.getDescription())))
                .andExpect(jsonPath("$.codigo", is(ResponseCode.CREACION_EXITOSA.getMsjCode())))
                .andExpect(jsonPath("$.estado", is(DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription())))
                .andExpect(jsonPath("$.token", is(JwtToken.getJWTToken(crearSesionBodyOk.getCedula(), crearSesionBodyOk.getIp()))))
        ;
    }


    @DisplayName("Test en controlador validar sesión con parámetros validos")
    @Test
    void validarSesionTest() throws Exception {


        when(sesionService.validateSesion(any(ValidateDeleteSesion.class))).thenReturn(
                new ResponseEntity<Object>(new ResponseOk(
                        ResponseCode.VALIDACION_EXITOSA.getMsjCode(),
                        DescriptionsResponse.MSJ_DESCRIPCION_VALIDACION_EXITOSA.getDescription(),
                        DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription())
                        , HttpStatus.OK
                )
        );

        ResultActions response = mockMvc.perform(
                post("/api/sesiones/validar")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(validarEliminarSesionBodyOk))

        );

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje", is(DescriptionsResponse.MSJ_DESCRIPCION_VALIDACION_EXITOSA.getDescription())))
                .andExpect(jsonPath("$.codigo", is(ResponseCode.VALIDACION_EXITOSA.getMsjCode())))
                .andExpect(jsonPath("$.estado", is(DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription())))

        ;
    }

    @DisplayName("Test en controlador eliminar sesión con parámetros validos")
    @Test
    void eliminarSesionTest() throws Exception {


        when(sesionService.deleteSesion(any(ValidateDeleteSesion.class))).thenReturn(
                new ResponseEntity<Object>(new ResponseOk(
                        ResponseCode.ELIMINACION_EXITOSA.getMsjCode(),
                        DescriptionsResponse.MSJ_DESCRIPCION_ELIMINACION_EXITOSA.getDescription(),
                        DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription())
                        , HttpStatus.OK
                )
        );

        ResultActions response = mockMvc.perform(
                delete("/api/sesiones/eliminar")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(validarEliminarSesionBodyOk))

        );

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje", is(DescriptionsResponse.MSJ_DESCRIPCION_ELIMINACION_EXITOSA.getDescription())))
                .andExpect(jsonPath("$.codigo", is(ResponseCode.ELIMINACION_EXITOSA.getMsjCode())))
                .andExpect(jsonPath("$.estado", is(DescriptionsResponse.MSJ_ESTADO_EXITOSO.getDescription())))

        ;
    }
}
