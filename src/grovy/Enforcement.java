package grovy;

import RMI.interfaceall.RMI;
import org.bluez.exceptions.*;
import sample.Controller;
import uiautomater.UIAutomater;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Enforcement {
    public static void printString(String s) {

        System.out.println(s);
        Controller.addLog(s);
    }

    public static int add(int a, int b) {

        return a + b;
    }

    public static void turnOnBluetooth() throws IOException, InterruptedException, BluezFailedException, BluezNotReadyException, BluezDoesNotExistException, BluezInvalidArgumentsException, BluezConnectionAttemptFailedException, BluezAuthenticationCanceledException, BluezAuthenticationRejectedException, BluezAlreadyExistsException, BluezAuthenticationFailedException, BluezAuthenticationTimeoutException, BluezAlreadyConnectedException, BluezInProgressException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                boolean check =false;
                RMI rmi = null;
                System.out.print("---Connect to Server---");
                try {
                    rmi = (RMI) Naming.lookup("rmi://192.168.1.78:1900/Server");
                    System.out.print("Connect to Server: OK");
                } catch (Exception e) {
                    System.out.print("Connect to Server: False");
                    //e.printStackTrace();
                }
                UIAutomater uiAutomater = new UIAutomater();
                System.out.print("---TurnOn Bluetooth Pi--- ");
                try {
                    rmi.turnOnBluetooth();
                    System.out.print("TurnOn Bluetooth: OK");
                } catch (Exception e) {
                    System.out.print("TurnOn Bluetooth: False");
                }
                System.out.print("---remove device---");
                try {
                    rmi.removeDevice();
                    System.out.print("remove device: OK");
                } catch (Exception e) {
                    System.out.print("-remove device: False---");
                    //e.printStackTrace();
                }
                System.out.print("---TurnOn Bluetooth Device---");

                try {
                    uiAutomater.turnOnBluetooth();
                    System.out.print("OK");
                } catch (Exception e) {
                    System.out.print("False");
                }

                System.out.print("---Start Profile ---");
                try {
                    rmi.startProfile();
                    System.out.print("OK");
                } catch (Exception e) {
                    System.out.print("False");
                }

                // rmi.removeDevice();

                //rmi.pair();
                System.out.print("pairing");
                final RMI finalRmi = rmi;


                new Thread(new Runnable() {
                    public void run() {
                        try {
                            System.out.print("find Device...");
                            finalRmi.pair();
                        } catch (RemoteException e) {
                            System.out.print("False");
                            //e.printStackTrace();
                        } catch (BluezFailedException e) {
                            ///e.printStackTrace();
                        } catch (BluezNotReadyException e) {
                            //e.printStackTrace();
                        } catch (BluezInvalidArgumentsException e) {
                            //e.printStackTrace();
                        } catch (BluezAuthenticationRejectedException e) {
                            //e.printStackTrace();
                        } catch (BluezAuthenticationCanceledException e) {
                            //.printStackTrace();
                        } catch (BluezConnectionAttemptFailedException e) {
                            //e.printStackTrace();
                        } catch (BluezAlreadyExistsException e) {
                            //e.printStackTrace();
                        } catch (BluezAuthenticationFailedException e) {
                            //e.printStackTrace();
                        } catch (BluezAuthenticationTimeoutException e) {
                            //e.printStackTrace();
                        }
                    }
                }).start();


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //
                }
                System.out.print("Sleep");
                //UIAutomater uiAutomater = new UIAutomater();
                System.out.print("---Confirm Pair---");
                try {
                    uiAutomater.confirmPair();
                    System.out.print("OK");
                } catch (Exception e) {
                    System.out.print("False");
                }


                System.out.print("---Trust and connect---");

                try {
                    rmi.pairConnect();
                    System.out.print("OK");
                } catch (Exception e) {
                    System.out.print("False");
                }

                System.out.print("---UI open app music---");
                try {
                    uiAutomater.runOpenAppMucis();
                    System.out.print("OK");
                } catch (Exception e) {
                    System.out.println("False");
                }
                System.out.print("---Play music by Pi---");
                try {
                    rmi.mediaPlayer();
                    System.out.print("OK");
                    check = true;
                } catch (Exception e) {
                    System.out.print("False");
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    //
                }


                System.out.print("---Send File---");
                try {
                    rmi.sendFile();
                    System.out.print("OK");
                } catch (Exception e) {
                    System.out.print("False");
                }

                //uiAutomater.confirmPair();
                System.out.print("---UI turn of bluetooth---");
                try {
                    uiAutomater.turnOffBluetooth();
                    System.out.print("OK");
                } catch (Exception e) {
                    System.out.print("False");
                }

                System.out.print("-------------------FINISH------------------");
                if(check){
                    System.out.print("TEST CASE PASS");
                }
                else {
                    System.out.print("TEST CASE FALSE");
                }
            }
        });
        thread.start();


    }

}
