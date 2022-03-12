package com.practise.networking.basics.threading;
import java.net.*;
import java.io.*;
public class Server1_Thread extends Thread
{// we are using telnet to communicate. as a client.
    private Socket CONNECTION = null;
    public Server1_Thread(Socket conn)
    {
        this.CONNECTION = conn;
    }
    public void run()
    {
        System.out.println("Connection set up successfully...");
        try
        {
            // PrintWriter can take both streams and files as input
            PrintWriter WRITE = new PrintWriter(CONNECTION.getOutputStream(), true);
            WRITE.println("HI Clinet ! ");

            BufferedReader READ = new BufferedReader(new InputStreamReader(CONNECTION.getInputStream()));
            System.out.println("client: " + READ.readLine());


            WRITE.close();
            READ.close();
            CONNECTION.close();
        }
        catch(Exception e){System.out.println(e.toString());}
    }
}
