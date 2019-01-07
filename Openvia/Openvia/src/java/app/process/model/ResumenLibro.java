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
import java.math.BigDecimal;

/**
 *
 * @author Miguelon
 */
public class ResumenLibro implements Serializable {
    private String tipoDoc;
    private int nroDoctos;
    private int nroDoctosAFijo;
    private int nroDoctosIvaRet;
    private BigDecimal montoNeto;
    private BigDecimal montoExento;
    private BigDecimal montoIVA;
    private BigDecimal montoDiesel;
    private BigDecimal montoGasolina;
    private BigDecimal montoTotal;
    private BigDecimal montoAFijo;
    private BigDecimal montoIvaRet;
    
    /** Creates a new instance of Consulta */
    public ResumenLibro() {
    }

    public ResumenLibro(String tipoDoc) {
        setTipoDoc(tipoDoc);
        setNroDoctos(0);
        setNroDoctosAFijo(0);
        setNroDoctosIvaRet(0);
        setMontoNeto(new BigDecimal(0));
        setMontoExento(new BigDecimal(0));
        setMontoIVA(new BigDecimal(0));
        setMontoDiesel(new BigDecimal(0));
        setMontoGasolina(new BigDecimal(0));
        setMontoTotal(new BigDecimal(0));
        setMontoAFijo(new BigDecimal(0));
        setMontoIvaRet(new BigDecimal(0));
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getNroDoctos() {
        return nroDoctos;
    }

    public void setNroDoctos(int nroDoctos) {
        this.nroDoctos = nroDoctos;
    }

    public int getNroDoctosAFijo() {
        return nroDoctosAFijo;
    }

    public void setNroDoctosAFijo(int nroDoctosAFijo) {
        this.nroDoctosAFijo = nroDoctosAFijo;
    }

    public int getNroDoctosIvaRet() {
        return nroDoctosIvaRet;
    }

    public void setNroDoctosIvaRet(int nroDoctosIvaRet) {
        this.nroDoctosIvaRet = nroDoctosIvaRet;
    }

    public BigDecimal getMontoNeto() {
        return montoNeto;
    }

    public void setMontoNeto(BigDecimal montoNeto) {
        this.montoNeto = montoNeto;
    }

    public BigDecimal getMontoExento() {
        return montoExento;
    }

    public void setMontoExento(BigDecimal montoExento) {
        this.montoExento = montoExento;
    }

    public BigDecimal getMontoIVA() {
        return montoIVA;
    }

    public void setMontoIVA(BigDecimal montoIVA) {
        this.montoIVA = montoIVA;
    }

    public void setMontoDiesel(BigDecimal montoDiesel) {
        this.montoDiesel = montoDiesel;
    }

    public BigDecimal getMontoDiesel() {
        return montoDiesel;
    }

    public void setMontoGasolina(BigDecimal montoGasolina) {
        this.montoGasolina = montoGasolina;
    }

    public BigDecimal getMontoGasolina() {
        return montoGasolina;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }
    
    public void setMontoAFijo(BigDecimal montoAFijo) {
        this.montoAFijo = montoAFijo;
    }

    public BigDecimal getMontoAFijo() {
        return montoAFijo;
    }
    
    public void setMontoIvaRet(BigDecimal montoIvaRet) {
        this.montoIvaRet = montoIvaRet;
    }

    public BigDecimal getMontoIvaRet() {
        return montoIvaRet;
    }
    
//    Acumulacion
    
    public void addNroDoctos(int n) {
        nroDoctos += n;
    }

    public void addNroDoctosAFijo(int n) {
        nroDoctosAFijo += n;
    }

    public void addNroDoctosIvaRet(int n) {
        nroDoctosIvaRet += n;
    }

    public void addMontoNeto(BigDecimal monto) {
        montoNeto = montoNeto.add( monto );
    }

    public void addMontoExento(BigDecimal monto) {
        montoExento = montoExento.add( monto );
    }

    public void addMontoIVA(BigDecimal monto) {
        montoIVA = montoIVA.add( monto );
    }

    public void addMontoDiesel(BigDecimal monto) {
        montoDiesel = montoDiesel.add( monto );
    }

    public void addMontoGasolina(BigDecimal monto) {
        montoGasolina = montoGasolina.add( monto );
    }

    public void addMontoTotal(BigDecimal monto) {
        montoTotal = montoTotal.add( monto );
    }

    public void addMontoAFijo(BigDecimal monto) {
        montoAFijo = montoAFijo.add( monto );
    }

    public void addMontoIvaRet(BigDecimal monto) {
        montoIvaRet = montoIvaRet.add( monto );
    }
}
