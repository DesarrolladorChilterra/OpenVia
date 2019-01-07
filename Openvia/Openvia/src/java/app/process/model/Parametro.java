/*
 * Parametro.java
 */

package app.process.model;

import java.io.Serializable;

public class Parametro implements Serializable {
    
    private String servidor;
    private String puerto;
    private String basedato;
    private String cuentaBD;
    private String passwordBD;
    // comun
    private String accionBD;
    
    public Parametro() {
        setAccionBD("");
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getBasedato() {
        return basedato;
    }

    public void setBasedato(String basedato) {
        this.basedato = basedato;
    }

    public String getCuentaBD() {
        return cuentaBD;
    }

    public void setCuentaBD(String cuentaBD) {
        this.cuentaBD = cuentaBD;
    }

    public String getPasswordBD() {
        return passwordBD;
    }

    public void setPasswordBD(String passwordBD) {
        this.passwordBD = passwordBD;
    }


    public String getAccionBD() {
        return accionBD;
    }

    public void setAccionBD(String accionBD) {
        this.accionBD = accionBD;
    }
    
}