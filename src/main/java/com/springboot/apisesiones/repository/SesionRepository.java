package com.springboot.apisesiones.repository;

import com.springboot.apisesiones.entity.CreateSesion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SesionRepository extends JpaRepository<CreateSesion,Long> {

     CreateSesion findByIp(String ip);

     CreateSesion findByCedula(String cedula);
}
