package com.practise.networking.basics;
import java.net.InetAddress;
// jpcap linkage libraries are absent so its not possible to execute this program on this platform.
public class PacketCaptureJPCAP {
    public static void main(String[] args){
        try{
            InetAddress address = InetAddress.getByName("192.168.3.1");
            System.out.println(address.isReachable(9000));

        }
        catch(Exception e){System.out.println(e.getMessage());}
    }
}
