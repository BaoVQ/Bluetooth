package RMI.server;

import RMI.interfaceall.RMI;
import adapter.Adapter;
import agentmanager.AgentManager;
import managerobject.ManageOj;
import mediaplayer.BTMediaPlayer;
import mediaplayer.Utils;
import obex.Client;
import org.bluez.exceptions.*;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.exceptions.DBusException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Process extends UnicastRemoteObject implements RMI {
    protected Process() throws RemoteException {
    }

    ProcessBuilder processBuilder = new ProcessBuilder();

    public void turnOnBluetooth() throws IOException, InterruptedException {
        System.out.println("turnon Bluetooth");
        String[] terminal = new String[]{"hciconfig", "hci0", "up"};
        processBuilder.processBuider(terminal);
    }

    public void turnOffBluetooth() throws IOException, InterruptedException {
        String[] terminal = new String[]{"hciconfig", "hci0", "down"};
        processBuilder.processBuider(terminal);
    }

    public void startProfile() throws IOException, InterruptedException {
        System.out.println("start Profile");
        String[] terminal = new String[]{"killall", "bluetoothd"};
        processBuilder.processBuider(terminal);
        terminal = new String[]{"hciconfig", "hci0", "up"};
        processBuilder.processBuider(terminal);
        terminal = new String[]{"bluetoothd", "-C", "-p", "a2dp,avrcp,opp"};
        processBuilder.processBuider1(terminal);
        terminal = new String[]{"hciconfig", "hci0", "up"};
        processBuilder.processBuider(terminal);
        System.out.println("OK");
    }

    public void registerAgent() throws RemoteException {
        System.out.println("Register Agent");
        System.out.println("0 DisplayOnly");
        System.out.println("1 DisplayYesNo");
        System.out.println("2 KeyboardOnly");
        System.out.println("3 NoInputNoOutput");
        System.out.println("4 KeyboardDisplay");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter capacity number: ");
        String number = scanner.nextLine();
        AgentManager.registerAgent(Integer.parseInt(number));
        System.out.println("OK");
    }

    public void unregisterAgent() throws RemoteException {
        System.out.println("Unregister Agent");
        AgentManager.unRegisterAgent();
        System.out.println("OK");
    }

    public void pair() throws RemoteException, BluezFailedException, BluezNotReadyException, BluezInvalidArgumentsException, BluezAuthenticationRejectedException, BluezAuthenticationCanceledException, BluezConnectionAttemptFailedException, BluezAlreadyExistsException, BluezAuthenticationFailedException, BluezAuthenticationTimeoutException {
        System.out.println("Pair");
        Adapter adapter = new Adapter();
        adapter.StartDiscovery();
        adapter.pair();
        System.out.println("OK Pair");
    }

    public void pairConnect() throws RemoteException, BluezFailedException, BluezNotReadyException, BluezAlreadyConnectedException, BluezInProgressException {
        Adapter adapter = new Adapter();
        //adapter.StartDiscovery();
        adapter.PairConnect();
    }

    public void removeDevice() throws RemoteException, BluezDoesNotExistException, BluezFailedException, BluezInvalidArgumentsException {
        System.out.println("Remove Device");
        try {
            Adapter adapter = new Adapter();
            DBusPath path = new DBusPath("/org/bluez/hci0/dev_34_8A_7B_1C_87_05");
            adapter.RemoveDevice(path);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("False removie devices");
            e.printStackTrace();
        }

    }

    public void sendFile() throws RemoteException, BluezFailedException, BluezInvalidArgumentsException, InterruptedException {
        //neu gap loi co the do chua bat cau hinh bluetooth
        //lenh terminal
        //obexd -p opp,filesystem,bluetooth -a -d
        //Scanner scanner = new Scanner(System.in);
        //String cmd = "";

        Client.addPropertiesHandler();
        //while (!cmd.equals("exit")) {

        //System.out.println("Enter command: ");
        //cmd = scanner.nextLine();
        //if (cmd.equals("send")) {
        Client.setReadyToSend();
        //System.out.println("Enter device address: ");
        //cmd = scanner.nextLine();
        Client.creteSession("34:8A:7B:1C:87:05");
        //System.out.println("Enter source file path: ");
        //cmd = scanner.nextLine();
        Client.sendFile("/home/pi/abc.txt");
        Thread.sleep(3000);
        //} else if (cmd.equals("received")) {
        //Client.setReadyToSend();
        //System.out.println("Now send file from device and wait for respond");
//                    Thread.sleep(10000);
//                    System.out.println("After 10s. File is " + (BluetoothObexUtil.isSendCompleted() ? "received" : "not received"));
        //}
        //}

        System.out.println("OK");

    }

    public void mediaPlayer() throws RemoteException {
        try {
            BTMediaPlayer bluetoothMediaPlayer = Utils.getMediaPlayer();

            bluetoothMediaPlayer.play();
        } catch (DBusException e) {
            e.printStackTrace();
        }
        System.out.println("OK");
    }

    public void cancelPairing() throws RemoteException, BluezDoesNotExistException, BluezFailedException {
        ManageOj manageOj = new ManageOj();
        manageOj.unPair();
        System.out.println("OK");
    }
}
