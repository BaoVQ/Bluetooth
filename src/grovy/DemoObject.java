package grovy;

import org.bluez.exceptions.*;

import java.io.IOException;

public interface DemoObject {
    public void printHello();

    public int add(int a, int b);

    public void turnOnBluetooth() throws IOException, InterruptedException, BluezFailedException, BluezNotReadyException, BluezDoesNotExistException, BluezInvalidArgumentsException, BluezAuthenticationRejectedException, BluezAuthenticationCanceledException, BluezConnectionAttemptFailedException, BluezAlreadyExistsException, BluezAuthenticationFailedException, BluezAuthenticationTimeoutException, BluezAlreadyConnectedException, BluezInProgressException;
}
