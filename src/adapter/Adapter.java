package adapter;

import managerobject.ManageOj;
import org.bluez.Adapter1;
import org.bluez.exceptions.*;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.types.Variant;
import test.AgentUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Adapter implements Adapter1 {
    public static String BUS_NAME = "org.bluez";
    public static String BUS_PATH = "/org/bluez/hci0";

    DBusPath path = new DBusPath("/org/bluez/hci0/dev_34_8A_7B_1C_87_05");
/*
    public Adapter (DBusPath path){
        this.path = path;
    }
*/
    public void StartDiscovery() throws BluezNotReadyException, BluezFailedException {
        try {
            DBusConnection connection = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
            Adapter1 adapter1 = connection.getRemoteObject(BUS_NAME,BUS_PATH, Adapter1.class);
            adapter1.StartDiscovery();

//            System.out.println(adapter1.GetAll(BUS_PATH));

            //System.out.println("DBusPath: "+path.getPath());

            AgentUtils.registerAgent(1);

            System.out.println("-----------start-------------");
            TimeUnit.SECONDS.sleep(8);
            adapter1.StopDiscovery();
            System.out.println("-------------Stop------------");
            ManageOj manageOj = new ManageOj();
            manageOj.GetManagedObjects();
            //path = manageOj.path;
            //System.out.println("getPath: "+path.getPath());
        } catch (DBusException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pair() throws BluezInvalidArgumentsException, BluezConnectionAttemptFailedException, BluezAuthenticationCanceledException, BluezAuthenticationRejectedException, BluezAlreadyExistsException, BluezAuthenticationFailedException, BluezAuthenticationTimeoutException, BluezFailedException {
        System.out.println("pair 1");
        Device device = new Device(path);
        device.Pair();
    }

    public void PairConnect() throws BluezFailedException, BluezAlreadyConnectedException, BluezNotReadyException, BluezInProgressException {
        System.out.println("pair 2");
        System.out.println("path pair2: "+path.getPath());
        Device device = new Device(path);
        device.setTrusted(true);
        device.Connect();
    }

    public void StopDiscovery() throws BluezNotReadyException, BluezFailedException, BluezNotAuthorizedException {

    }

    public void RemoveDevice(DBusPath dBusPath) throws BluezInvalidArgumentsException, BluezFailedException {
        try {
            DBusConnection connection = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
            Adapter1 adapter1 = connection.getRemoteObject(BUS_NAME, BUS_PATH, Adapter1.class);
            adapter1.RemoveDevice(dBusPath);
        } catch (DBusException e) {
            e.printStackTrace();
        }
    }

    public void SetDiscoveryFilter(Map<String, Variant<?>> map) throws BluezNotReadyException, BluezNotSupportedException, BluezFailedException {

    }

    public String[] GetDiscoveryFilters() {
        return new String[0];
    }

    public DBusPath ConnectDevice(Map<String, Variant<?>> map) throws BluezInvalidArgumentsException, BluezAlreadyExistsException, BluezNotSupportedException, BluezNotReadyException, BluezFailedException {
        return null;
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
