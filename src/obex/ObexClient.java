package obex;

import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.types.Variant;

import java.util.Map;

@DBusInterfaceName(value = "org.bluez.obex.Client1")

public interface ObexClient extends DBusInterface {
    DBusPath CreateSession(String destination, Map<String, Variant<?>> args);

    void RemoveSession(DBusPath session);
}
