/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.process.model;

import java.io.Serializable;

/**
 *
 * @author Miguelón
 */
public class DoctoProc implements Serializable {

    private int idDocto;
    private int idTipoDocto;
    private String ted;
    private int estadoDTE;
    private String descEstadoDTE;

    public DoctoProc () {
    }

    /**
     * @return the idDocto
     */
    public int getIdDocto() {
        return idDocto;
    }

    /**
     * @param idDocto the idDocto to set
     */
    public void setIdDocto(int idDocto) {
        this.idDocto = idDocto;
    }

    /**
     * @return the idTipoDocto
     */
    public int getIdTipoDocto() {
        return idTipoDocto;
    }

    /**
     * @param idTipoDocto the idTipoDocto to set
     */
    public void setIdTipoDocto(int idTipoDocto) {
        this.idTipoDocto = idTipoDocto;
    }

    /**
     * @return the ted
     */
    public String getTed() {
        return ted;
    }

    /**
     * @param ted the ted to set
     */
    public void setTed(String ted) {
        this.ted = ted;
    }

    /**
     * @return the estadoDTE
     */
    public int getEstadoDTE() {
        return estadoDTE;
    }

    /**
     * @param estadoDTE the estadoDTE to set
     */
    public void setEstadoDTE(int estadoDTE) {
        this.estadoDTE = estadoDTE;
    }

    /**
     * @return the descEstadoDTE
     */
    public String getDescEstadoDTE() {
        return descEstadoDTE;
    }

    /**
     * @param descEstadoDTE the descEstadoDTE to set
     */
    public void setDescEstadoDTE(String descEstadoDTE) {
        this.descEstadoDTE = descEstadoDTE;
    }


}
