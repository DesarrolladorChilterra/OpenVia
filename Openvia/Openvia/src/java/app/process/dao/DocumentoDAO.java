/*
 * OperadorDAO.java
 */

package app.process.dao;

import java.math.BigDecimal;

/**
 * @author Miguel Vega Brante.
 */
public class DocumentoDAO  {

    // Yukón
    private static String cRutEmisor    = "79952820-7";
    private static String cRazonEmisor  = "COMERCIALIZADORA YUKON LIMITADA";
    private static String cGiroEmisor   = "Ferreteria y Materiales de Construccion";
    private static String cCodActEmisor = "523410";
    private static String cDirEmisor    = "Av. El Rodeo 13394";
    private static String cComunaEmisor = "Lo Barnechea";
    private static String cCiudadEmisor = "Santiago";
    private static BigDecimal factorIva = new BigDecimal("1.19");
     
    private static final String QUERY_ENC_BOLETA = ""
            + "SELECT a.c_tipodocumento_id, a.nro_documento as folio, to_char(a.fecha_emision, 'yyyy-MM-dd') as fchEmis, "
            + "       CASE WHEN "
            + "           (select coalesce(max(y.c_forma_pago_id), 4) from pos_c_detalle_asignacion_pagos x, pos_c_pagos y where x.c_pagos_id = y.c_pagos_id and x.c_documento_id = a.c_documento_id ) = 4 "
            + "       THEN 'n2' "
            + "       ELSE 'n1' "
            + "       END as fmaPago, "
            + "       b.taxid as rutRecep, "
            + "       b.name as rznSocRecep, "
            + "       upper(coalesce(d.Address1,'') || ' ' || coalesce(d.Address2,''))::varchar(60) AS dirRecep, "
            + "       e.nombre,COALESCE( e.nombre, upper(d.city))::varchar(20) as cmnaRecep, upper(d.city)::varchar(15) as ciudadRecep, "
            + "       COALESCE( b.giro, '') AS giroRecep, "
            + "       a.monto_neto::integer as neto, "
            + "       a.monto_impuesto::integer as mntIva, "
            + "       round(a.monto_neto+a.monto_impuesto) as mntTotal, "
            + "       a.c_documento_id "
            + "FROM   pos_c_documento a "
            + "       left outer join pos_c_bpartner b on ( a.c_bpartner_id = b.c_bpartner_id ) "
            + "       left outer join pos_c_bpartner_location c on ( a.c_bpartner_location_id = c.c_bpartner_location_id ) "
            + "       left outer join pos_c_location d on ( c.c_location_id = d.c_location_id ) "
            + "       left outer join pos_comuna e on ( d.comuna_id = e.comuna_id ) "
            + "WHERE  a.c_documento_id = ? ";

    private static final String QUERY_ENC_NO_BOL = ""
            + "SELECT a.c_tipodocumento_id, a.nro_documento as folio, to_char(a.fecha_emision, 'yyyy-MM-dd') as fchEmis, "
            + "       CASE WHEN "
            + "           (select coalesce(max(y.c_forma_pago_id), 4) from pos_c_detalle_asignacion_pagos x, pos_c_pagos y where x.c_pagos_id = y.c_pagos_id and x.c_documento_id = a.c_documento_id ) = 4 "
            + "       THEN 'n2' "
            + "       ELSE 'n1' "
            + "       END as fmaPago, "
            + "       b.taxid as rutRecep, "
            + "       b.name as rznSocRecep, "
            + "       upper(coalesce(d.Address1,'') || ' ' || coalesce(d.Address2,''))::varchar(60) AS dirRecep, "
            + "       e.nombre,COALESCE( e.nombre, upper(d.city))::varchar(20) as cmnaRecep, upper(d.city)::varchar(15) as ciudadRecep, "
            + "       COALESCE( b.giro, '') AS giroRecep, "
            + "       a.monto_neto::integer as mntNeto, "
            + "       a.monto_impuesto::integer as mntIva, "
            + "       round(a.monto_neto+a.monto_impuesto) as mntTotal, "
            + "       a.c_documento_id "
            + "FROM   pos_c_documento a "
            + "       inner join pos_c_bpartner b on ( a.c_bpartner_id = b.c_bpartner_id ) "
            + "       inner join pos_c_bpartner_location c on ( a.c_bpartner_location_id = c.c_bpartner_location_id ) "
            + "       inner join pos_c_location d on ( c.c_location_id = d.c_location_id ) "
            + "       left outer join pos_comuna e on ( d.comuna_id = e.comuna_id ) "
            + "WHERE  a.c_documento_id = ? ";

