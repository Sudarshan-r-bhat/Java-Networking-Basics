package com.practise.networking.basics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
/*
    here InetAddress, pinging, portscanning,are demonstrated
*/

public class InetAddressDemo
{// InetAddress doesn't have a constructor
    public static void main(String[] args)
    {
        try
        {
            String url = "campuscommune.tcs.com";
            InetAddress ipaddress = InetAddress.getByName(url);
            System.out.println(ipaddress);
            // pinging timeout = 10000
            System.out.println(ipaddress.isReachable(1000)); // permission will be denied in windows.

            Process P = Runtime.getRuntime().exec("ping " + ipaddress.getHostAddress());
            BufferedReader READ = new BufferedReader(new InputStreamReader(P.getInputStream()));

            String line;
            while((line = READ.readLine()) != null)
                System.out.println(line);
        }
        catch(Exception e){e.printStackTrace();}

        for(int i = 130; i < 138; i++)
        {
            try
            {
                Socket CONNECTION = new Socket("127.0.0.1", i);
                System.out.println("the port is open at port -  " + i);
                CONNECTION.close();
            }
            catch(Exception e){System.out.println(" closed at port -  " + i + e.getMessage());}
        }
    }
}
