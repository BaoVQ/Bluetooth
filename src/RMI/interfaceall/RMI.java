package RMI.interfaceall;

import org.bluez.exceptions.*;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI extends Remote {
    void turnOnBluetooth() throws IOException, InterruptedException;
    void turnOffBluetooth() throws IOException, InterruptedException;
    void startProfile() throws IOException, InterruptedException;
    void registerAgent() throws RemoteException;
    void unregisterAgent() throws RemoteException;
    void pair() throws RemoteException, BluezFailedException, BluezNotReadyException, BluezInvalidArgumentsException, BluezAuthenticationRejectedException, BluezAuthenticationCanceledException, BluezConnectionAttemptFailedException, BluezAlreadyExistsException, BluezAuthenticationFailedException, BluezAuthenticationTimeoutException;
    void pairConnect() throws RemoteException, BluezFailedException, BluezNotReadyException, BluezAlreadyConnectedException, BluezInProgressException;
    void removeDevice() throws RemoteException, BluezDoesNotExistException, BluezFailedException, BluezInvalidArgumentsException;
    void sendFile() throws RemoteException, BluezFailedException, BluezInvalidArgumentsException, InterruptedException;
    void mediaPlayer() throws RemoteException;
    void cancelPairing() throws RemoteException, BluezDoesNotExistException, BluezFailedException;
}
