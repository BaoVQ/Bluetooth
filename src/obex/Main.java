package obex;

import org.bluez.exceptions.BluezFailedException;
import org.bluez.exceptions.BluezInvalidArgumentsException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BluezFailedException, BluezInvalidArgumentsException {

        Scanner scanner = new Scanner(System.in);
        String cmd = "";

        Client.addPropertiesHandler();
        while (!cmd.equals("exit")) {

            System.out.println("Enter command: ");
            cmd = scanner.nextLine();
            if (cmd.equals("send")) {
                Client.setReadyToSend();
                System.out.println("Enter device address: ");
                cmd = scanner.nextLine();
                Client.creteSession(cmd);
                System.out.println("Enter source file path: ");
                cmd = scanner.nextLine();
                Client.sendFile(cmd);
            } else if (cmd.equals("received")) {
                Client.setReadyToSend();
                System.out.println("Now send file from device and wait for respond");
//                    Thread.sleep(10000);
//                    System.out.println("After 10s. File is " + (BluetoothObexUtil.isSendCompleted() ? "received" : "not received"));
            }
        }

    }
}
