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
@WebServlet(name = "VerificaNuovo", urlPatterns = {"/VerificaNuovo"})
public class VerificaNuovo extends HttpServlet {

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
        HttpSession session =request.getSession();
        Object temp=session.getAttribute("Username");

   if(temp!=null)
     {
        try {
            String titolo=request.getParameter("titolo");
            titolo="\""+titolo+"\"";
            String query="Select Titolo From DocumentiDaPubblicare Where Titolo="+titolo+";";
            String vuoto="";
            RisultatoQuery=Database.query(query);
            
            if(!RisultatoQuery.next())
            {
                     String Query2="";
                     String autore=session.getAttribute("Username").toString();
                     String testo1=request.getParameter("messaggio1");
                     String testo2=request.getParameter("messaggio2");
                     String testo3=request.getParameter("messaggio3");
                     String testo4=request.getParameter("messaggio4");
                     String testo5=request.getParameter("messaggio5");
                     testo1="\""+testo1+"\"";
                     testo2="\""+testo2+"\"";
                     testo3="\""+testo3+"\"";
                     testo4="\""+testo4+"\"";
                     testo5="\""+testo5+"\"";
                     if((vuoto.compareTo(testo2))!=0 &&(vuoto.compareTo(testo3))!=0  &&(vuoto.compareTo(testo4))==0  && (vuoto.compareTo(testo5))!=0)
                       Query2="Insert into DocumentiDaPubblicare(Titolo,Parte1,Parte2,Parte3,Parte4,Parte5,Editore1) values("+titolo+","+testo1+","+testo2+",'"+testo3+","+testo4+","+testo5+",'"+autore+"');";
                     else if((vuoto.compareTo(testo2))==0)
                             Query2="Insert into DocumentiDaPubblicare(Titolo,Parte1,Parte2,Parte3,Parte4,Parte5,Editore1) values("+titolo+","+testo1+",'','','','','"+autore+"');";
                     else if((vuoto.compareTo(testo3))==0)                                                                                
                             Query2="Insert into DocumentiDaPubblicare(Titolo,Parte1,Parte2,Parte3,Parte4,Parte5,Editore1) values("+titolo+","+testo1+","+testo2+",'','','','"+autore+"');";
       
                     else if((vuoto.compareTo(testo4))==0)
                                    Query2="Insert into DocumentiDaPubblicare(Titolo,Parte1,Parte2,Parte3,Parte4,Parte5,Editore1) values("+titolo+","+testo1+","+testo2+","+testo3+",'','','"+autore+"');";
                     else
                                    Query2="Insert into DocumentiDaPubblicare(Titolo,Parte1,Parte2,Parte3,Parte4,Parte5,Editore1) values("+titolo+","+testo1+","+testo2+","+testo3+","+testo4+",'','"+autore+"');";
                   
       
                     Database.operation(Query2);
                     String indirizzo="/NuovoOk.jsp";
                     doForward(indirizzo,request,response);
                    
            }
            else
            {
                String indirizzo="/NuovoErrato.jsp"; 
                doForward(indirizzo,request,response); 
            }
        }
        
        catch(SQLException e){
            
        }
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
