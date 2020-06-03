package RMI.client;

import RMI.interfaceall.RMI;
import org.bluez.exceptions.*;
import uiautomater.UIAutomater;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.security.spec.ECField;
import java.util.Scanner;

public class Control {
    public static void main(String[] args) throws Exception {
        RMI rmi = null;
        try {
            rmi = (RMI) Naming.lookup("rmi://192.168.0.113:1900/Server");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Lua chon lenh: ");
        System.out.println("1 Bat bluetooth");
        System.out.println("2 Tat Bluetooth");
        System.out.println("3 Start profile");
        System.out.println("4 Register Agent");
        System.out.println("5 Unregister Agent");
        System.out.println("6 Pair");
        System.out.println("7 Removie Device");
        System.out.println("8 Send File ");
        System.out.println("9 Media Player ");
        System.out.println("10 Cancel Pairing");
        System.out.println("11 Exit Program ");
        while (true){
            int choice;
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            switch (choice){
                case 1: {
                    rmi.turnOnBluetooth();
                    break;
                }
                case 2: {
                    rmi.turnOffBluetooth();
                    break;
                }
                case 3: {
                    rmi.startProfile();
                    break;
                }
                case 4:{
                    rmi.registerAgent();
                    break;
                }
                case 5:{
                    rmi.unregisterAgent();
                    break;
                }
                case 6:{

                    rmi.pair();
/*
                    try{
                        final RMI finalRmi = rmi;
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    finalRmi.pair();
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                } catch (BluezFailedException e) {
                                    e.printStackTrace();
                                } catch (BluezNotReadyException e) {
                                    e.printStackTrace();
                                } catch (BluezInvalidArgumentsException e) {
                                    e.printStackTrace();
                                } catch (BluezAuthenticationRejectedException e) {
                                    e.printStackTrace();
                                } catch (BluezAuthenticationCanceledException e) {
                                    e.printStackTrace();
                                } catch (BluezConnectionAttemptFailedException e) {
                                    e.printStackTrace();
                                } catch (BluezAlreadyExistsException e) {
                                    e.printStackTrace();
                                } catch (BluezAuthenticationFailedException e) {
                                    e.printStackTrace();
                                } catch (BluezAuthenticationTimeoutException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                    catch (Exception e){

                    }

*/


                    Thread.sleep(2000);
                    System.out.println("Sleep");

                    try{
                        //**************thuc hien sau****************

                        //UIAutomater uiAutomater = new UIAutomater();
                        //uiAutomater.confirmPair();


                        System.out.println("start pair");

                        rmi.pairConnect();
                        break;
                    }
                    catch (Exception e){

                    }

                }
                case 7: {
                    rmi.removeDevice();
                    break;
                }
                case 8: {
                    try {
                        rmi.sendFile();
                        break;
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
                case 9: {
                    rmi.mediaPlayer();
                    break;
                }
                case 10:{
                    rmi.cancelPairing();
                    break;
                }
                case 11: {
                    return;
                }
                default: {
                    System.out.println("wrong input");
                }
            }

        }
    }
}
