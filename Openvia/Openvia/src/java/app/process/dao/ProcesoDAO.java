/*
 * OperadorDAO.java
 */

package app.process.dao;

import app.Constantes;
import app.process.model.DoctoProc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Vega Brante.
 */
public class ProcesoDAO extends Constantes {
     
    private static final String QUERY_PROC = ""
        + "SELECT c_documento_id, c_tipodocumento_id, ted from pos_c_documento "
        + " WHERE (estado_dte is null or estado_dte != '0') "
        + "   AND c_tipodocumento_id > 100 "
        + "   AND ted is not null "
        + "   AND estado_docto not in ('A', 'E') "
        + " ORDER BY 1 "
        + "LIMIT 30";
    
    private static final String UPD_DOCTO = 
          "UPDATE pos_c_documento "
        + "   SET estado_dte = ?, desc_estado_dte = ? "
        + " WHERE c_documento_id = ? ";
    
    /** Crea una nueva instancia de OperadorDAO */
    public ProcesoDAO() {
    }

    public static List selectDoctos(Transaccion trx) throws SQLException{
        Connection con = null;
        ResultSet res = null;
        PreparedStatement pst = null;
        List list = new ArrayList();
        String query;

        // Obtener conexion
        con = trx.getConn();
        
        // Traer Doctos
        query = QUERY_PROC;
        pst = con.prepareStatement(query);
        pst.setMaxRows(30);
        res = pst.executeQuery();
        
        while ( res.next() ){
            DoctoProc doctoProc = new DoctoProc();
            doctoProc.setIdDocto(res.getInt("c_documento_id"));
            doctoProc.setIdTipoDocto(res.getInt("c_tipodocumento_id"));
            doctoProc.setTed(res.getString("ted"));
            list.add(doctoProc);
        }

        res.close();
        pst.close();

        return list;
    }

    public static int updateDocto(Transaccion trx, DoctoProc doctoProc) throws SQLException{
        Connection con = null;
        PreparedStatement pst = null;

        // Obtener conexion
        con = trx.getConn();
        
        pst = con.prepareStatement(UPD_DOCTO);
        pst.setObject(1, doctoProc.getEstadoDTE());
        pst.setObject(2, doctoProc.getDescEstadoDTE());
        pst.setObject(3, doctoProc.getIdDocto() );
        int ret  = pst.executeUpdate();
       
        pst.close();
        
        return ret;
    }
 
    
}