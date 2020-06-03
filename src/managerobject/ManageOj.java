package managerobject;

import adapter.Device;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.ObjectManager;
import org.freedesktop.dbus.types.Variant;

import java.util.Map;

public class ManageOj implements ObjectManager {

    public static String BUS_NAME = "org.bluez";
    public static String BUS_PATH = "/";

    public DBusPath path =null;

    public Map<DBusPath, Map<String, Map<String, Variant<?>>>> GetManagedObjects() {
        try {
            //System.out.println("test");
            DBusConnection connection = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
            ObjectManager objectManager = connection.getRemoteObject(BUS_NAME, BUS_PATH, ObjectManager.class);
            //System.out.println(objectManager.GetManagedObjects());
            Map<DBusPath, Map<String, Map<String, Variant<?>>>> tests = objectManager.GetManagedObjects();
            for (Map.Entry<DBusPath, Map<String, Map<String, Variant<?>>>> test : tests.entrySet()) {
                Map<String, Map<String, Variant<?>>> test2s = test.getValue();
                if (test.getKey().getPath().contains("/org/bluez/hci0/dev_34_8A_7B_1C_87_05")) {
                    path = test.getKey();
                    System.out.println("Path: " + path);
                    Device device = new Device(path);
                   // System.out.println(device.getName());
                    //device.Pair();
                    //UIAutomater uiAutomater = new UIAutomater();
                    //uiAutomater.confirmPair();
                    //device.setTrusted(true);
                   // System.out.println("trust "+device.isTrusted());
                    //device.Connect();
                    //System.out.println("connect "+device.isConnected());

                }
            }
        } catch (DBusException e) {
            e.printStackTrace();
        }

        return null;
    }





    public Map<DBusPath, Map<String, Map<String, Variant<?>>>> unPair() {
        try {
            DBusConnection connection = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
            ObjectManager objectManager = connection.getRemoteObject(BUS_NAME, BUS_PATH, ObjectManager.class);
            DBusPath path;
            Map<DBusPath, Map<String, Map<String, Variant<?>>>> tests = objectManager.GetManagedObjects();
            for (Map.Entry<DBusPath, Map<String, Map<String, Variant<?>>>> test : tests.entrySet()) {
                Map<String, Map<String, Variant<?>>> test2s = test.getValue();
                if (test.getKey().getPath().contains("/org/bluez/hci0/dev_34_8A_7B_1C_87_05")) {
                    path = test.getKey();
                    System.out.println("Path: " + path);
                    Device device = new Device(path);
                    //System.out.println(device.getName());
                    device.CancelPairing();
                    System.out.println("Pairing Status: "+device.isPaired());
                }
            }
        } catch (DBusException e) {
            e.printStackTrace();
        }

        return null;
    }

/*
    public void unPair() throws BluezDoesNotExistException, BluezFailedException {
        DBusPath path = new DBusPath("/org/bluez/hci0/dev_74_9E_F5_13_00_D0");
        System.out.println("cancel Pairing");
        Device device = new Device(path);
        System.out.println(path.getPath());
        device.CancelPairing();
        System.out.println("pair status: "+device.isPaired());
    }
*/

    public boolean isRemote() {
        return false;
    }

    public String getObjectPath() {
        return null;
    }


}
