package com.springboot.apisesiones.utility;

import com.springboot.apisesiones.entity.CreateSesion;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken {


    public static String secretKey = "proyectoSesiones";

    public static String getJWTToken(CreateSesion createSesion) {


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
