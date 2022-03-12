package com.practise.networking.basics;
import java.net.ServerSocket;
import java.net.Socket;
public class FtpServer {
    public static void main(String[] args) {
        try {
            ServerSocket SERVER = new ServerSocket(9090);
            while(true){
                Socket CONNECTION = SERVER.accept();
                try{
                    FtpServerThread thread = new FtpServerThread(CONNECTION);
                    thread.start();
                }
                catch(Exception e) { System.out.println(e.toString()); }
            }

        }
        catch(Exception e) { System.out.println(e.toString()); }
    }
}