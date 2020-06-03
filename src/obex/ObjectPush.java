package obex;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.types.Variant;

import java.util.Map;

@DBusInterfaceName(value = "org.bluez.obex.ObjectPush1")

public interface ObjectPush extends DBusInterface {
    CustomTuple<String, Map<String, Variant<?>>> SendFile(String sourcefile);


}
