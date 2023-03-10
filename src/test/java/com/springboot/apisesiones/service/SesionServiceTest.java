package com.springboot.apisesiones.service;

import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.enums.DescriptionsResponse;
import com.springboot.apisesiones.model.ResponseBad;
import com.springboot.apisesiones.model.ResponseOk;
import com.springboot.apisesiones.model.SesionEntity;
import com.springboot.apisesiones.model.ValidateDeleteSesion;
import com.springboot.apisesiones.repository.SesionRepository;
import com.springboot.apisesiones.utility.JwtToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SesionServiceTest {

    @Mock
    private SesionRepository sesionRepository;

    @InjectMocks
    private SesionService sesionService;

    private SesionEntity crearSesionBodyOk, validarEliminarSesionBodyOk, crearSesionBodyBad, validarEliminarSesionBodyBad;


    @BeforeEach
    void setup() {
        // Antes de ejecutar las pruebas, cambiar fechaInicio con la fecha actual
        crearSesionBodyOk = new CreateSesion(
                1L,
                "192.12.122.235",
                "1234567106",
                "10/03/2023",
                "firefox",
                "23:49:58"
        );

        validarEliminarSesionBodyOk = new ValidateDeleteSesion(
                1L,
                "192.12.122.235",
                "1234567106",
                "10/03/2023",
                "firefox",
                "23:49:58",
                JwtToken.getJWTToken(crearSesionBodyOk.getCedula(), crearSesionBodyOk.getIp())
        );

        // Se establece mal el valor fechaInicio en los siguientes objetos, por temas de verficación en pruebas
        crearSesionBodyBad = new CreateSesion(
                1L,
                "192.12.122.235",
                "1234567106",
                "08/03/2023",
                "firefox",
                "23:49:58"
        );

        validarEliminarSesionBodyBad= new ValidateDeleteSesion(
                1L,
                "192.12.122.235",
                "1234567106",
                "08/03/2023",
                "firefox",
                "23:49:58",
                JwtToken.getJWTToken(crearSesionBodyOk.getCedula(), crearSesionBodyOk.getIp())
        );
    }

    @DisplayName("Test para probar servicio crear sesión con parámetros válidos")
    @Test
    void crearSesionTest() {

        given(sesionRepository.save((CreateSesion) crearSesionBodyOk))
                .willReturn((CreateSesion) crearSesionBodyOk);

        ResponseEntity<Object> crearSesion = sesionService.createSesion((CreateSesion) crearSesionBodyOk);
        ResponseOk bodyResponseCrearSesion = (ResponseOk) crearSesion.getBody();

        assertThat(crearSesion.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(bodyResponseCrearSesion.token)
                .isEqualTo(JwtToken.getJWTToken(crearSesionBodyOk.getCedula(), crearSesionBodyOk.getIp()));

    }

    @DisplayName("Test para probar servicio crear sesión con parámetros no válidos")
    @Test
    void crearSesionParametroNoValidoTest() {

        ResponseEntity<Object> crearSesion = sesionService.createSesion((CreateSesion) crearSesionBodyBad);
        ResponseBad bodyResponseCrearSesion = (ResponseBad) crearSesion.getBody();

        assertThat(crearSesion.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(bodyResponseCrearSesion.getMensaje())
                .isEqualTo(DescriptionsResponse.MSJ_DESCRIPCION_ERROR_PARAMETRO.getDescription());

    }

    @DisplayName("Test para probar servicio validar sesión con parámetros válidos")
    @Test
    void validarSesionTest() {

        given(sesionRepository.findByCedula(validarEliminarSesionBodyOk.getCedula()))
                .willReturn((CreateSesion) crearSesionBodyOk);

        ResponseEntity<Object> validarSesionResponse = sesionService.validateSesion((ValidateDeleteSesion) validarEliminarSesionBodyOk);
        ResponseOk bodyResponseValidarSesion = (ResponseOk) validarSesionResponse.getBody();

        assertThat(validarSesionResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(bodyResponseValidarSesion.mensaje)
                .isEqualTo(DescriptionsResponse.MSJ_DESCRIPCION_VALIDACION_EXITOSA.getDescription());
    }

    @DisplayName("Test para probar servicio validar sesión con parámetros no válidos")
    @Test
    void validarSesionParametroNoValidoTest() {

        ResponseEntity<Object> validarSesionResponse = sesionService.validateSesion((ValidateDeleteSesion) validarEliminarSesionBodyBad);
        ResponseBad bodyResponseValidarSesion = (ResponseBad) validarSesionResponse.getBody();

        assertThat(validarSesionResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(bodyResponseValidarSesion.getMensaje())
                .isEqualTo(DescriptionsResponse.MSJ_DESCRIPCION_ERROR_PARAMETRO.getDescription());
    }

    @DisplayName("Test para probar servicio eliminar sesión con parámetros válidos")
    @Test
    void eliminarSesionTest() {

        given(sesionRepository.findByCedula(validarEliminarSesionBodyOk.getCedula()))
                .willReturn((CreateSesion) crearSesionBodyOk);

        ResponseEntity<Object> eliminarSesionResponse = sesionService.deleteSesion((ValidateDeleteSesion) validarEliminarSesionBodyOk);
        ResponseOk bodyResponseEliminarSesion = (ResponseOk) eliminarSesionResponse.getBody();

        assertThat(eliminarSesionResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(bodyResponseEliminarSesion.mensaje)
                .isEqualTo(DescriptionsResponse.MSJ_DESCRIPCION_ELIMINACION_EXITOSA.getDescription());

    }

    @DisplayName("Test para probar servicio eliminar sesión con parámetros no válidos")
    @Test
    void eliminarSesionParametroNoValidoTest() {

        given(sesionRepository.findByCedula(validarEliminarSesionBodyBad.getCedula()))
                .willReturn((CreateSesion) crearSesionBodyBad);

        ResponseEntity<Object> eliminarSesionResponse = sesionService.deleteSesion((ValidateDeleteSesion) validarEliminarSesionBodyBad);
        ResponseBad bodyResponseEliminarSesion = (ResponseBad) eliminarSesionResponse.getBody();

        assertThat(eliminarSesionResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(bodyResponseEliminarSesion.getMensaje())
                .isEqualTo(DescriptionsResponse.MSJ_DESCRIPCION_ERROR_PARAMETRO.getDescription());

    }


}
