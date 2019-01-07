/*
 * Utils.java
 *
 * Created on 21 de diciembre de 2006, 04:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package app.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
//import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 *
 * @author Miguelon
 */
public class Util {
    private static final String SPACES = "                                                                                                         ";
//    private static final String GATOS = "################";
    public static final int LEFT = -1;
    public static final int CENTER = 0;
    public static final int RIGTH = 1;
    
    /** Creates a new instance of Utils */
    public Util() {
    }
    
    public static String makeProper(String theString) {
        if (theString == null) {
            return null;
        }
        java.io.StringReader in = new java.io.StringReader(theString.toLowerCase());
        boolean precededBySpace = true;
        StringBuffer properCase = new StringBuffer();
        while(true) {
            int i;
            try {
                i = in.read();
                if (i == -1)  break;
                char c = (char)i;
                if (c == ' ' || c == '"' || c == '(' || c == '.' || c == '/' || c == '\\' || c == ',') {
                    properCase.append(c);
                    precededBySpace = true;
                } else {
                    if (precededBySpace) {
                        properCase.append(Character.toUpperCase(c));
                    } else {
                        properCase.append(c);
                    }
                    precededBySpace = false;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return theString;    
            }
        }
        return properCase.toString();    
    }
    
    public static String dateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date stringToDate(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static int nroDias ( String fechaDesde, String fechaHasta) {
        Date fecDesde = stringToDate(fechaDesde, "yyyyMMdd");
        Date fecHasta = stringToDate(fechaHasta, "yyyyMMdd");
        return (int) (((fecHasta.getTime() - fecDesde.getTime()) / (1000 * 60 * 60 * 24)));
        
    }
    
    
    public static void copyFile(java.io.File source, java.io.File destination ) {
        try {
                java.io.FileInputStream inStream=new java.io.FileInputStream(source);
                java.io.FileOutputStream outStream=new java.io.FileOutputStream(destination);

                int len;
                byte[] buf=new byte[2048];

                while ((len=inStream.read(buf))!=-1) {
                        outStream.write(buf,0,len);
                }
                inStream.close();
                outStream.close();
        } catch (Exception e) {
        }
    }

//    public static void copyDirectory(File sourceDir, File destDir) {
//        if(!sourceDir.exists()){
//            return;
//        }
//        if(!destDir.exists()){
//            destDir.mkdir();
//        }
//        File[] children = sourceDir.listFiles();
//        for(File sourceChild : children){
//            String name = sourceChild.getName();
//            File destChild = new File(destDir, name);
//            if(sourceChild.isDirectory()){
//                if (!sourceChild.getAbsolutePath().equals(destDir.getAbsolutePath()))
//                    copyDir(sourceChild, destChild, destDir);
//            }
//            else {
//                copyFile(sourceChild, destChild);
//            }
//        }
//    }
 
//    private static void copyDir(File sourceDir, File destDir, File noCopiar) {
//        if(!sourceDir.exists()){
//            return;
//        }
//        if(!destDir.exists()){
//            destDir.mkdir();
//        }
//        File[] children = sourceDir.listFiles();
//        for(File sourceChild : children){
//            String name = sourceChild.getName();
//            File destChild = new File(destDir, name);
//            if(sourceChild.isDirectory()){
//                if (!sourceChild.getAbsolutePath().equals(noCopiar.getAbsolutePath()))
//                    copyDir(sourceChild, destChild, noCopiar);
//            }
//            else {
//                copyFile(sourceChild, destChild);
//            }
//        }
//    }    
    
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
    
        // The directory is now empty so delete it
        return dir.delete();
    }
    
    public static String getFileExt(String archivo) {
        String fileExt = "";
        if ( archivo.indexOf(".") != -1)
            fileExt = archivo.substring( archivo.lastIndexOf("."));
        return fileExt;
    }
    
    public static String espacios( int n) {
        if (n == 0)
            return "";
        if ( n <= 100 )
            return SPACES.substring(0,n);
        else {
            int i = n;
            String ret = "";
            while (i > 100) {
                ret += SPACES.substring(0,100);
                i = i-100;
            }
            ret += SPACES.substring(0,i);
            return ret;
        }
    }
    
    public static String formatDecimal( BigDecimal number, int dec, int largo ) {
        String ret = "0";
        
        if (number == null)
            return espacios(largo);
        
        //redondear
        number = number.setScale(dec, BigDecimal.ROUND_HALF_UP);
        
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
        nf.setMinimumFractionDigits(dec);
        nf.setMaximumFractionDigits(dec);
        ret = nf.format(number);
        
//        DecimalFormat myFormatter = new DecimalFormat("#." + GATOS.substring(0,dec));
//        ret = myFormatter.format(number);
        
        ret = ret.replaceAll("[.]", "");
        ret = ret.replaceAll("[,]", "");
        if (largo != 0) {
            ret = espacios(largo - ret.length()) + ret;
        }
        return ret;
    }
        
    public static String formatDecimalConComa( BigDecimal number, int dec, int largo ) {
        String ret = "0";
        
        if (number == null)
            return espacios(largo);
        
        //redondear
        number = number.setScale(dec, BigDecimal.ROUND_HALF_UP);
        
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
        nf.setMinimumFractionDigits(dec);
        nf.setMaximumFractionDigits(dec);
        ret = nf.format(number);
        
//        DecimalFormat myFormatter = new DecimalFormat("#." + GATOS.substring(0,dec));
//        ret = myFormatter.format(number);
        
//        ret = ret.replaceAll("[,]", "");
//        ret = ret.replaceAll("[.]", ",");
        if (largo != 0) {
            ret = espacios(largo - ret.length()) + ret;
        }
        return ret;
    }

    public static String justifyString(String string, int largo, int justify) {
        String ret = "";

        if (string == null)
            return espacios(largo);
        
       string = string.trim();
       if (largo <= string.length() ) {
           ret = string.substring(0, largo);
       } else {
           if (justify == LEFT) {
               ret = string + espacios(largo - string.length());
           } else if (justify == RIGTH) {
               ret = espacios(largo - string.length()) + string;
           } else {
               int mid = new BigDecimal(""+(largo - string.length())/2).intValue();
               ret = espacios(mid) + string + espacios(largo-string.length()-mid);
           }
       }
            
        return ret;
    }
    
    public static String formatDate( BigDecimal date) {
        return dateToString(new Date(date.intValue()), "yyyyMMdd");
    }
    
    public static String julianToDate( Integer julian ) {
        
        if (julian == null || julian.intValue() == 0)
            return espacios(8);
        
        String fecha = julian.toString();
        int largo = fecha.length();
        
        String dia = fecha.substring(largo-3, largo);
        String ano = fecha.substring(0, largo-3);
         
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, new Integer(ano).intValue()+1900);
        cal.set(Calendar.DAY_OF_YEAR, new Integer(dia).intValue());
        
        
        return dateToString(cal.getTime(), "yyyyMMdd");
    }
    
