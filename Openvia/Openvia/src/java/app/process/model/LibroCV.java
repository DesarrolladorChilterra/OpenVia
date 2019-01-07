/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.process.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguelón
 */
public class LibroCV implements Serializable {
    private String rutEmpresa;
    private String resolucion;
    private String tipoLibro;
    private String periodo;
    private String stringLibro;
    private List doctos;

    public LibroCV () {
        this.doctos = new ArrayList(); 
    }
    public String getRutEmpresa() {
        return rutEmpresa;
    }

    public void setRutEmpresa(String rutEmpresa) {
        this.rutEmpresa = rutEmpresa;
    }

    public String getTipoLibro() {
        return tipoLibro;
    }

    public void setTipoLibro(String tipoLibro) {
        this.tipoLibro = tipoLibro;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    public String getStringLibro() {
        return stringLibro;
    }

    public void setStringLibro(String stringLibro) {
        this.stringLibro = stringLibro;
    }

    public List getDoctos() {
        return doctos;
    }

    public void setDoctos(List doctos) {
        this.doctos = doctos;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

}
