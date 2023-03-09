package com.springboot.apisesiones.repository;

import com.springboot.apisesiones.entity.CreateSesion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SesionRepositoryTest {

    @Autowired
    SesionRepository sesionRepository;

    CreateSesion sesion;

    @BeforeEach
    void setup() {

        sesion = new CreateSesion(
                1L,
                "192.12.122.214",
                "12345671",
                "09/03/2023",
                "firefox",
                "23:49:58"
        );

    }


    @DisplayName("Test para guardar sesión")
    @Test
    void testGuardarSesion() {

        CreateSesion sesionGuardada = sesionRepository.save(sesion);

        assertThat(sesionGuardada).isNotNull();
        assertThat(sesionGuardada.getCedula()).isEqualTo("12345671");

    }

    @DisplayName("Test para buscar sesión por Ip")
    @Test
    void testBuscarSesionIp() {

        sesionRepository.save(sesion);

        CreateSesion sesionDBIp = sesionRepository.findByIp(sesion.getIp());

        assertThat(sesionDBIp).isNotNull();
        assertThat(sesionDBIp.getIp()).isEqualTo(sesion.getIp());

    }

    @DisplayName("Test para buscar sesión por Cedula")
    @Test
    void testBuscarSesionCedula() {

        sesionRepository.save(sesion);

        CreateSesion sesionDBCedula = sesionRepository.findByCedula(sesion.getCedula());

        assertThat(sesionDBCedula).isNotNull();
        assertThat(sesionDBCedula.getCedula()).isEqualTo(sesion.getCedula());

    }

    @DisplayName("Test para eliminar sesión")
    @Test
    void testEliminarSesion() {

        sesionRepository.save(sesion);

        sesionRepository.delete(sesion);

        CreateSesion sesionDeleted = sesionRepository.findByCedula(sesion.getCedula());

        assertThat(sesionDeleted).isNull();

    }



}
