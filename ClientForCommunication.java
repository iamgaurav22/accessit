package accessit;

import java.net.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ClientForCommunication
{
   static Socket client;
   static OutputStream outToServer;
   static DataOutputStream out;
   static boolean isCombinationValid = false;
   static boolean isUsernameValid = false;
   
   public static void communicate()
   {
      String serverName = "192.168.43.202";
      int port = 22222;
      
      try
      {
         System.out.println("Connecting to " + serverName +" on port " + port);
         client = new Socket(serverName, port);
         System.out.println("Just connected to " 
		 + client.getRemoteSocketAddress());
         
         outToServer = client.getOutputStream();
         out = new DataOutputStream(outToServer);
               
         
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
   
   public static void sendOTP(String uname) throws IOException
   {
       try {
           out.writeInt(5);
           out.writeUTF(uname);
       } catch (IOException ex) {
           Logger.getLogger(ClientForCommunication.class.getName()).log(Level.SEVERE, null, ex);
           
           InputStream inFromServer = client.getInputStream();
           DataInputStream in = new DataInputStream(inFromServer);
           
           boolean flag = in.readBoolean();
       }
   }
   
   public static void logInCommunication(String username, String password) throws IOException, NoSuchAlgorithmException
   {
       
        out.writeInt(1);
        
        out.writeUTF(username);
        out.writeUTF(encrypt(password));
         
        InputStream inFromServer = client.getInputStream();
        DataInputStream in =
                        new DataInputStream(inFromServer);
         
        isCombinationValid = in.readBoolean();
        isUsernameValid = in.readBoolean(); 
   }
   
   public static boolean signUpCommunication(String firstName,String lastName,String email,String username,String password,int otp) throws IOException, NoSuchAlgorithmException
   {
       boolean flag = false;

       out.writeInt(0);
       
       out.writeUTF(firstName);
       out.writeUTF(lastName);
       out.writeUTF(email);
       out.writeUTF(username);
       
       String encryptedPassword = encrypt(password);
       out.writeUTF(encryptedPassword);
       out.writeInt(otp);
       
       InputStream inFromServer = client.getInputStream();
       DataInputStream in = new DataInputStream(inFromServer); 
       flag = in.readBoolean();
       
       return flag;
   }
   
   public static boolean checkUsernameCommunication(String username) throws IOException
   {
       boolean flag = false;

       out.writeInt(3);
       System.out.println("integer 3 written");
       out.writeUTF(username);
       System.out.println("username written");
       
       InputStream inFromServer = client.getInputStream();
       DataInputStream in = new DataInputStream(inFromServer); 
       flag = in.readBoolean();
       
       return flag;
   }
   
   public static boolean sendMail(String email) throws IOException
   {
       boolean flag = false;
       
       out.writeInt(4);
       out.writeUTF(email);
       System.out.println("email written");
       
       InputStream inFromServer = client.getInputStream();
       DataInputStream in = new DataInputStream(inFromServer); 
       
       flag = in.readBoolean();
       return flag;
   }
   
   static void closeSocket() throws IOException
   {
       client.close();
   }
   
   static boolean getIsUsernameValid()
   {
       return isUsernameValid;
   }
   
   static boolean getIsCombinationValid()
   {
       return isCombinationValid;
   }
   
   private static String encrypt(String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException
   {
       MessageDigest md = MessageDigest.getInstance("SHA-256");
       md.update(pass.getBytes("UTF-16")); // Change this to "UTF-16" if needed
       byte[] digest = md.digest();
       return Arrays.toString(digest);
   }
}
