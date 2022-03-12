package com.practise.networking.basics;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class FtpClient {
    public static void main(String[] args) {
        try {
            Socket FTPCONNECTION = new Socket("localhost", 9090);


            PrintWriter STREAMWRITE = new PrintWriter(FTPCONNECTION.getOutputStream(), true);
            STREAMWRITE.println("FTPfile.txt");


            BufferedInputStream input = new BufferedInputStream(FTPCONNECTION.getInputStream());
            BufferedOutputStream outputfile = new BufferedOutputStream(new FileOutputStream("FTPfilestored.txt"));

            byte[] buffer = new byte[1024];
            int byteread = 0;
            while((byteread = input.read(buffer)) != -1){
                outputfile.write(buffer, 0, byteread);
                outputfile.flush();
            }
            System.out.println("File received successfully....!");
            STREAMWRITE.close();
            input.close();
            outputfile.close();
            FTPCONNECTION.close();
        }
        catch(Exception e) { System.out.println(e.toString()); }
    }
}
