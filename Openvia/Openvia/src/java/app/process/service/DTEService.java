/*
 * DTEService.java
 */

package app.process.service;

import app.Constantes;
import dteboxcliente.GrupoBusqueda;
import java.util.List;

/**
 * @author Miguel Vega Brante.
 */
public class DTEService extends Constantes {

//  ***************************************************************************
    // Parámetros Chilterra
    public static boolean tienePos = false;
    private static final String rutEmisor = "78224490-6";

    // Produccion
//    private static final String apiURL = "http://10.100.3.5/api/Core.svc/Core";
//    private static final String apiAuth = "b6519382-ce21-4e7c-9df1-c63521012252";
    // Nuevo
//    private static final String apiURL = "http://200.6.103.3/api/Core.svc/Core";
//    private static final String apiAuth = "bbacd18d-c249-464f-8011-b7e0b2473ccb";
    // Nuevo 2018
    private static final String apiURL = "http://200.6.118.41/api/Core.svc/Core";
    private static final String apiAuth = "f3c5a632-14c9-4bdf-8b16-dae4558fe00f";

    private static final String ambiente = dteboxcliente.Ambiente.Produccion;

    /** Crea una nueva instancia de TipoDatoService */
    public DTEService() {
    }

    public static byte[] traePDF(String rutEmi, int tipoDTE, long folio) {

        dteboxcliente.Servicio servicio = new dteboxcliente.Servicio(apiURL, apiAuth);
//        dteboxcliente.ResultadoRecuperarPDF resultado = servicio.ObtenerPDF(ambiente, rutEmi, tipoDTE, folio);
        dteboxcliente.ResultadoRecuperarPDF resultado = servicio.RecuperarPdf(ambiente, GrupoBusqueda.Emitidos, rutEmi, tipoDTE, folio);
        if ( resultado.getResultadoServicio().getEstado() != 0 ) {
            System.out.println(resultado.getResultadoServicio().getDescripcion());
//            resultado.getResultadoServicio().getExcepcionOriginal().printStackTrace();
        }
        return resultado.getDatos();
    }

