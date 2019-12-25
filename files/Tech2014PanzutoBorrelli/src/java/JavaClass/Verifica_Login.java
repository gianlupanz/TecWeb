/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author iAngelo
 */
@WebServlet(name = "Verifica_Login", urlPatterns = {"/Verifica_Login"})
public class Verifica_Login extends HttpServlet {

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
            throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      ResultSet RisultatoQuery;

      
            try {
                
                String Username=request.getParameter("Username");
                 String Password=request.getParameter("Password");
                 String query="Select Nome From Utente Where Username='"+Username+"' AND Password='"+Password+"';";
                 RisultatoQuery=Database.query(query);
   
                 
                 if(!RisultatoQuery.next()){
                     System.err.println("NON HO TROVATO NESSUN CONTATTO");
                     String indirizzo="/Login_Errato.jsp"; 
                     doForward(indirizzo,request,response);
                    
                 }
                 else
                 {
                   System.err.println("TUTTO OK, ESISTI");
                   HttpSession session =request.getSession();
                   session.setAttribute( "Username",Username);
                   String indirizzo="/Crea_Pagina_Editore.jsp";
                   doForward(indirizzo,request,response);
                 }
            }
            
            catch(SQLException e)
            {
                System.err.println("ERRORE DALLA PRIMA QUERY DI CONTROLLO DI USERNAME ESISTENTE");
                System.err.println(e.getCause() + e.getMessage());
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
        processRequest(request, response);
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
        processRequest(request, response);
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