    public static String julianToDateFormat( Integer julian ) {
        
        if (julian == null || julian.intValue() == 0)
            return espacios(8);
        
        String fecha = julian.toString();
        int largo = fecha.length();
        
        String dia = fecha.substring(largo-3, largo);
        String ano = fecha.substring(0, largo-3);
         
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, new Integer(ano).intValue()+1900);
        cal.set(Calendar.DAY_OF_YEAR, new Integer(dia).intValue());
        
        
        return dateToString(cal.getTime(), "dd-MM-yyyy");
    }

    public static String julianToDateAprox( Integer julian ) {
        
        if (julian == null || julian.intValue() == 0)
            return espacios(8);
        
        String fecha = julian.toString();
        int largo = fecha.length();
        
        String dia = fecha.substring(largo-3, largo);
        String ano = fecha.substring(0, largo-3);
         
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, new Integer(ano).intValue()+1900);
        cal.set(Calendar.DAY_OF_YEAR, new Integer(dia).intValue());
        
        int diaM = cal.get(Calendar.DAY_OF_MONTH);
        int maxDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if ( diaM <= 15 ) { // || diaM > 30
            cal.set(Calendar.DAY_OF_MONTH, 15);
//            if (diaM > 30)
//                if (cal.get(Calendar.MONTH) < 11 )
//                    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
//                else
//                    cal.set(Calendar.MONTH, 0);
//                    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
        } else {
//            if (maxDia >= 30)
//                cal.set(Calendar.DAY_OF_MONTH, 30);
//            else
                cal.set(Calendar.DAY_OF_MONTH, maxDia);
        }
        return dateToString(cal.getTime(), "yyyyMMdd");
    }
    
    public static Integer dateToJulian( String date ) {
        
        if (date == null || date.trim().equals(""))
            return new Integer(0);
        
        String ano = date.substring(0, 4);
        String mes = date.substring(4, 6);
        String dia = date.substring(6, 8);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, new Integer(ano).intValue());
        cal.set(Calendar.MONTH, new Integer(mes).intValue()-1);
        cal.set(Calendar.DAY_OF_MONTH, new Integer(dia).intValue());

        return new Integer( ((cal.get(Calendar.YEAR) - 1900)*1000) + cal.get(Calendar.DAY_OF_YEAR) );
    }
    
    public static boolean esNumero(String string) {
        
        if ( string == null || string.trim().equals("") ) {
            return false;
        }
        if (string.trim().matches("-?[0-9][0-9]*")) {
            return true;
        }
        return false;
//        try{
//            Integer.parseInt(string);
//            return true;
//        } catch (Exception e){
//            return false;
//        }
        
    }
    
    public static String getRut(String rut) {
        String ret = "";
        
        if (rut == null)
            return "";
        ret = rut.trim();
        return ret.substring(0, ret.length()-2);
    }

    public static String getDVRut(String rut) {
        String ret = "";
        
        if (rut == null)
            return "";
        ret = rut.trim();
        return ret.substring(ret.length()-1,ret.length());
    }
    
   public static void delay(long sec) {
       try {
           Thread.sleep(sec*1000); // do nothing for (miliSec) miliseconds
       }
       catch(InterruptedException e) {
       }
   }    
   
   public static String formatHora(String hora) {
       String ret = "";
       
       if (hora != null && hora.length() > 4) {
           hora = justifyString(hora, 6, RIGTH);
           ret = hora.substring(0, 2);
           ret+= ":" + hora.substring(2, 4);
           ret+= ":" + hora.substring(4, 6);
           
       }
       
       return ret.trim();
   }
   
    public static void saveProperties(String name, Properties properties) {

//        String pre = System.getProperty("user.home");
        String pre = "C:";
        String sep = File.separator.equals("/")? "/":"\\";
    	File cfg = new File( pre + sep + name + ".properties" );

    	if( cfg.exists() ) {
            cfg.delete();
        }
        try {
            FileOutputStream fos = new FileOutputStream(cfg, false);
            properties.store(fos, "Propiedades");
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static Properties loadProperties( String name ) {

//        String pre = System.getProperty("user.home");
//    	File cfg = new File( pre + "/" + name + ".properties" );
        String pre = "C:";
        String sep = File.separator.equals("/")? "/":"\\";
    	File cfg = new File( pre + sep + name + ".properties" );

        Properties prop = null;

        if( cfg.exists() ) {
            prop = new Properties();
            InputStream is = null;

            try {
                is = new FileInputStream(cfg);
                prop.load(is);
                is.close();
                cfg = null;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        return prop;
    }
   
    
    public static void main(String[] args) {
//        System.out.println(formatDecimal(new BigDecimal("0"), 6, 18));
//        System.out.println(dateToString(new Date(2010,04,15),"E").toUpperCase());
//        System.out.println(formatHora("1234567"));
        System.out.println( esNumero("-1"));
        System.out.println(Integer.parseInt( "0700" ));
//        System.out.println(julianToDate(new Integer(109365)));
//        System.out.println(julianToDateAprox(new Integer(109365)));
//        System.out.println( nroDias("20100301", "20100310") );
    }

}
