package test;


import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;

@DBusInterfaceName("org.bluez.AgentManager1")
public interface AgentManager1 extends DBusInterface {

    public void RegisterAgent(DBusPath agent, String capability);

    public void UnregisterAgent(DBusPath agent);

    public void RequestDefaultAgent(DBusPath agent);
}
