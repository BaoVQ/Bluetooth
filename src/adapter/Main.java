package adapter;

import org.bluez.exceptions.BluezFailedException;
import org.bluez.exceptions.BluezNotReadyException;

public class Main {
    public static void main(String[] args) throws BluezFailedException, BluezNotReadyException {
        Adapter adapter = new Adapter();
        adapter.StartDiscovery();
    }
}
