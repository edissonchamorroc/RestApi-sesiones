package com.springboot.apisesiones.repository;

import com.springboot.apisesiones.entity.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SesionRepository extends JpaRepository<Sesion,Long> {

     Sesion findByIp(String ip);

     Sesion findByCedula(String cedula);
}
