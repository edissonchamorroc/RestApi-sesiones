package com.springboot.apisesiones.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logica {

    private static String DIGITS = "[0-9]";
    private static final String SPLITER_DOT = "\\.";
    private static final String SPLITER_DOUBLEDOT = ":";
    private static String SPECIAL_CHARACTERS = "(?=.*[@#$%*/|,:;{}?<>!()^&.+=_-])";
    private static String CHARACTERS = "[a-zA-Z]";

    public static String delimiter(int min, int max) {

        if (min <= 0) return "{," + max + "}";
        else if (max <= 0) return "{" + min + ",}";
        else return "{" + min + "," + max + "}";

    }

    public static int camposEnIP(String ip) {

        String[] ipSplit = ip.split(SPLITER_DOT);

        return ipSplit.length;

    }

    public static boolean rangosEnCampo(String data, String spliter, int min, int max) {

        boolean inRange = false;

        String[] ipSplit = data.split(spliter);

        for (String bitIp : ipSplit) {

            if (conversionStrintToInt(bitIp) >= min && conversionStrintToInt(bitIp) <= max) inRange = true;

            else {
                inRange = false;
                break;
            }

        }

        return inRange;
    }

    public static int conversionStrintToInt(String numberString) {

        return Integer.parseInt(numberString);
    }

    public static boolean cantidadDigitos(String number) {

        return !number.matches(DIGITS.concat(delimiter(5, 15)));

    }

    public static boolean contieneCaracterEspecial(String data, String exception) {

        Pattern pattern = Pattern.compile(SPECIAL_CHARACTERS.replace(exception, ""));

        Matcher matcher = pattern.matcher(data);

        return matcher.find();
    }

    public static boolean malRangoTiempo(String time) {

        String[] times = time.split(SPLITER_DOUBLEDOT);

        if (conversionStrintToInt(times[0]) >=0 && conversionStrintToInt(times[0])<=23 &&
                conversionStrintToInt(times[1]) >=0 && conversionStrintToInt(times[1])<=59 &&
                 conversionStrintToInt(times[2]) >=0 && conversionStrintToInt(times[2])<=59){

            return false;

        }else return true;

    }

    public static boolean contieneLetras(String data){

        Pattern pattern = Pattern.compile(CHARACTERS);

        Matcher matcher = pattern.matcher(data);

        return matcher.find();
    }
}
