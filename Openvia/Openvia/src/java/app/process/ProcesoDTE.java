/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.process;

import app.common.Util;
import java.util.Date;

/**
 *
 * @author Miguelón
 */
public class ProcesoDTE {
    private static String horaIniLV = "0700";
    private static String horaFinLV = "0100";
    private static String horaIniSa = "0700";
    private static String horaFinSa = "2000";
    private static String horaIniDo = "0700";
    private static String horaFinDo = "2000";
    private static boolean activo = false;
    private static int iteraciones = 0;
    private static int doctos = 0;
    private static int frecuencia = 60;

    public static String getHoraIniLV() {
        return horaIniLV;
    }

    public static void setHoraIniLV(String aHoraIniLV) {
        horaIniLV = aHoraIniLV;
    }

    public static String getHoraFinLV() {
        return horaFinLV;
    }

    public static void setHoraFinLV(String aHoraFinLV) {
        horaFinLV = aHoraFinLV;
    }

    public static String getHoraIniSa() {
        return horaIniSa;
    }

    public static void setHoraIniSa(String aHoraIniSa) {
        horaIniSa = aHoraIniSa;
    }

    public static String getHoraFinSa() {
        return horaFinSa;
    }

    public static void setHoraFinSa(String aHoraFinSa) {
        horaFinSa = aHoraFinSa;
    }

    public static String getHoraIniDo() {
        return horaIniDo;
    }

    public static void setHoraIniDo(String aHoraIniDo) {
        horaIniDo = aHoraIniDo;
    }

    public static String getHoraFinDo() {
        return horaFinDo;
    }

    public static void setHoraFinDo(String aHoraFinDo) {
        horaFinDo = aHoraFinDo;
    }
    
    public static boolean isActivo() {
        return activo;
    }

    public static void setActivo(boolean aActivo) {
        activo = aActivo;
    }

    public static boolean isInRango() {
        boolean inRango = false;
        
        int hAct = Integer.parseInt(Util.dateToString(new Date(),"HHmm"));
        String diaSem = Util.dateToString(new Date(),"E").toUpperCase();
        
        int hMin;
        int hMax;
        
        // Determinar dia de la semana
        if (diaSem.startsWith("DOM") || diaSem.startsWith("SUN")) { // Domingo
            hMin = Integer.parseInt( getHoraIniDo() );
            hMax = Integer.parseInt( getHoraFinDo() );            
        } else if (diaSem.startsWith("S")) { // Sabado
            hMin = Integer.parseInt( getHoraIniSa() );
            hMax = Integer.parseInt( getHoraFinSa() );            
        } else { // Lu -Vi
            hMin = Integer.parseInt( getHoraIniLV() );
            hMax = Integer.parseInt( getHoraFinLV() );
        }
        
        if (hMin > hMax) {
            inRango = (hAct >= hMin) || (hAct <= hMax);
        } else {
            inRango = (hAct >= hMin) && (hAct <= hMax);
        }
        return inRango;
    }

    public static int getIteraciones() {
        return iteraciones;
    }

    public static void setIteraciones(int aIteraciones) {
        iteraciones = aIteraciones;
    }

    public static int getDoctos() {
        return doctos;
    }

    public static void setDoctos(int aDoctos) {
        doctos = aDoctos;
    }

    public static int getFrecuencia() {
        return frecuencia;
    }

    public static void setFrecuencia(int aFrecuencia) {
        frecuencia = aFrecuencia;
    }

}
