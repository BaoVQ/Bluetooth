package adapter;

import org.bluez.Device1;
import org.bluez.exceptions.*;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.Properties;
import org.freedesktop.dbus.types.Variant;

import java.util.Map;

public class Device implements Device1 {
    private final String INTERFACE_NAME = "org.bluez.Device1";
    private final String address_prop = "Address";
    private final String name_prop = "Name";
    private final String ispaired_prop = "Paired";
    private final String isconnected_prop = "Connected";
    private final String trusted_prop = "Trusted";
    public static String BUS_NAME = "org.bluez";
    DBusPath path;
    public Device(DBusPath path){
        this.path = path;
    }

    private org.freedesktop.dbus.interfaces.Properties getProperty(){
        try {
            DBusConnection connection = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
            return connection.getRemoteObject(BUS_NAME,path.getPath(), Properties.class);
        } catch (DBusException e) {
            e.printStackTrace();
        }
        return null;
    }

    ///////////////////GET DEVICE///////////////////////
    private Device1 getDevice1() {

        try {
            DBusConnection connection = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
            return connection.getRemoteObject(BUS_NAME,path.getPath(), Device1.class);
        } catch (DBusException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName() {
        return getProperty().Get(INTERFACE_NAME, "Name");
    }

    private Device1 getDeviceInterface() {

        try {
            DBusConnection connection = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
            return connection.getRemoteObject("org.bluez", path.getPath(), Device1.class);
        } catch (DBusException e) {
            e.printStackTrace();
        }

        return null;
    }



    public String getAddress() {
        return getProperty().Get(INTERFACE_NAME, address_prop);

    }

    public boolean isPaired() {
        return getProperty().Get(INTERFACE_NAME, ispaired_prop);

    }

    public boolean isConnected() {
        return getProperty().Get(INTERFACE_NAME, isconnected_prop);

    }

    public boolean isTrusted() {
        return getProperty().Get(INTERFACE_NAME, trusted_prop);

    }

    public void setTrusted(boolean trusted) {

        getProperty().Set(INTERFACE_NAME, trusted_prop, trusted);
    }


    public void Connect() throws BluezNotReadyException, BluezFailedException, BluezInProgressException, BluezAlreadyConnectedException {

        Device1 device1 = getDevice1();
        device1.Connect();
    }

    public void Disconnect() throws BluezNotConnectedException {

    }

    public void ConnectProfile(String s) throws BluezFailedException, BluezInProgressException, BluezInvalidArgumentsException, BluezNotAvailableException, BluezNotReadyException {

    }

    public void DisconnectProfile(String s) throws BluezFailedException, BluezInProgressException, BluezInvalidArgumentsException, BluezNotSupportedException {

    }

    public void Pair() throws BluezInvalidArgumentsException, BluezFailedException, BluezAlreadyExistsException, BluezAuthenticationCanceledException, BluezAuthenticationFailedException, BluezAuthenticationRejectedException, BluezAuthenticationTimeoutException, BluezConnectionAttemptFailedException {

        Device1 device1 = getDevice1();
        device1.Pair();
    }

    public void CancelPairing() throws BluezDoesNotExistException, BluezFailedException {
        Device1 device1 = getDevice1();
        device1.CancelPairing();
    }

    public <A> A Get(String s, String s1) {
        return null;
    }

    public <A> void Set(String s, String s1, A a) {

    }

    public Map<String, Variant<?>> GetAll(String s) {
        return null;
    }

    public boolean isRemote() {
        return false;
    }

    public String getObjectPath() {
        return null;
    }
}
