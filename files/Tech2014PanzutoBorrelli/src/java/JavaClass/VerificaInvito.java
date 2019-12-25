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
@WebServlet(name = "VerificaInvito", urlPatterns = {"/VerificaInvito"})
public class VerificaInvito extends HttpServlet {

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
        String user,query,titolo,utente,tmp,indirizzo="";
        HttpSession session;
        HttpSession err =request.getSession();
        ResultSet rs,rs2,rs3;
        int val,val2,val3,cont1,cont2,cont3,cont4,cont5,res;
        
        utente = request.getParameter("prova1");
        titolo = request.getParameter("prova2");
        titolo="\""+titolo+"\"";
        session = request.getSession();
        user = session.getAttribute("Username").toString();
        //controllo che non permette di invitare un utente che già condivide il documento
        query = "select * from DocumentiDaPubblicare where Titolo ="+titolo+" AND "
                + "(Editore1 = '"+utente+"' or Editore2 = '"+utente+"' or "
                + "Editore3 = '"+utente+"' or Editore4 = '"+utente+"' or Editore5 = '"+utente+"');";
        rs = Database.query(query);
  
       

        if(rs.next())
            val=1;
        else
            val=0;
       

        query="Select * from DocumentiDaPubblicare Where Titolo="+titolo+" AND Editore1<>'NULL'";
        rs = Database.query(query);
        if(rs.next())
            cont1= 1;
        else
            cont1=0;
      
        query="Select * from DocumentiDaPubblicare Where Titolo="+titolo+" AND Editore2<>'NULL'";
        rs = Database.query(query);
        if(rs.next())
            cont2= 1;
        else
            cont2=0;
        query="Select * from DocumentiDaPubblicare Where Titolo="+titolo+" AND Editore3<>'NULL'";
        rs = Database.query(query);
        if(rs.next())
            cont3= 1;
        else
            cont3=0;
        query="Select * from DocumentiDaPubblicare Where Titolo="+titolo+" AND Editore4<>'NULL'";
        rs = Database.query(query);
        if(rs.next())
            cont4= 1;
        else
            cont4=0;
        query="Select * from DocumentiDaPubblicare Where Titolo="+titolo+" AND Editore5<>'NULL'";
        rs = Database.query(query);
        if(rs.next())
            cont5= 1;
        else
            cont5=0;
       
        res=cont1+cont2+cont3+cont4+cont5;
        if (res>=5 || val==1)
        {
            indirizzo = "/ImpossibileInvitare.jsp";
            
            if(res>=5)
                err.setAttribute( "Errore","Documento Condiviso già da 5 persone");
            else
                err.setAttribute( "Errore","La persona già condivide il documento");
        }
            else{
             //controllo che non permette di invitare un utente che è già stato invitato e di cui attendiamo la risposta
            query = "Select * from NotificheCondivisione where "
                    + "TitoloDocumento="+titolo+" and Destinatario ='"+utente+"';";
            rs = Database.query(query);
            if (rs.next())
            {
                indirizzo = "/ImpossibileInvitare.jsp";
                err.setAttribute( "Errore","La persona e' già stata invitata");           
            }
            else{
                indirizzo = "/InvitoOk.jsp";
                query = "Insert into NotificheCondivisione values "
                        + "("+titolo+",'"+utente+"','"+user+"');";
                Database.operation(query);
            }
        }
        doForward(indirizzo,request,response);
        
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
            Logger.getLogger(VerificaInvito.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerificaInvito.class.getName()).log(Level.SEVERE, null, ex);
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
