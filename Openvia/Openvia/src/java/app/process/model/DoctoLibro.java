/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.process.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Miguelón
 */
public class DoctoLibro implements Serializable {
    private String RPKCO;
    private String RPDCT;
    private BigDecimal RPDOC;
    private BigDecimal montoNeto;
    private BigDecimal montoExento;
    private BigDecimal montoIVA;
    private BigDecimal montoDiesel;
    private BigDecimal montoGasolina;
    private BigDecimal montoTotal;
    private BigDecimal montoAFijo;
    private BigDecimal montoIvaRet;

    public DoctoLibro (String RPKCO, String RPDCT, BigDecimal RPDOC) {
        this.RPKCO = RPKCO;
        this.RPDCT = RPDCT;
        this.RPDOC = RPDOC;
        // Inicializa valores
        montoNeto = new BigDecimal(0);
        montoExento = new BigDecimal(0);
        montoIVA = new BigDecimal(0);
        montoDiesel = new BigDecimal(0);
        montoGasolina = new BigDecimal(0);
        montoTotal = new BigDecimal(0);
        montoAFijo = new BigDecimal(0);
        montoIvaRet = new BigDecimal(0);
    }

    public void addMontoNeto(BigDecimal monto) {
        montoNeto = montoNeto.add(monto);
        addMontoTotal(monto);
    }

    public void addMontoExento(BigDecimal monto) {
        montoExento = montoExento.add(monto);
        addMontoTotal(monto);
    }

    public void addMontoIVA(BigDecimal monto) {
        montoIVA = montoIVA.add(monto);
        addMontoTotal(monto);
    }

    public void addMontoDiesel(BigDecimal monto) {
        montoDiesel = montoDiesel.add(monto);
        addMontoTotal(monto);
    }

    public void addMontoGasolina(BigDecimal monto) {
        montoGasolina = montoGasolina.add(monto);
        addMontoTotal(monto);
    }

    public void addMontoTotal(BigDecimal monto) {
        montoTotal = montoTotal.add(monto);
    }

    public void addMontoAFijo(BigDecimal monto) {
        montoAFijo = montoAFijo.add(monto);
    }

    public void addMontoIvaRet(BigDecimal monto) {
        montoIvaRet = montoIvaRet.add(monto);
    }

    public String getRPKCO() {
        return RPKCO;
    }

    public String getRPDCT() {
        return RPDCT;
    }

    public BigDecimal getRPDOC() {
        return RPDOC;
    }

    public BigDecimal getMontoNeto() {
        return montoNeto;
    }

    public BigDecimal getMontoExento() {
        return montoExento;
    }

    public BigDecimal getMontoIVA() {
        return montoIVA;
    }

    public BigDecimal getMontoDiesel() {
        return montoDiesel;
    }

    public BigDecimal getMontoGasolina() {
        return montoGasolina;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public BigDecimal getMontoAFijo() {
        return montoAFijo;
    }

    public BigDecimal getMontoIvaRet() {
        return montoIvaRet;
    }
    
}
