/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaClass;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.*;
import javax.naming.*;

/**
 *
 * @author iAngelo
 */
public class Database {
    
  InitialContext ctx = null;
  DataSource ds = null;
  Connection conn = null;
  PreparedStatement ps = null;
  ResultSet rs = null;
    
  
  public static ResultSet query (String Query) throws ServletException {
    Connection        conn = null;
   Statement         st = null;
   ResultSet         rs = null;
   ResultSetMetaData rmd = null;
   
   try{
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      System.out.println("Driver Registration Successful.");
   } catch (InstantiationException ie){
      System.out.println("Class Instantiation Exception: " + ie);
   } catch (ClassNotFoundException cnf){
      System.out.println("Class Not Found Exception: " + cnf);
   } catch (IllegalAccessException iae){
      System.out.println("Illegal Access Exception: " + iae);
   }
  
   try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/DBTech?user=root&password=gianluca");
      System.out.println("Connection to MySQL Database Successful");
   } catch (SQLException sqe1){
      System.out.println("Caught SQL Exception: " + sqe1);
   }
   try {
      st = conn.createStatement();
      rs = st.executeQuery(Query);
      } 
     catch (SQLException sqe2){
      System.out.println("Caught SQL Exception: " + sqe2);
   }

  return rs;
  
}

  
    public static void operation (String Query) throws ServletException {
    Connection        conn = null;
   Statement         st = null;
   ResultSet         rs = null;

   try{
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      System.out.println("Driver Registration Successful.");
   } catch (InstantiationException ie){
      System.out.println("Class Instantiation Exception: " + ie);
   } catch (ClassNotFoundException cnf){
      System.out.println("Class Not Found Exception: " + cnf);
   } catch (IllegalAccessException iae){
      System.out.println("Illegal Access Exception: " + iae);
   }
  
   try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/DBTech?user=root&password=gianluca");
      System.out.println("Connection to MySQL Database Successful");
   } catch (SQLException sqe1){
      System.out.println("Caught SQL Exception: " + sqe1);
   }
   try {
      st = conn.createStatement();
      st.executeUpdate(Query);
      } 
     catch (SQLException sqe2){
      System.out.println("Caught SQL Exception: " + sqe2);
   }
   
}
  
  
  
}
