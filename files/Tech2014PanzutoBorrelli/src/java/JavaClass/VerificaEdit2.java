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
@WebServlet(name = "VerificaEdit2", urlPatterns = {"/VerificaEdit2"})
public class VerificaEdit2 extends HttpServlet {

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
        
        String testo_nuovo, titolo, query, parte;
        ResultSet rs;
        HttpSession hs,hs2,session;
        int parte2;
        String sezione1,sezione2,sezione3,sezione4,sezione5;
        // Inizializzo le variabili e prendo i dati sia da client (form) sia
        // dalle variabili di sessione
        session= request.getSession();
        session.setAttribute( "MODIFICA",null);// Entro nella sezione critica, evitando che altri possano modificare la stessa sezione
        hs = request.getSession();
        hs2 = request.getSession();
        titolo = hs.getAttribute("TitoloDocumento").toString();
        titolo="\""+titolo+"\"";
        parte = hs2.getAttribute("Parte").toString();
        parte2=Integer.parseInt(parte);
        parte="Parte"+parte;
        testo_nuovo = request.getParameter("testo");
        testo_nuovo="\""+testo_nuovo+"\"";
        // Prendo i dati del vecchio documento
        query = "select Parte1, Parte2, Parte3, Parte4, Parte5"
                +" from DocumentiDaPubblicare where Titolo ="+titolo+";";
        rs = Database.query(query);
        rs.next();
         sezione1=rs.getString(1);
        sezione2=rs.getString(2);
        sezione3=rs.getString(3);
        sezione4=rs.getString(4);
        sezione5=rs.getString(5);
        sezione1="\""+sezione1+"\"";
        sezione2="\""+sezione2+"\"";
        sezione3="\""+sezione3+"\"";
        sezione4="\""+sezione4+"\"";
        sezione5="\""+sezione5+"\"";
        
        // Inserimento nello storico del vecchio documento
        // Storico (DateTime in tipo Stringa,Editore,Titolo,Parte1,Parte2....)
        // La funzione NOW () restituisce il DateTime Corrente
        query = "insert into Storico values (NOW(),"
                +titolo+","+sezione1+","+sezione2+","
                +sezione3+","+sezione4+","+sezione5+");";
        Database.operation (query);
        
        // effettuo l'aggiornamento della parte nuova
        query = "update DocumentiDaPubblicare SET "
                +parte+" = "+testo_nuovo+" where Titolo ="+titolo+";";
        Database.operation(query);

        //Setto a null le variabili di sessione
        hs.setAttribute("Documento",null);
        hs2.setAttribute("Parte",null);
        session.setAttribute( "MODIFICA","INIZIO");
        // Visto che ha effettuato solo aggiornamenti e inserimenti in un DB,
        // alla fine di tutto, verrà reindirizzato nella home page dell'editore
        doForward("/Successo_Modifica.jsp",request,response);
        
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
            Logger.getLogger(VerificaEdit2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerificaEdit2.class.getName()).log(Level.SEVERE, null, ex);
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
