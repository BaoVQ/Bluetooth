package grovy;

import org.bluez.exceptions.*;

import java.io.IOException;

public class thucthi implements DemoObject {
    public void printHello() {

    }

    public int add(int a, int b) {
        return 0;
    }

    public void turnOnBluetooth() throws IOException, InterruptedException, BluezFailedException, BluezNotReadyException, BluezDoesNotExistException, BluezInvalidArgumentsException, BluezAuthenticationRejectedException, BluezAuthenticationCanceledException, BluezConnectionAttemptFailedException, BluezAlreadyExistsException, BluezAuthenticationFailedException, BluezAuthenticationTimeoutException, BluezAlreadyConnectedException, BluezInProgressException {
        System.out.println("start");
        Enforcement.turnOnBluetooth();
    }
}
