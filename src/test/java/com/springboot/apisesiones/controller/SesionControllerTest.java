package com.springboot.apisesiones.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.enums.DescriptionsResponse;
import com.springboot.apisesiones.enums.ResponseCode;
import com.springboot.apisesiones.model.SesionEntity;
import com.springboot.apisesiones.service.SesionService;
import com.springboot.apisesiones.utility.JwtToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Test en controlador crear sesión")
    @Test
    void crearSesionTest() throws Exception {
        SesionEntity crearSesionBodyOk = new CreateSesion(
                1L,
                "192.12.122.235",
                "1234567106",
                "10/03/2023",
                "firefox",
                "23:49:58"
        );

        given(sesionService.createSesion(any(CreateSesion.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/sesiones/crear")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
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
}
