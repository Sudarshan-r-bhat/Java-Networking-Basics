package com.practise.networking.basics;
import static java.lang.Thread.sleep;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/*
* This can be made more efficient by using BufferedInputStream to read the bytes. In the while loop, and storing the value in the buffer "data".
* then send the packets one by one within the while loop.
*
* */

public class MulticastUdpServer {
    public static void main(String[] args) {
        try {
            // DatagramSocket connection
            MulticastSocket CONNECTION = new MulticastSocket(444);
            // process data
            byte[] data;
            String str0 = "Hello this is a multicast message from the server....";
            data = str0.getBytes();
            // create packet
            InetAddress multicastaddress = InetAddress.getByName("224.0.0.1");

            int retry = 1;
            while(retry < 60) {
                DatagramPacket packet = new DatagramPacket(data, data.length, multicastaddress, 444);
                //CONNECTION.setSoTimeout(3000);
                CONNECTION.send(packet);
                System.out.println("message sent ....");
                retry++;
                sleep(3000);
            }
            CONNECTION.close();
        }
        catch(Exception e) { System.out.println(e.getMessage()); }
    }

}
