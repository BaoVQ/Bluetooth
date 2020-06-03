package mediaplayer;

import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.types.Variant;

import java.util.Map;

@DBusInterfaceName(value = "org.freedesktop.DBus.ObjectManager")
public interface ObjectMn extends DBusInterface {
    Map<DBusPath, Map<String, Map<String, Variant<?>>>> GetManagedObjects();

}
