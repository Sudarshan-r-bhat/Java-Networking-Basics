package com.practise.networking.basics.threading;
import java.net.*;
public class Server1
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket SERVER = new ServerSocket(9090);
            Boolean stop = false;
            while(!stop)
            {
                System.out.println("waiting for connection....");
                Socket CONNECTION = SERVER.accept();
                System.out.println("Client connected....");
                Server1_Thread thread = new Server1_Thread(CONNECTION);
                thread.start();
            }
            SERVER.close();
        }
        catch(Exception e){e.printStackTrace();}
    }
}
