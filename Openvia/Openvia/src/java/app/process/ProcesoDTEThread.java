/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app.process;

import java.util.List;

/**
 *
 * @author Miguelón
 */
public class ProcesoDTEThread extends Thread {
//    private static MyThreadGroup myThreadGroup;
    
    public ProcesoDTEThread() {
        super();
    }

    @Override
    public void run() {
        if (!ProcesoDTE.isActivo()) {
            ProcesoDTE.setActivo(true);
            procesa();
        }
    }

    private void procesa() {
        List doctos;
        
//        while (ProcesoDTE.isActivo()) {
//            if (ProcesoDTE.isInRango()) {
//                ProcesoDTE.setIteraciones(ProcesoDTE.getIteraciones()+1);
//                doctos = ProcesoService.traeDoctos();
//                for (Iterator it= doctos.iterator(); it.hasNext(); ) {
//                    DoctoProc doctoProc = (DoctoProc) it.next();
//                    if (ProcesoDTE.isActivo()) {
//                        ProcesoDTE.setDoctos(ProcesoDTE.getDoctos()+1);
//
//                        // Procesar
//                        System.out.println("ID: " + doctoProc.getIdDocto());
//                        if ( doctoProc.getIdTipoDocto() == 101 ) {
//                            DTEService.enviaDoctoBoleta(doctoProc);
//                        } else {
//                            DTEService.enviaDoctoNoBol(doctoProc);
//                        }
//                        ProcesoService.updateDocto(doctoProc);
//
//                        Util.delay(new Integer(2).longValue());
//
//                    } else {
//                        break;
//                    }
//                }
//                if (ProcesoDTE.isActivo()) {
//                    long sec = new Integer(ProcesoDTE.getFrecuencia()).longValue();
//                    Util.delay(sec);
//                }
//            } else {
//                Util.delay(new Integer(60).longValue());
//            }
//        }
    }
    
}
