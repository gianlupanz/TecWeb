/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaClass;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gianluca
 */
@WebServlet(name = "VerificaEdit1", urlPatterns = {"/VerificaEdit1"})
public class VerificaEdit1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        ResultSet RisultatoQuery;
        HttpSession session =request.getSession();
        HttpSession session2 =request.getSession();
        HttpSession session3 =request.getSession();
        HttpSession hs2=request.getSession();
        HttpSession hs3=request.getSession();
       Object temp2=session.getAttribute("MODIFICA");
       String titolo = hs3.getAttribute("TitoloDocumento").toString();
        titolo="\""+titolo+"\"";
        Object temp3=session.getAttribute("Username");
        
     if(temp3!=null)
     {
       int parte2,parte;
        
       if(temp2!=null)
        
        {
            
            String p = request.getParameter("ParteDoc");
            parte = Integer.parseInt(p);

        
        String Titolo,query,temp;
        int n;
        // Acquisisco i dati: Il titolo dalla variabile di sessione e la parte
        // come richiesta dalla form
        Titolo = session.getAttribute("TitoloDocumento").toString();
        Titolo="\""+Titolo+"\"";
        
            session2.setAttribute("Parte",p);
            doForward("/Modifica.jsp",request,response);

     }//parte già in modifica
        else
            doForward("/ModificaNO.jsp",request,response);  
     }
     else
        {
          String indirizzo="/Login_Errato.jsp"; 
                doForward(indirizzo,request,response); 
        }
    
    }   
    

    private void doForward( String nuovaPagina, HttpServletRequest request, 
            HttpServletResponse response ) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(nuovaPagina);
        dispatcher.forward(request,response);

    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VerificaEdit1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VerificaEdit1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
