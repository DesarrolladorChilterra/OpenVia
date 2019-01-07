/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import app.common.Util;
import app.process.service.DTEService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Miguelon
 */
public class TraePDF extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        boolean pdfOk = false;
        String mensaje = "Error en parámetros.";

        String pRut = request.getParameter("RUT");
        String par1 = request.getParameter("TipoDoc");
        String par2 = request.getParameter("FolioDoc");

        if ( Util.esNumero(par1) && Util.esNumero(par2) && pRut != null) {

            int i = 10;
            byte[] bytes = null;

            while ( i > 0) {
                bytes = DTEService.traePDF( pRut, Integer.parseInt(par1), Long.parseLong(par2) );
                if (bytes == null) {
                    i--;
                    Util.delay(3);
                } else {
                    break;
                }
            }
            if (bytes == null) {
                mensaje = "PDF no encontrado.";
            } else {

                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                response.setHeader( "Content-disposition", "inline; filename=DTE-" + par1 + "-" + par2 + ".pdf");
                response.getOutputStream().write(bytes);
                pdfOk = true;
            }

        }

        if ( !pdfOk ) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet TraePDF</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + mensaje  + "</h1>");
                out.println("</body>");
                out.println("</html>");    //           */
            } finally {
                out.close();
            }
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