    public static String traeEstadoFiscal(int tipoDTE, long folio) {

        dteboxcliente.Servicio servicio = new dteboxcliente.Servicio(apiURL, apiAuth);
        dteboxcliente.ResultadoEstadoFiscal resultado = servicio.EstadoFiscal(ambiente, GrupoBusqueda.Emitidos, rutEmisor, tipoDTE, folio);
        if ( resultado.getResultadoServicio().getEstado() != 0 ) {
            System.out.println(resultado.getResultadoServicio().getDescripcion());
            resultado.getResultadoServicio().getExcepcionOriginal().printStackTrace();
        }
        return resultado.getComentarios();
    }

//    public static boolean enviaDoctoBoleta(DoctoProc doctoProc) {
//        boolean resp = false;
//
//        Transaccion trx = Transaccion.getTransaccion(false);
//        if (trx != null){
//            try {
//
//                // Crear documento GDE
//                DocumentoBoleta.SiiDte.BOLETADefType boleta = new DocumentoBoleta.SiiDte.BOLETADefType();
//                DocumentoBoleta.SiiDte.Documento doc = new DocumentoBoleta.SiiDte.Documento();
//                boleta.setDocumento(doc);
//
//                //Encabezado
//                doc.setEncabezado(new DocumentoBoleta.SiiDte.Encabezado());
//
//                if ( DocumentoDAO.traeDoctoBoleta(trx, doctoProc, doc) ) {
//
//                    dteboxcliente.Servicio servicio = new dteboxcliente.Servicio(apiURL, apiAuth);
//                    dteboxcliente.ResultadoEnvioDocumento resultado = servicio.EnviarDocumento(boleta, ambiente, fechaResolucion, numeroResolucion, tipoPdf417, doctoProc.getTed());
//
//                    doctoProc.setEstadoDTE(resultado.getResultadoServicio().getEstado());
//                    doctoProc.setDescEstadoDTE(resultado.getResultadoServicio().getDescripcion());
//                    resp = true;
//                    if ( doctoProc.getEstadoDTE() == 0){
//                        doctoProc.setDescEstadoDTE("OK");
//                        System.out.println("ok");
////                        System.out.println(doc.toXml());
//
//                    } else {
//                        String error = resultado.getResultadoServicio().getDescripcion();
//                        System.out.println("Error:\n[" + error + "]");
//                        System.out.println(resultado.getResultadoServicio().getExcepcionOriginal());
//                    }
//                }
//
//            } catch (SQLException ex) {
////                resp = ex.getMessage();
//                ex.printStackTrace();
//            } catch ( Exception ex) {
//                ex.printStackTrace();
//            }
//            trx.close();
//        }
//        return resp;
//    } // EnviaDoctoBoleta


//    public static boolean enviaDoctoNoBol(DoctoProc doctoProc) {
//        boolean resp = false;
//
//        Transaccion trx = Transaccion.getTransaccion(false);
//        if (trx != null){
//            try {
//
//                // Crear documento GDE
//                DocumentoDTE.SiiDte.DTEDefType dte = new DocumentoDTE.SiiDte.DTEDefType();
//                dte.setDTE_Choice(new DocumentoDTE.SiiDte.DTE_Choice());
//                //Documento
//                dte.getDTE_Choice().setDocumento(new DocumentoDTE.SiiDte.Documento());
//                DocumentoDTE.SiiDte.Documento doc = dte.getDTE_Choice().getDocumento();
//                //Encabezado
//                doc.setEncabezado( new DocumentoDTE.SiiDte.Encabezado() );
//
//
////               // IDDoc
////                doc.getEncabezado().setIdDoc( new DocumentoDTE.SiiDte.IdDoc() );
////                // Emisor
////                doc.getEncabezado().setEmisor( new DocumentoDTE.SiiDte.Emisor() );
////                // Receptor
////                doc.getEncabezado().setReceptor( new DocumentoDTE.SiiDte.Receptor() );
////                // Receptor
////                doc.getEncabezado().setTotales( new DocumentoDTE.SiiDte.Totales() );
//
//                if ( DocumentoDAO.traeDoctoNoBol(trx, doctoProc, doc, dte) ) {
//
//                    dteboxcliente.Servicio servicio = new dteboxcliente.Servicio(apiURL, apiAuth);
//                    dteboxcliente.ResultadoEnvioDocumento resultado = servicio.EnviarDocumento(dte, ambiente, fechaResolucion, numeroResolucion, tipoPdf417, doctoProc.getTed());
//
//                    doctoProc.setEstadoDTE(resultado.getResultadoServicio().getEstado());
//                    doctoProc.setDescEstadoDTE(resultado.getResultadoServicio().getDescripcion());
//                    resp = true;
//                    if ( doctoProc.getEstadoDTE() == 0){
//                        doctoProc.setDescEstadoDTE("OK");
//                        System.out.println("ok");
////                        System.out.println(doc.toXml());
//
//                    } else {
//                        String error = resultado.getResultadoServicio().getDescripcion();
//                        System.out.println("Error:\n[" + error + "]");
//                        System.out.println(resultado.getResultadoServicio().getExcepcionOriginal());
//                    }
////                   if (resultado.getResultadoServicio().getEstado() == 0){
////                        System.out.println("ok");
////                        System.out.println(doc.toXml());
////                    } else {
////                        String error = resultado.getResultadoServicio().getDescripcion();
////                        System.out.println("Error:\n[" + error + "]");
////                    }
//                }
//
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            } catch ( Exception ex) {
//                ex.printStackTrace();
//            }
//            trx.close();
//        }
//        return resp;
//    } // EnviaDoctoNoBol

//    public static String enviaConsumoFolios(Date fecha) {
//        String resp = "";
//
//        Transaccion trx = Transaccion.getTransaccion(true);
//        if (trx != null){
//            try {
//
//                //ConsumoFolios
//                ConsumoFolio.SiiDte.ConsumoFolios consumoFolios = new ConsumoFolio.SiiDte.ConsumoFolios();
//                consumoFolios.setDocumentoConsumoFolios(new ConsumoFolio.SiiDte.DocumentoConsumoFolios());
//
//                String fechaDAO = Util.dateToString(fecha, "yyyyMMdd");
//                String fechaEnvio = Util.dateToString(fecha, "yyyy-MM-dd");
//                if ( DocumentoDAO.traeConsumoFolios(trx, fechaDAO, consumoFolios) ) {
//
//                    dteboxcliente.Servicio servicio = new dteboxcliente.Servicio(apiURL, apiAuth);
//                    dteboxcliente.ResultadoEnvio resultado = servicio.EnviarConsumoFolio(consumoFolios, ambiente, fechaResolucion, numeroResolucion,  rutEmisor, fechaEnvio, fechaEnvio, 1, 1);
//
//                    if (resultado.getResultadoServicio().getEstado() == 0) {
////                        System.out.println(consumoFolios.toXml());
//                        System.out.println("Envío ok");
//                        System.out.println(resultado.getResultadoServicio().getDescripcion());
//                        resp = consumoFolios.toXml();
//                    } else {
//                        String error = resultado.getResultadoServicio().getDescripcion();
//                        System.out.println(error);
//                        if (resultado.getResultadoServicio().getExcepcionOriginal() != null){
//                            System.out.println(resultado.getResultadoServicio().getExcepcionOriginal().getMessage());
//                        }
//                        resp = error;
//                    }
//
//                }
//                trx.commit();
//            } catch (SQLException ex) {
//                trx.rollback();
//                ex.printStackTrace();
//            } catch ( Exception ex) {
//                trx.rollback();
//                ex.printStackTrace();
//            }
//            trx.close();
//        }
//        return resp;
//    } // EnviaDoctoNoBol

//    public static boolean EnviaLibros() {
//        boolean resp = false;
//
//        Transaccion trx = Transaccion.getTransaccion(true);
//        if (trx != null){
//            try {
//
//                String data = DocumentoDAO.TraeDataLibros(trx);
////                System.out.println(data);
//                adem2dte.Servicio.enviaLibros("GDE", data);
//                trx.commit();
//            } catch (SQLException ex) {
//                trx.rollback();
//                ex.printStackTrace();
//            } catch ( Exception ex) {
//                trx.rollback();
//                ex.printStackTrace();
//            }
//            trx.close();
//        }
//        return resp;
//    } // EnviaLibros


    public static void main(String[] args) {

        byte[] bytes = traePDF("76516670-5", 33, 1380);
        System.out.println(new String(bytes));

    }

}
