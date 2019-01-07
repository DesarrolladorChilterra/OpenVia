/*
 * ManagerDB.java
 */

package app.process.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ManagerDB {
    private static final String jdbcDriver = "org.postgresql.Driver";
    private static final String login = "postgres";
    // Produccion
    private static final String passwd_central = "pg1234";
    private static final String url_central = "jdbc:postgresql://10.100.30.2:5432/yukon";
//    private static final String url_central = "jdbc:postgresql://200.6.117.139:5432/yukon";
    // Desarrollo
//    private static final String passwd_central = "yukon";
//    private static final String url_central = "jdbc:postgresql://192.168.18.98:5432/yukon_test";
            
    static {
        try {
            //Driver
            Class.forName(jdbcDriver);
            //connectionRW = getConnectionRW();
            //connectionRO = getConnectionRO();
        }
        catch( Exception ex){
            //System.out.println( ex.toString() );
        }
    }
    
  
    public static Connection getConnectionRW(){        
        Connection connectionRW = null;
        try {
            if( connectionRW==null ){
                DriverManager.setLoginTimeout(10);
                connectionRW = DriverManager.getConnection(url_central, login, passwd_central);
                connectionRW.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connectionRW;
    }
    
    public static Connection getConnectionRO(){        
        Connection connectionRO = null;
        try {
            if( connectionRO==null ){
                DriverManager.setLoginTimeout(10);
                connectionRO = DriverManager.getConnection(url_central, login, passwd_central);
                connectionRO.setAutoCommit(false);
                connectionRO.setReadOnly(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connectionRO;
    }          
    
    public static boolean closeConnection(Connection connection) {
        //return true;
        if( connection==null)
            return true;
        try {
            connection.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
         
    }        

    public static boolean isOnline(){        
        Connection connectionRO = null;
        boolean ret = true;
        try {
            DriverManager.setLoginTimeout(5);
            connectionRO = DriverManager.getConnection(url_central, login, passwd_central);
            connectionRO.close();
        } catch (SQLException ex) {
            ret = false;
            ex.printStackTrace();
        }
        return ret;
    }          
    
        
}