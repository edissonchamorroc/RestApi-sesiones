package com.springboot.apisesiones.utility;

import com.springboot.apisesiones.entity.CreateSesion;
import com.springboot.apisesiones.entity.SesionEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken {


    public static String secretKey = "proyectoSesiones";

    public static String getJWTToken(SesionEntity createSesion) {


        String token = Jwts
                .builder()
                .setId(createSesion.getCedula())
                .claim("ip", createSesion.getIp())
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes())
                .compact();

        return token;
    }
}
