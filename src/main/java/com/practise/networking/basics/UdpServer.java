package com.practise.networking.basics;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class UdpServer {
    public static void main(String[] args) {
        try {
            // socket connection
            DatagramSocket CONNECTION = new DatagramSocket(9090);
            // make a  buffer
            byte[] data = new byte[1024];
            // make a receive packet
            DatagramPacket packet = new DatagramPacket(data, data.length);
            boolean iter = true;
            while(iter) {
                CONNECTION.receive(packet);
                byte[] receivedbytes = packet.getData();
                String receivedmessage = new String(receivedbytes);

                System.out.println("message received from : "
                        + packet.getAddress().toString()
                        + " sender's name : "
                        + packet.getAddress().getHostName() + "\n"
                        + receivedmessage + "\n");
                String check = new String(packet.getData());
                if(check.strip().substring(0, 3).equals("end"))
                    iter = false;
            }
            System.out.println("Connection will be closed now....");
            CONNECTION.close();
        }
        catch(Exception e) { System.out.println(e.getMessage()); }
    }
}