    private static final String QUERY_DET_DOCTO = ""
            + "SELECT b.value, b.name as descripcion, a.cantidad, round(a.valor_unitario) as valor_unitario, "
            + "       round(a.valor_descuento) as valor_descuento, round(a.valor_total) as valor_total "
            + "FROM   pos_c_detalledocumento a, pos_m_producto b "
            + "WHERE  a.m_product_id = b.m_product_id "
            + "AND    a.c_documento_id = ? "
            + "ORDER BY a.nro_item";

    private static final String QUERY_RELAC = ""
            + "select c.c_tipodocumento_id, c.nro_documento, c.fecha_emision, "
            + "      (c.monto_neto + c.monto_impuesto) - (b.monto_neto + b.monto_impuesto) as dif_monto "
            + "from   pos_c_doc_relacionados a, pos_c_documento b, pos_c_documento c "
            + "WHERE  a.c_documento_id = b.c_documento_id "
            + "AND    a.c_documento_id1 = c.c_documento_id "
            + "and    a.c_documento_id = ? "
            + "LIMIT 1";
    
    private static final String QUERY_ENC =
        "SELECT * " +
        "FROM   pos_c_documento " +
        "WHERE  c_documento_id = ? ";


    /** Crea una nueva instancia de OperadorDAO */
    public DocumentoDAO() {
    }

//    public static boolean traeDoctoBoleta(Transaccion trx, DoctoProc doctoProc, DocumentoBoleta.SiiDte.Documento doc) throws SQLException{
//        Connection con = null;
//        ResultSet res = null;
//        PreparedStatement pst = null;
//        boolean ret = false;
//
//        // Obtener conexion
//        con = trx.getConn();
//
//        // Trae resolucion
////        RESOL = ProcesoService.traeResolucion(RTKCO, RTDCT.equals(cBOLETA));
//
////        docto = new Docto();
//        pst = con.prepareStatement(QUERY_ENC_BOLETA);
//        pst.setObject(1, doctoProc.getIdDocto());
//        res = pst.executeQuery();
//
//        try  {
//
//            if ( res.next() ){
//                ret = true;
//
//                //Documento/Encabezado/IdDoc
//                DocumentoBoleta.SiiDte.IdDoc idDoc = new DocumentoBoleta.SiiDte.IdDoc();
//                idDoc.setTipoDTE(DocumentoBoleta.SiiDte.DTEType.n39);
//                idDoc.setFolio(BigInteger.valueOf(res.getInt("folio")));
//                idDoc.setFchEmis(new com.liquid_technologies.ltxmllib8.DateTime(com.liquid_technologies.ltxmllib8.DateTimeType.date, res.getString("fchEmis")));
//                idDoc.setIndServicio(DocumentoBoleta.SiiDte.IdDoc_IndServicio.n3);
//                doc.getEncabezado().setIdDoc(idDoc);
//
//                //Documento/Encabezado/Emisor ( Yukón !! )
//                DocumentoBoleta.SiiDte.Emisor emisor = new DocumentoBoleta.SiiDte.Emisor();
//                emisor.setRUTEmisor(cRutEmisor);
//                emisor.setRznSocEmisor(cRazonEmisor);
//                emisor.setGiroEmisor(cGiroEmisor);
////                emisor.getActeco().add(new BigInteger(cCodActEmisor));
//
//                emisor.setCdgSIISucur(BigInteger.ONE);
//                emisor.setDirOrigen(cDirEmisor);
//                emisor.setCmnaOrigen(cComunaEmisor);
//                emisor.setCiudadOrigen(cCiudadEmisor);
//                doc.getEncabezado().setEmisor(emisor);
//
//                //Documento/Encabezado/Receptor
//                DocumentoBoleta.SiiDte.Receptor receptor = new DocumentoBoleta.SiiDte.Receptor();
////                if ( res.getString("rutRecep") == null ) {
//                    receptor.setRUTRecep("66666666-6");
//                    receptor.setRznSocRecep("Cliente Boletas");
////                } else {
////                    receptor.setRUTRecep( res.getString("rutRecep") );
////                    receptor.setRznSocRecep( res.getString("rznSocRecep") );
////                    receptor.setDirRecep( res.getString("dirRecep") );
////                    receptor.setCmnaRecep( res.getString("cmnaRecep") );
////                    receptor.setCiudadRecep( res.getString("ciudadRecep") );
////                }
//                doc.getEncabezado().setReceptor(receptor);
//
//                //Documento/Encabezado/Totales
//                DocumentoBoleta.SiiDte.Totales totales = new DocumentoBoleta.SiiDte.Totales();
//                totales.setMntTotal(new BigInteger(String.valueOf(res.getInt("mntTotal"))));
//                doc.getEncabezado().setTotales(totales);
//
//                //Documento/Detalle
//                res.close();
//                pst.close();
//                pst = con.prepareStatement(QUERY_DET_DOCTO);
//                pst.setObject(1, doctoProc.getIdDocto());
//                res = pst.executeQuery();
//
//                long linea = 1;
//                while ( res.next() ) {
//                    DocumentoBoleta.SiiDte.Detalle det = new DocumentoBoleta.SiiDte.Detalle();
//                    det.setNroLinDet(BigInteger.valueOf(linea));
//
//                    DocumentoBoleta.SiiDte.CdgItem cdgitem = new DocumentoBoleta.SiiDte.CdgItem();
//                    cdgitem.setTpoCodigo("INT");
//                    cdgitem.setVlrCodigo(toXML(res.getString("value")));
//                    det.getCdgItem().add(cdgitem);
//
//                    det.setNmbItem(toXML(res.getString("descripcion")));
//                    if ( linea == 1) {
//                       det.setNmbItem(toXML( Util.justifyString(res.getString("descripcion"),40,Util.LEFT) ));
//                    }
//                    det.setQtyItem(res.getBigDecimal("cantidad") );
//                    det.setPrcItem(BigDecimal.valueOf(res.getInt("valor_unitario")));
//                    if (res.getInt("valor_descuento") != 0) {
//                        if (res.getInt("valor_descuento") > 0) {
//                            det.setDescuentoMonto( new BigInteger(String.valueOf(res.getInt("valor_descuento"))) );
//                        } else {
//                            det.setRecargoMonto( new BigInteger(String.valueOf(res.getInt("valor_descuento")*-1)) );
//                        }
//                    }
//                    det.setMontoItem( new BigInteger(String.valueOf(res.getInt("valor_total"))) );
//                    linea++;
//
//                    doc.getDetalle().add(det);
//                }
//
////                // Referencias
////                DocumentoBoleta.SiiDte.Referencia ref = new DocumentoBoleta.SiiDte.Referencia();
////                ref.setNroLinRef(BigInteger.valueOf(1));
////                ref.setRazonRef("Registro interno");
////                ref.setCodRef("INT");
////                ref.setCodCaja("CAJA 2");
////                ref.setCodVndor("A23");
//
////                doc.getReferencia().add(ref);
//
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        res.close();
//        pst.close();
//
//
//        return ret;
//    } // TraeDoctoBoleta


//    public static boolean traeDoctoNoBol(Transaccion trx, DoctoProc doctoProc, DocumentoDTE.SiiDte.Documento doc, DocumentoDTE.SiiDte.DTEDefType dte) throws SQLException{
//        Connection con = null;
//        ResultSet res = null;
//        PreparedStatement pst = null;
//        boolean ret = false;
//        DocumentoDTE.SiiDte.CampoStringA campoString;
//
//        // Obtener conexion
//        con = trx.getConn();
//
//        // Trae resolucion
////        RESOL = ProcesoService.traeResolucion(RTKCO, RTDCT.equals(cBOLETA));
//
////        docto = new Docto();
//        pst = con.prepareStatement(QUERY_ENC_NO_BOL);
//        pst.setObject(1, doctoProc.getIdDocto());
//        res = pst.executeQuery();
//
//        try  {
//
//            if ( res.next() ){
//                ret = true;
//
//                // Personalizados
//                DocumentoDTE.SiiDte.PersonalizadosA personalizados = new DocumentoDTE.SiiDte.PersonalizadosA();
//                dte.setPersonalizados(personalizados);
//                personalizados.setDocPersonalizado(new DocumentoDTE.SiiDte.DocPersonalizadoA());
//
//                //Documento/Encabezado/IdDoc
//                DocumentoDTE.SiiDte.IdDoc idDoc = new DocumentoDTE.SiiDte.IdDoc();
//                int tipoDoc = res.getInt("c_tipodocumento_id");
//                switch (tipoDoc) {
////                    case 101:
////                        idDoc.setTipoDTE(DocumentoDTE.SiiDte.DTEType.n33);
////                        break;
//                    case 102: // Factura
//                        idDoc.setTipoDTE(DocumentoDTE.SiiDte.DTEType.n33);
//                        break;
//                    case 103: // Guía despacho
//                        idDoc.setTipoDTE(DocumentoDTE.SiiDte.DTEType.n52);
//                        break;
//                    case 104: // Nota de Crédito
//                        idDoc.setTipoDTE(DocumentoDTE.SiiDte.DTEType.n61);
//                        break;
//                    case 105: //Factura Exenta
//                        idDoc.setTipoDTE(DocumentoDTE.SiiDte.DTEType.n34);
//                        break;
//                    default:
//                        idDoc.setTipoDTE(null);
//                        break;
//                }
//
////                idDoc.setTipoDTE(DocumentoDTE.SiiDte.DTEType.n33);
//                idDoc.setFolio(BigInteger.valueOf(res.getInt("folio")));
////                idDoc.setFchEmis(new com.liquid_technologies.ltxmllib8.DateTime(com.liquid_technologies.ltxmllib8.DateTimeType.date, "2013-06-04"));
//                idDoc.setFchEmis(new com.liquid_technologies.ltxmllib8.DateTime(com.liquid_technologies.ltxmllib8.DateTimeType.date, res.getString("fchEmis")));
//                // n1 contado ( efectivo, ch dia, debito )
//                // n2 crédito
//                campoString = new DocumentoDTE.SiiDte.CampoStringA();
//                campoString.setName("CONDICION");
//
//                if (res.getString("fmaPago").equals("n1") ) {
//                    idDoc.setFmaPago(DocumentoDTE.SiiDte.IdDoc_FmaPago.n1);
//                    campoString.setText("CONTADO");
//                }
//                if (res.getString("fmaPago").equals("n2") ) {
//                    idDoc.setFmaPago(DocumentoDTE.SiiDte.IdDoc_FmaPago.n2);
//                    campoString.setText("CREDITO");
//                }
//                if (res.getString("fmaPago").equals("n3") ) {
//                    idDoc.setFmaPago(DocumentoDTE.SiiDte.IdDoc_FmaPago.n3);
//                    campoString.setText("SIN CARGO");
//                }
//                personalizados.getDocPersonalizado().getCampoString().add(campoString);
//                doc.getEncabezado().setIdDoc(idDoc);
//
//                //Documento/Encabezado/Emisor ( Yukón !! )
//                DocumentoDTE.SiiDte.Emisor emisor = new DocumentoDTE.SiiDte.Emisor();
//                emisor.setRUTEmisor(cRutEmisor);
//                emisor.setRznSoc(cRazonEmisor);
//                emisor.setGiroEmis(cGiroEmisor);
//                emisor.getActeco().add(new BigInteger(cCodActEmisor));
//                emisor.setDirOrigen(cDirEmisor);
//                emisor.setCmnaOrigen(cComunaEmisor);
//                emisor.setCiudadOrigen(cCiudadEmisor);
//                doc.getEncabezado().setEmisor(emisor);
//
//                //Documento/Encabezado/Receptor
//                DocumentoDTE.SiiDte.Receptor receptor = new DocumentoDTE.SiiDte.Receptor();
//                receptor.setRUTRecep( res.getString("rutRecep") );
//                receptor.setRznSocRecep( toXML(res.getString("rznSocRecep")).toUpperCase() );
//                receptor.setDirRecep( toXML(res.getString("dirRecep")) );
//                receptor.setCmnaRecep( res.getString("cmnaRecep") );
//                receptor.setCiudadRecep( res.getString("ciudadRecep") );
//                receptor.setGiroRecep( Util.justifyString(toXML(res.getString("giroRecep")),40, Util.LEFT ) );
//                doc.getEncabezado().setReceptor(receptor);
//
//                //Documento/Encabezado/Totales
//                DocumentoDTE.SiiDte.Totales totales = new DocumentoDTE.SiiDte.Totales();
//                if (tipoDoc == 105 ) {
//                    totales.setMntExe(new BigInteger(String.valueOf(res.getInt("mntNeto"))));
////                    totales.setTasaIVA(BigDecimal.valueOf(0));
////                    totales.setIVA(BigInteger.valueOf(0));
//                } else {
//                    totales.setMntNeto(new BigInteger(String.valueOf(res.getInt("mntNeto"))));
//                    totales.setTasaIVA(BigDecimal.valueOf(19));
//                    totales.setIVA(new BigInteger(String.valueOf(res.getInt("mntIva"))));
//                }
//                totales.setMntTotal(new BigInteger(String.valueOf(res.getInt("mntTotal"))));
//                doc.getEncabezado().setTotales(totales);
//
//                //Documento/Detalle
//                res.close();
//                pst.close();
//                pst = con.prepareStatement(QUERY_DET_DOCTO);
//                pst.setObject(1, doctoProc.getIdDocto());
//                res = pst.executeQuery();
//
//                long linea = 1;
//                while ( res.next() ) {
//                    DocumentoDTE.SiiDte.Detalle det = new DocumentoDTE.SiiDte.Detalle();
//                    det.setNroLinDet(BigInteger.valueOf(linea));
//
//                    DocumentoDTE.SiiDte.CdgItem cdgitem = new DocumentoDTE.SiiDte.CdgItem();
//                    cdgitem.setTpoCodigo("INT");
//                    cdgitem.setVlrCodigo(toXML(res.getString("value")));
//                    det.getCdgItem().add(cdgitem);
//
//                    det.setNmbItem(toXML(res.getString("descripcion")));
//                    det.setQtyItem( res.getBigDecimal("cantidad") );
//
//                    BigDecimal valUnit = BigDecimal.valueOf(res.getInt("valor_unitario")).divide(factorIva, 4, RoundingMode.HALF_UP);
//                    BigDecimal valDesc = BigDecimal.valueOf(res.getInt("valor_descuento")).divide(factorIva, 0, RoundingMode.HALF_UP);
//                    BigDecimal valTot = BigDecimal.valueOf(res.getInt("valor_total")).divide(factorIva, 0, RoundingMode.HALF_UP);
//
//                    det.setPrcItem( valUnit );
//                    if ( valDesc.intValue() != 0 ) {
//                        if ( valDesc.intValue() > 0 ) {
//                            det.setDescuentoMonto( valDesc.toBigInteger() );
//                        } else {
//                            det.setRecargoMonto( valDesc.toBigInteger().abs() );
//                        }
//                    }
//                    det.setMontoItem( valTot.toBigInteger() );
//                    linea++;
//
//                    doc.getDetalle().add(det);
//                }
//
//                // Referencias
//                res.close();
//                pst.close();
//                pst = con.prepareStatement(QUERY_RELAC);
//                pst.setObject(1, doctoProc.getIdDocto());
//                res = pst.executeQuery();
//                int i = 1;
//                while ( res.next() ) {
//                    DocumentoDTE.SiiDte.Referencia ref = new DocumentoDTE.SiiDte.Referencia();
//                    ref.setNroLinRef(BigInteger.valueOf(i));
//                    String tipoDocRef = "";
//                    if ( res.getInt("c_tipodocumento_id") == 1  ) {
//                        tipoDocRef = "35";
//                    }
//                    if ( res.getInt("c_tipodocumento_id") == 2  ) {
//                        tipoDocRef = "30";
//                    }
//                    if ( res.getInt("c_tipodocumento_id") == 3  ) {
//                        tipoDocRef = "50";
//                    }
//                    if ( res.getInt("c_tipodocumento_id") == 4  ) {
//                        tipoDocRef = "60";
//                    }
//                    if ( res.getInt("c_tipodocumento_id") == 101  ) {
//                        tipoDocRef = "39";
//                    }
//                    if ( res.getInt("c_tipodocumento_id") == 102  ) {
//                        tipoDocRef = "33";
//                    }
//                    if ( res.getInt("c_tipodocumento_id") == 103  ) {
//                        tipoDocRef = "52";
//                    }
//                    if ( res.getInt("c_tipodocumento_id") == 104  ) {
//                        tipoDocRef = "61";
//                    }
//                    ref.setTpoDocRef(tipoDocRef); //1-3 char
//                    ref.setFolioRef( "" + res.getInt("nro_documento"));
//                    String fechaRef = Util.dateToString(res.getDate("fecha_emision"), "yyyy-MM-dd");
//                    ref.setFchRef(new com.liquid_technologies.ltxmllib8.DateTime(com.liquid_technologies.ltxmllib8.DateTimeType.date, fechaRef));
//                    if ( doctoProc.getIdTipoDocto() == 104 ) {
//                        if ( res.getBigDecimal("dif_monto").intValue() == 0 ) {
//                            ref.setCodRef(DocumentoDTE.SiiDte.Referencia_CodRef.n1);
//                            ref.setRazonRef("ANULA DOCTO REF.");
//                        } else {
//                            ref.setCodRef(DocumentoDTE.SiiDte.Referencia_CodRef.n3);
//                            ref.setRazonRef("DEVOLUCION");
//                        }
//                    }
//                    doc.getReferencia().add(ref);
//                    i++;
//                }
//
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        res.close();
//        pst.close();
//
//
//        return ret;
//    } // TraeDoctoNoBol

//    public static boolean traeConsumoFolios(Transaccion trx, String fecha, ConsumoFolio.SiiDte.ConsumoFolios consumoFolios) throws SQLException{
//        Connection con = null;
//        ResultSet res = null;
//        PreparedStatement pst = null;
//        boolean ret = false;
//        ConsumoFolio.SiiDte.TipoConsumoType tipoDocConsumo = null;
//        ConsumoFolio.SiiDte.Resumen resumen = null;
//        // Obtener conexion
//        con = trx.getConn();
//
//        pst = con.prepareStatement( "select * from ovsp_consumo_folios( ? )" );
//        pst.setObject(1, fecha);
//        res = pst.executeQuery();
//
//        try  {
//
//            while ( res.next() ){
//                ret = true;
//                String tipoReg = res.getString( "oTipoReg" );
//                int tipoDoc = res.getInt( "oTipoDoc" );
//                if ( tipoDoc == 39 ) {
//                    tipoDocConsumo = ConsumoFolio.SiiDte.TipoConsumoType.n39;
//                }
//                if ( tipoDoc == 61 ) {
//                    tipoDocConsumo = ConsumoFolio.SiiDte.TipoConsumoType.n61;
//                }
//
//                if ( tipoReg.equals( "RESUMEN" )  ) {
//
//                    resumen = new ConsumoFolio.SiiDte.Resumen();
//                    resumen.setMntNeto(BigInteger.valueOf(res.getLong("oMontoNeto")));
//                    resumen.setMntIva(BigInteger.valueOf(res.getLong("oMontoIva")));
//                    resumen.setTasaIVA(res.getBigDecimal("oTasaIva"));
//
//                    resumen.setTipoDocumento( tipoDocConsumo );
//                    resumen.setMntTotal(BigInteger.valueOf(res.getLong("oMontoTotal")));
//                    resumen.setFoliosEmitidos(BigInteger.valueOf(res.getLong("oEmitidos")));
//                    resumen.setFoliosAnulados(BigInteger.valueOf(res.getLong("oAnulados")));
//                    resumen.setFoliosUtilizados(BigInteger.valueOf(res.getLong("oUtilizados")));
//
//                    consumoFolios.getDocumentoConsumoFolios().getResumen().add(resumen);
//
//                }
//
//                if ( tipoReg.equals( "RANGO" )  ) {
//
//                    ConsumoFolio.SiiDte.RangoUtilizados rangoUtilizados = new ConsumoFolio.SiiDte.RangoUtilizados();
//                    rangoUtilizados.setInicial(BigInteger.valueOf(res.getLong("oFolioIni")));
//                    rangoUtilizados.setFinal(BigInteger.valueOf(res.getLong("oFolioFin")));
//                    resumen.getRangoUtilizados().add(rangoUtilizados);
//
//                }
//
//                if ( tipoReg.equals( "ANULADO" )  ) {
//
//                    ConsumoFolio.SiiDte.RangoAnulados rangoAnulados = new ConsumoFolio.SiiDte.RangoAnulados();
//                    rangoAnulados.setInicial(BigInteger.valueOf(res.getLong("oFolioIni")));
//                    //rangoAnulados.setFinal(BigInteger.valueOf(12345));
//                    resumen.getRangoAnulados().add(rangoAnulados);
//
//                }
//                System.out.print( tipoReg + "|" + tipoDoc + "|" + res.getLong("oMontoNeto") + "|" + res.getLong("oMontoIva") + "|" + res.getBigDecimal("oTasaIva") );
//                System.out.print( "|" + res.getLong("oMontoTotal") + "|" + res.getLong("oEmitidos") + "|" + res.getLong("oAnulados") + "|" + res.getLong("oUtilizados") );
//                System.out.println( "|" + res.getLong("oFolioIni") + "|" + res.getLong("oFolioFin"));
//
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        res.close();
//        pst.close();
//
//
//        return ret;
//    } // TraeConsumoFolios

//    public static String TraeDataLibros(Transaccion trx) throws SQLException{
//        Connection con = null;
//        ResultSet res = null;
//        PreparedStatement pst = null;
//        String data = "";
//        // Obtener conexion
//        con = trx.getConn();
//
//        pst = con.prepareStatement( "select * from ovsp_gen_libro_cv_gde( ? )" );
//        pst.setObject(1, 1021826);
//        res = pst.executeQuery();
//
//        try  {
//            if ( res.next() ){
//                data = res.getString(1);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        res.close();
//        pst.close();
//
//
//        return data;
//    } // TraeDataLibros
//
//    private static String toXML( String string) {
//        String ret;
//
//        if (string == null) {
//            return null;
//        }
//
//        ret = string;
//        ret = ret.replaceAll("&", "&amp;");
//        ret = ret.replaceAll("<", "&lt;");
//        ret = ret.replaceAll(">", "&gt;");
//        ret = ret.replaceAll("“", "&quot;");
//        ret = ret.replaceAll("‘", "&apos;");
//
//        return ret;
//    }
   
    
}