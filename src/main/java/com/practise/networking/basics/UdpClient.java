package com.practise.networking.basics;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class UdpClient {
    public static void main(String[] args) {
        try {
            // socket connection
            DatagramSocket CONNECTION = new DatagramSocket(0);
            // process data
            byte[] data;
            String str0 = "CLIENT: hello server ";
            data = str0.getBytes();
            // create packet
            InetAddress clientip = InetAddress.getLocalHost();

            int retry = 1;
            while(retry < 6) {
                // we shall signal the server to close connection after sending all our messages.
                if(retry == 5) {
                    str0 = "end";
                    data = str0.getBytes();
                }
                DatagramPacket packet = new DatagramPacket(data, data.length, clientip, 9090);
                CONNECTION.setSoTimeout(3000);
                CONNECTION.send(packet);
                System.out.println("message sent is ...." + new String(packet.getData()));
                retry++;
            }
            CONNECTION.close();
        }
        catch(Exception e) { System.out.println(e.getMessage()); }
    }
}
