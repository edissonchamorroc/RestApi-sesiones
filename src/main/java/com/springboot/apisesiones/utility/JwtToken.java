package com.springboot.apisesiones.utility;

import com.springboot.apisesiones.model.SesionEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken {


    public static String secretKey = "proyectoSesiones";

    public static String getJWTToken(String cedula, String ip) {


        String token = Jwts
                .builder()
                .setId(cedula)
                .claim("ip", ip)
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes())
                .compact();

        return token;
    }
}
