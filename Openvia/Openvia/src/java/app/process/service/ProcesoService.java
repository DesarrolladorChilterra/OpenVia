/*
 * OperadorService.java
 */

package app.process.service;

import app.Constantes;
import app.common.Util;
import app.process.dao.ProcesoDAO;
import app.process.dao.Transaccion;
import app.process.ProcesoDTE;
import app.process.ProcesoDTEThread;
import app.process.model.DoctoProc;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Vega Brante.
 */
public class ProcesoService extends Constantes {
    private static ProcesoDTEThread tProceso = null;
    
    /** Crea una nueva instancia de TipoDatoService */
    public ProcesoService() {
    }
    
    public static void activar( boolean activar ) {

        if (!DTEService.tienePos) {
            return;
        }
        
        // Siempre detener si es que estaba corriendo
        ProcesoDTE.setActivo(false);
        if (tProceso != null) {
            int iter = 0;
            while (tProceso.isAlive()) {
                Util.delay(new Integer(1).longValue());
                iter++;
                if (iter > 9) {
                    tProceso.interrupt();
                    break;
                }
            }
            tProceso = null;
        }
        
        if (activar) {
            ProcesoDTE.setDoctos(0);
            ProcesoDTE.setIteraciones(0);
            tProceso = new ProcesoDTEThread();
            tProceso.start();
        }

    }
    
    
    public static List traeDoctos() {
        List list = new ArrayList();
        
        Transaccion trx = Transaccion.getTransaccion(false);
        if (trx != null){
            try {
                list = ProcesoDAO.selectDoctos(trx);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                trx.close();
            }
        }
        return list;
    }

 
    public static String updateDocto(DoctoProc doctoProc) {
        String resp = null;
        
        Transaccion trx = Transaccion.getTransaccion(true);
        if (trx != null){
            try {
                int ret = ProcesoDAO.updateDocto(trx, doctoProc);
                trx.commit();
                resp = "OK";
            } catch (SQLException ex) {
                resp = ex.getMessage();
                trx.rollback();
            } finally {
                trx.close();
            }
        }
        return resp;
    }

}
