/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Miguel Vega Brante
 */
public class Config {
    private static String adempiereUser = null;
    private static String adempierePass = null;
    private static String mailUser = null;
    private static String mailPass = null;

    private static String getCatalinaBase() {
        return System.getProperty( "catalina.base" );
    }

    /**
     * @return the adempiereUser
     */
    public static String getAdempiereUser() {
        if (adempiereUser == null) {
            setProperties();
        }
        return adempiereUser;
    }

    /**
     * @return the adempierePass
     */
    public static String getAdempierePass() {
        if (adempierePass == null) {
            setProperties();
        }
        return adempierePass;
    }

    /**
     * @return the mailUser
     */
    public static String getMailUser() {
        if (mailUser == null) {
            setProperties();
        }
        return mailUser;
    }

    /**
     * @return the mailPass
     */
    public static String getMailPass() {
        if (mailPass == null) {
            setProperties();
        }
        return mailPass;
    }


    private static void setProperties() {
        Properties p = loadProperties();

        if (p != null) {
            adempiereUser = p.getProperty("adempiereUser");
            adempierePass = p.getProperty("adempierePass");
            mailUser = p.getProperty("mailUser");
            mailPass = p.getProperty("mailPass");
            p.clear();
            p = null;
        }
}

    private static Properties loadProperties( ) {

        String pathCfg = getCatalinaBase();
//        String pre = File.separator.equals("/")? "/home/tomcat":"C:";
        String sep = File.separator.equals("/")? "/":"\\";
    	File cfg = new File( pathCfg + sep + "conf" + sep + "chilterra.properties" );

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

}
