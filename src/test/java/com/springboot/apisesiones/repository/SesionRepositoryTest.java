package com.springboot.apisesiones.repository;

import com.springboot.apisesiones.entity.CreateSesion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SesionRepositoryTest {

    @Autowired
    SesionRepository sesionRepository;

    CreateSesion sesion ;
    @BeforeEach
    void setUp() {

         sesion = new CreateSesion(
                1L,
                "192.12.122.235",
                "1234567106",
                "08/03/2023",
                "firefox",
                "23:49:58"
        );


    }

    @Test
    void testGuardarSesion() {

        CreateSesion sesionGuardada = sesionRepository.save(sesion);

        assertThat(sesionGuardada).isNotNull();
        assertThat(sesionGuardada.getCedula()).isEqualTo("1234567106");

    }

}
