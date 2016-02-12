/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

// File Name GreetingServer.java


import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static server.JDBCExample1.conn;
import static server.JDBCExample1.stmt;
//import static server.JDBCExample1.conn;
//import static server.JDBCExample1.stmt;


public class Server extends Thread
{
   private boolean reqSignup;
   private boolean reqLogIn;
   private boolean reqEditInfo;
   private ServerSocket serverSocket;
   private DataInputStream in;
   private Socket server;
   private DataOutputStream out;
   
   public Server(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(100000000);
      reqEditInfo = false;
      reqLogIn = false;
      reqSignup = false;
      

   }

   public void run()
   {
      while(true)
      {
         try
         {
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            System.out.println(InetAddress.getLocalHost());
            server = serverSocket.accept();
            
            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());
         
            
            int choice = in.readInt();
            System.out.println(choice);
             switch (choice) {
                 case 0:
                     signUpRequest();
                     break;
                 case 1:
                     logInRequest();
                     break;
                 case 2:
                     editInfoRequest();
                     break;
                 case 3:
                     checkUsernameAvailibity();
                     break;
                 case 4:
                     send_email();
                     break;
                 case 5:
                     reset_password();
                
                 default:
                     break;
             }  
                
               ;
            
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            break;
         } catch (SQLException ex) {
              Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
   }
   
   public static void main(String [] args)
   {
      int port = 22222;
      try
      {
         Thread t = new Server(port);
         t.start();
      }catch(IOException e)
      {
      }
    
   }
   
   void editInfoRequest()
   {
       
   }
   
   void logInRequest() throws IOException, SQLException
   {
      JDBCExample1.connect();
      
      String username = in.readUTF();
      String password = in.readUTF();
      
      boolean isUsernameValid = false;
       boolean isCombinationValid = false;
       
       isCombinationValid = JDBCExample1.authenticate(username,password);
       //check the validity of the data
       isUsernameValid = true;
       
       
       out.writeBoolean(isCombinationValid);
       out.writeBoolean(isUsernameValid);
       
   }
  void signUpRequest() throws SQLException, IOException
   {
      
      String firstname = in.readUTF();
      String lastname = in.readUTF();
      String email = in.readUTF();
      String user_name = in.readUTF();
      String password = in.readUTF();
      int otp = in.readInt();
      boolean temp =JDBCExample1.SignUprequest(firstname,lastname,email,user_name,password,otp);
      out.writeBoolean(temp);
      if(temp)
      {
      JDBCExample1.deleteotp(user_name);
      }
     
   }
  
   void checkUsernameAvailibity() throws IOException, SQLException
   {
       String user_name = in.readUTF();
       System.out.println("checkpoint 2!!");

       boolean temp= JDBCExample1.checkUsername(user_name);
       out.writeBoolean(temp);
   }
   
   void send_email() throws IOException, SQLException
   {
       System.out.println("send email function!!");
       String email = in.readUTF();
       Random rand = new Random();
       int otp= Math.abs(rand.nextInt());
        boolean temp= JDBCExample1.saveotp(email,otp);
        if (temp)
        {
       SendEmail.send(email, otp);
        }
        out.writeBoolean(temp);
   }
   
   void reset_password() throws IOException, SQLException
   {
       String username =in.readUTF();
       boolean temp= JDBCExample1.checkUsername(username);
       out.writeBoolean(temp);
       boolean temp1=JDBCExample1.extractemail(username);
       out.writeBoolean(temp1);
       int otp=in.readInt();
       boolean temp2=JDBCExample1.changepassword(username,otp);
       
       //JDBCExample1.deleteotp(username);
       
   }
   
}
