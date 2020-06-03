package RMI.server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Enumeration;


public class Server {
    public static void main(String[] args) throws RemoteException, SocketException {
        String addr = "";
        Enumeration<NetworkInterface> netif = NetworkInterface
                .getNetworkInterfaces();
        for (; netif.hasMoreElements();) {

            NetworkInterface n = netif.nextElement();
            Enumeration<InetAddress> a = n.getInetAddresses();
            for (; a.hasMoreElements();) {

                InetAddress i = a.nextElement();
                System.out.println(i.getHostAddress());
                if (i.getHostAddress().contains("192")) {

                    addr = i.getHostAddress();
                    break;
                }
            }
        }
        System.setProperty("java.rmi.server.hostname", addr);
        Process process = new Process();

        try {
            //System.out.println(InetAddress.getLocalHost().getHostAddress());
            // get local IP
            // ServerSocket ss = new ServerSocket(1020,
            // ).getInetAddress().getHostAddress();
            // System.setProperty("java.rmi.server.hostname", "192.168.1.78");
            LocateRegistry.createRegistry(1900);

            //System.out.println(System.getProperty("java.rmi.server.hostname"));
            Naming.rebind("rmi://192.168.0.113:1900/Server", process);
            System.out.println("start servers");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
//		System.setProperty("java.security.policy", "/root/BTSimulator/all.policy");
//		System.setSecurityManager(new SecurityManager());
//		System.out.println("IP: " + addr);
//		LocateRegistry.createRegistry(1900);
//		Process process = new Process();
//		Naming.rebind("rmi://" + addr + ":1900/Server", process);
//		System.out.println(System.getProperty("java.rmi.server.hostname"));
//		System.out.println("Binding...");

    }
}
