package obex;

import org.bluez.exceptions.BluezFailedException;
import org.bluez.exceptions.BluezNotSupportedException;
import org.bluez.obex.Session1;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.types.Variant;

import java.util.Map;

public class Session implements Session1 {
    public String GetCapabilities() throws BluezNotSupportedException, BluezFailedException {
        try {
            DBusConnection connection = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);

        } catch (DBusException e) {
            e.printStackTrace();
        }
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
