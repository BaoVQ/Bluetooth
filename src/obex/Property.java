package obex;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.Variant;

import java.util.List;
import java.util.Map;

@DBusInterfaceName(value = "org.freedesktop.DBus.Properties")

public interface Property extends DBusInterface {
    public class PropertiesChanged extends DBusSignal {
        String interfaceName;
        public Map<String, Variant<?>> properties;
        List<String> infos;

        public PropertiesChanged(String path, String interfaceName, Map<String, Variant<?>> properties, List<String> info)
                throws DBusException {
            super(path);
            this.interfaceName = interfaceName;
            this.properties = properties;
            this.infos = info;

        }

        public String getInterfaceName() {
            return interfaceName;
        }
        public Map<String, Variant<?>> getProperties() {
            return properties;
        }
    }
}
