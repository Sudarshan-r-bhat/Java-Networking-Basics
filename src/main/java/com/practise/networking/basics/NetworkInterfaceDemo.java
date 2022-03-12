package com.practise.networking.basics;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

public class NetworkInterfaceDemo {  // NetworkInterface doesn't have a accessible constructor
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("localhost");
            //getByInetAddress()
            //getByName()
            //getNetworkInterfaces()
            //NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);
            //NetworkInterface networkInterface = NetworkInterface.getByName("eth3");
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while(networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if(networkInterface != null) {
                    System.out.println("NIC name: " + networkInterface.getName());
                    System.out.println("NIC display name: " + networkInterface.getDisplayName());
                    System.out.println("NIC get hardware address (MAC) " + getString(networkInterface.getHardwareAddress()));
                    System.out.println("MTU: " + networkInterface.getMTU());
                    System.out.println("Index: " + networkInterface.getIndex());
                    NetworkInterface parentInterface = networkInterface.getParent();
                    if(parentInterface != null) {
                        System.out.println("Parent interface: " + parentInterface.getDisplayName());
                    }
                    else {
                        System.out.println("No parent interface!");
                    }
                    System.out.println("Is loopback?  " + networkInterface.isLoopback());
                    System.out.println("Is up " + networkInterface.isUp());
                    System.out.println("Is virtual " + networkInterface.isVirtual());
                    System.out.println("Support multicast?  " + networkInterface.supportsMulticast());
                    //Enumeration<InetAddress> nifAddresses = networkInterface.getInetAddresses();
                    List<InterfaceAddress> list = networkInterface.getInterfaceAddresses();
                    for(int i=0; i<list.size(); i++) {
                        System.out.println("Ip address: " + (list.get(i)).getAddress().getHostAddress());
                    }
                    System.out.println("");
                    System.out.println("__________________________________________________");
                    System.out.println("");
                }
                else {
                    System.out.println("Interface not foud");
                }
            }
        }
        catch(Exception e) { System.out.println(e.toString()); }
    }
    public static String getString(byte[] address) {
        if(address == null)
            return null;
        StringBuilder sb = new StringBuilder(18);
        for(byte b : address) {
            sb.append(String.format("%02x", b));
            sb.append(":");
        }
        return sb.toString();
    }
}
