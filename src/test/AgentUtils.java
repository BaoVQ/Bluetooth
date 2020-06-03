package test;


import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;

public class AgentUtils {

    public static final int DisplayOnly = 0;
    public static final int DisplayYesNo = 1;
    public static final int KeyboardOnly = 2;
    public static final int NoInputNoOutput = 3;
    public static final int KeyboardDisplay = 4;

    public static String BUS_NAME = "org.bluez";
    public static String BUS_PATH = "/org/bluez";

    private static final String[] CAPABILITY = {
            "DisplayOnly", "DisplayYesNo", "KeyboardOnly", "NoInputNoOutput", "KeyboardDisplay"
    };

    public static MyAgent myAgent = new MyAgent();

    public static boolean isRegistered = false;


    public static void registerAgent(int capa) {

        if (!isRegistered) {

            DBusConnection bus;
            try {
                bus = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
                AgentManager1 agentManager1 = bus.getRemoteObject(BUS_NAME, BUS_PATH, AgentManager1.class);
                bus.exportObject(myAgent.AgentPath, myAgent);
                agentManager1.RegisterAgent(new DBusPath(myAgent.AgentPath), CAPABILITY[capa]);
                agentManager1.RequestDefaultAgent(new DBusPath(myAgent.AgentPath));
                System.out.println("Register agent : " + CAPABILITY[capa]);

                isRegistered = true;
            } catch (DBusException e) {

                e.printStackTrace();
            }
        }
    }

    public static void unRegisterAgent() {

        if (isRegistered) {

            DBusConnection bus;
            try {
                bus = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
                AgentManager1 agentManager1 = bus.getRemoteObject(BUS_NAME, BUS_PATH, AgentManager1.class);
                agentManager1.UnregisterAgent(new DBusPath(MyAgent.AgentPath));
                bus.unExportObject(MyAgent.AgentPath);
                System.out.println("Unregistered agent");
                isRegistered = false;
            } catch (DBusException e) {

                e.printStackTrace();
            }
        }
    }
}
