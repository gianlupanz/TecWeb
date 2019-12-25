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
@WebServlet(name = "VerificaStorico", urlPatterns = {"/VerificaStorico"})
public class VerificaStorico extends HttpServlet {

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
        
        String data, titolo, query, parte1,parte2,parte3,parte4,parte5;
        ResultSet rs;
        HttpSession hs;
             HttpSession session =request.getSession();
        Object temp=session.getAttribute("Username");

   if(temp!=null)
     {
	// nella select ho messo come 'value' la data del testo

        data = request.getParameter("storico1");
        hs = request.getSession();
        titolo = hs.getAttribute("TitoloDocumento").toString();
        titolo="\""+titolo+"\"";
        
	// inserisco nello storico l'ultimo documento da pubblicare che verrà sostituito con quello ripristinato

        query="SELECT Parte1,Parte2,Parte3,Parte4,Parte5 From DocumentiDaPubblicare WHERE Titolo="+titolo+";";
        rs = Database.query(query);
        rs.next();
        parte1=rs.getString(1);
        parte2=rs.getString(2);
        parte3=rs.getString(3);
        parte4=rs.getString(4);
        parte5=rs.getString(5);
        parte1="\""+parte1+"\"";
        parte2="\""+parte2+"\"";
        parte3="\""+parte3+"\"";
        parte4="\""+parte4+"\"";
        parte5="\""+parte5+"\"";
        
        query="INSERT INTO STORICO VALUES(NOW(),"+titolo+","+parte1+","+parte2+","+parte3+","+parte4+","+parte5+");";
     
        Database.operation(query);
        
        query="SELECT Parte1,Parte2,Parte3,Parte4,Parte5 FROM Storico WHERE Data='"+data+"';";
        rs = Database.query(query);
        rs.next();
        parte1=rs.getString(1);
        parte2=rs.getString(2);
        parte3=rs.getString(3);
        parte4=rs.getString(4);
        parte5=rs.getString(5);
        parte1="\""+parte1+"\"";
        parte2="\""+parte2+"\"";
        parte3="\""+parte3+"\"";
        parte4="\""+parte4+"\"";
        parte5="\""+parte5+"\"";
        
        query="UPDATE DocumentiDaPubblicare Set Parte1="+parte1+" ,Parte2="+parte2+", Parte3="+parte3+", Parte4="+parte4+", Parte5="+parte5+" WHERE Titolo="+titolo+";";
	// aggiorno l'ultimo documento da pubblicare con il ripristino, aggiornando le 5 parti
        
        
        
        Database.operation(query);
    
        doForward("/StoricoOk.jsp",request,response);
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
            Logger.getLogger(VerificaStorico.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerificaStorico.class.getName()).log(Level.SEVERE, null, ex);
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
