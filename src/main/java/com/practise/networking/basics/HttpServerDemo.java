package com.practise.networking.basics;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerDemo{
    public static void main(String[] args) {
        try{
            ServerSocket SERVER = new ServerSocket(80);
            while (true){
                Socket CONNECTION = SERVER.accept();
                try{
                    HttpServerThread thread = new HttpServerThread(CONNECTION);
                    thread.start();
                }
                catch (Exception e){ System.out.println(e.toString());}
            }
        }
        catch (Exception e){ System.out.println(e.toString()); }
    }
}
