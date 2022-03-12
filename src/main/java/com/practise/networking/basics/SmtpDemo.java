package com.practise.networking.basics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SmtpDemo {
    public static void main(String[] args){
        try{
            Socket SMTPCONNECTION = new Socket("localhost", 9090);  // default : 25
            BufferedReader READ = new BufferedReader(new InputStreamReader(SMTPCONNECTION.getInputStream()));
            PrintWriter WRITE = new PrintWriter(new OutputStreamWriter(SMTPCONNECTION.getOutputStream()));
            String responsetext;

            try{
                WRITE.println("Server:");
                System.out.println("Step 1. greeting the server..\r\n");
                while((responsetext = READ.readLine()) != null){
                    System.out.println("response is: " + responsetext);
                    if(responsetext.contains("220")){
                        System.out.println("Server replied...we are good to go....");
                        break;
                    }
                }

                WRITE.println("HELO" + InetAddress.getLocalHost().getHostAddress());
                System.out.println("Step 2. sending helo/ehlo to server ...\r\n");
                while((responsetext = READ.readLine()) != null){
                    System.out.println("response is: " + responsetext);
                    if(responsetext.contains("250"))
                        break;
                }

                WRITE.println("MAIL FROM: localhost@kle.ac.in");
                System.out.println("Step 3. sending message origin location ...\r\n");
                while((responsetext = READ.readLine()) != null){
                    if(responsetext.contains("250"))
                        break;
                }


                WRITE.println("RCPT TO: sudarshanrbhat.srb2@gmail.com");
                System.out.println("Step 4. sending message receipt location ...\r\n");
                while((responsetext = READ.readLine()) != null){
                    if(responsetext.contains("250"))
                        break;
                }
                WRITE.println("DATA");
                System.out.println("Step 5. requesting permission to send message body ...\r\n");
                while((responsetext = READ.readLine()) != null){
                    if(responsetext.contains("354"))
                        break;
                }

                WRITE.println("MAIL FROM: localhost@kle.ac.in");
                WRITE.println("RCPT TO: sudarshanrbhat.srb2@gmail.com");
                WRITE.println("Subject: this is a test mail");
                WRITE.println("This is just a test message");
                WRITE.println("regards, \r\n localhost");
                WRITE.println();
                WRITE.println(".");
                WRITE.println();
                System.out.println("Step 6. specify the end sequence...\r\n");
                while((responsetext = READ.readLine()) != null){
                    if(responsetext.contains("250"))
                        break;
                }

                WRITE.println("QUIT");
                System.out.println("Step 7. sending close connection request ...\r\n");
                while((responsetext = READ.readLine()) != null){
                    if(responsetext.contains("221"))
                        break;
                }

                System.out.println("Mail sent successfully....!");

                WRITE.close();
                READ.close();
                SMTPCONNECTION.close();
            }
            catch(Exception e){System.out.println(e.getMessage());}
        }
        catch(Exception e){System.out.println(e.getMessage());}

    }

}
