/*
 * ResumenLibro.java
 *
 * Created on 18 de marzo de 2007, 12:44 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package app.process.model;

import java.io.Serializable;

/**
 *
 * @author Miguelon
 */
public class TotalesLibro implements Serializable {
    private int nroDoctos;
    private int nroDoctosDetalle;
    
    /** Creates a new instance of Consulta */
    public TotalesLibro() {
        setNroDoctos(0);
        setNroDoctosDetalle(0);
    }

    public int getNroDoctos() {
        return nroDoctos;
    }

    public void setNroDoctos(int nroDoctos) {
        this.nroDoctos = nroDoctos;
    }

    public int getNroDoctosDetalle() {
        return nroDoctosDetalle;
    }

    public void setNroDoctosDetalle(int nroDoctosDetalle) {
        this.nroDoctosDetalle = nroDoctosDetalle;
    }

}
