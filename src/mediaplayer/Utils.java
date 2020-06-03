package mediaplayer;

import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.types.Variant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
    public static List<BTMediaPlayer> getManagedMediaPlayer() throws DBusException {
        DBusConnection conn = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
        ObjectMn om = conn.getRemoteObject("org.bluez", "/", ObjectMn.class);
        List<BTMediaPlayer> mpList = new ArrayList<BTMediaPlayer>();
        for (Map.Entry<DBusPath, Map<String, Map<String, Variant<?>>>> entry : om.GetManagedObjects().entrySet()) {
            DBusPath path = entry.getKey();
            Map<String, Map<String, Variant<?>>> interfaces = entry.getValue();
            if (interfaces.keySet().contains("org.bluez.MediaPlayer1")) {
                mpList.add(new BTMediaPlayer(path));
            }
        }
        return mpList;
    }

    public static BTMediaPlayer getMediaPlayer() throws DBusException {
        DBusConnection conn = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
        ObjectMn om = conn.getRemoteObject("org.bluez", "/", ObjectMn.class);
        for (Map.Entry<DBusPath, Map<String, Map<String, Variant<?>>>> entry : om.GetManagedObjects().entrySet()) {
            DBusPath path = entry.getKey();
            System.out.println("Path: " + path);
            Map<String, Map<String, Variant<?>>> interfaces = entry.getValue();
            System.out.println("Interfaces: " + interfaces);

            if (interfaces.keySet().contains("org.bluez.MediaPlayer1")) {
                return new BTMediaPlayer(path);
            }
        }
        return null;
    }
}
