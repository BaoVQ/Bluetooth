package test;

import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.types.UInt16;
import org.freedesktop.dbus.types.UInt32;

@DBusInterfaceName(value = "org.bluez.Agent1")
public class MyAgent implements DBusInterface {


    public static final String AgentPath = "/my_agent/agent";


    public MyAgent() {
    }

    public void Release() {

        System.out.println("Release");
    }

    public String RequestPinCode(DBusPath device) {

        System.out.println("RequestPinCode for device: " + device.getPath());
        String pin = "1234"; //some random pin code
        return pin;
    }

    public void DisplayPincode(DBusPath device, String pincode) {

        System.out.println("DisplayPincode");
        System.out.println("Device: " + device + " ---- Pincode: " + pincode);
    }

    public UInt32 RequestPasskey(DBusPath device) {

        System.out.println("RequestPasskey");
        int passkey = 1111;
        System.out.println("Passkey: " + passkey);
        return new UInt32(passkey);
    }

    public void DisplayPasskey(DBusPath device, UInt32 passkey, UInt16 entered) {

        System.out.println("DisplayPasskey");
        System.out.println("Device: " + device);
        System.out.println("Entered: " + entered);
        System.out.println("Passkey: " + passkey);

    }

    public void RequestConfirmation(DBusPath device, UInt32 passkey) {

        System.out.println("RequestConfirmation");
        System.out.println("Passkey: " + passkey);
        System.out.println("Device: " + device);
//        BTDevice btDevice = new BTDevice(device);

//        btDevice.setTrusted(true);

        return;
    }

    public void RequestAuthorization(DBusPath device) {

        System.out.println("RequestAuthorization");
        System.out.println("Device: " + device);
        return;

    }

    public void AuthorizeService(DBusPath device) {

        System.out.println("AuthorizeService");
        System.out.println("Device: " + device);


    }

    public void Cancel() {

        System.out.println("Cancel");

    }


    public boolean isRemote() {
        return false;
    }

    public String getObjectPath() {
        return null;
    }


}
