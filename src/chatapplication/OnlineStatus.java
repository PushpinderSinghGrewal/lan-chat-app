/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication;

/**
 *
 * @author Pinder
 */

import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;
import java.util.*;
public class OnlineStatus implements Runnable{
     DatagramSocket s;

    OnlineStatus(){
        try {
            s=new DatagramSocket();
        } catch (SocketException ex) {
            
        }}
    @Override
    public void run(){ 
        while(true)
    { 
        try {
             
                byte[] buf ;
 
                  
                                 
                   
                buf=MulticastClient.name.getBytes();
             
            // send it
          
                InetAddress group = InetAddress.getByName("230.0.0.2");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 5000);
               
              s.send(packet);  
              //System.out.println(Math.random());
             try{sleep((long)(Math.random() * 20000));
              
             }catch(Exception e){}
               }       
         catch (IOException e) {
               System.out.println("error in online status class");
               s.close();}
    
    
    
    
 
}}
}class ReceiveOnlineStatus implements Runnable{
InetAddress address=null;
MulticastSocket socket=null;
public static ArrayList al=new ArrayList();
ReceiveOnlineStatus(){
     try{
       socket = new MulticastSocket(5000) ;
        
        
        address=InetAddress.getByName("230.0.0.2");
      socket.joinGroup(address);
      }
        catch(Exception e)
        {System.err.println("error");
        }
}



@Override
public void run(){
    al=new ArrayList();
while(true){ 
    
   
   try{ DatagramPacket packet;
       
         
         
         byte[] buf = new byte[256];
         packet = new DatagramPacket(buf, buf.length);
   
  socket.receive(packet);
   
String name=new String(packet.getData(), 0, packet.getLength());
if(name.equals("exited"))
    al=new ArrayList();
     
       
if(!al.contains(name)&& !name.equals("exited"))
{
    al.add(name);
      
   if(MulticastClient.jTextArea3.getText().equals(""))
     MulticastClient.jTextArea3.setText(name);
   else
   { MulticastClient.jTextArea3.setText("");
   for(Object obj:al)
   {
     MulticastClient.jTextArea3.setText(MulticastClient.jTextArea3.getText()+obj.toString()+"\n");  
   }
   }       
}

   }
catch(Exception e){System.out.println("error in receiveonline status class");}










}}




}