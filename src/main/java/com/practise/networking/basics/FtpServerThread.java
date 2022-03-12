package com.practise.networking.basics;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
public class FtpServerThread extends Thread {
    private Socket FTPCONNECTION;

    FtpServerThread(Socket CONN){
        this.FTPCONNECTION = CONN;
    }

    public void run() {
        try {
            // we have to use InputStreamReader because client is sending a sequence of characters using PrintWriter.
            InputStreamReader READER = new InputStreamReader(FTPCONNECTION.getInputStream());
            BufferedReader READNAME = new BufferedReader(READER);
            String filename = READNAME.readLine();
            System.out.println("Requested file is " + filename);

            File f = new File(filename);

            BufferedOutputStream STREAMWRITE = new BufferedOutputStream(FTPCONNECTION.getOutputStream());
            if(f.exists()){
                // send code 1
                //FTPCONNECTION.getOutputStream().write(1);

                FileInputStream READFILE = new FileInputStream(f);
                BufferedInputStream IN = new BufferedInputStream(READFILE);

                // byte[] bytefile = READFILE.readAllBytes();
                byte[] buffer = new byte[1024];
                int bytesread = 0;
                while((bytesread = IN.read(buffer)) != -1){
                    STREAMWRITE.write(buffer, 0, bytesread);
                    STREAMWRITE.flush();
                }
                System.out.println("file transfer successfull...!");
                READFILE.close();
                IN.close();
                STREAMWRITE.close();
            }
            else{
                FTPCONNECTION.getOutputStream().write(0);
            }

            READNAME.close();
            READER.close();
            FTPCONNECTION.close();
        }
        catch(Exception e) { System.out.println(e.toString()); }
    }
}