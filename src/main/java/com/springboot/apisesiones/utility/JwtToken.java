package com.springboot.apisesiones.utility;

import com.springboot.apisesiones.entity.Sesion;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken {


    public static String secretKey = "proyectoSesiones";

    public static String getJWTToken(Sesion sesion) {


        String token = Jwts
                .builder()
                .setId(sesion.getCedula())
                .claim("ip", sesion.getIp())
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes())
                .compact();

        return token;
    }
}
