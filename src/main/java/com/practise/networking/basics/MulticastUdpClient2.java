package com.practise.networking.basics;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastUdpClient2 {
    public static void main(String[] args) {
        try {
            // socket connection
            MulticastSocket MULTICASTCONNECTION = new MulticastSocket(444);
            MULTICASTCONNECTION.joinGroup(InetAddress.getByName("224.0.0.1"));
            // make a  buffer
            byte[] data = new byte[1024];
            // make a receive packet
            DatagramPacket packet = new DatagramPacket(data, data.length);


            boolean iter = true;
            while(iter) {
                MULTICASTCONNECTION.receive(packet);
                byte[] receivedbytes = packet.getData();
                String receivedmessage = new String(receivedbytes, 0,packet.getLength());

                System.out.println("message received from : "
                        + packet.getAddress().toString()
                        + " sender's name : "
                        + packet.getAddress().getHostName() + "\n"
                        + receivedmessage + "\n");
                iter = false;
            }
            System.out.println("Connection will be closed now....");
            MULTICASTCONNECTION.close();
        }
        catch(Exception e) { System.out.println(e.getMessage()); }
    }
}
