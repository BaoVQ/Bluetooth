package obex;

import org.bluez.exceptions.BluezFailedException;
import org.bluez.exceptions.BluezInvalidArgumentsException;
import org.bluez.exceptions.BluezNotAuthorizedException;
import org.bluez.obex.Client1;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusSigHandler;
import org.freedesktop.dbus.types.Variant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Client implements Client1 {
    private static int NO_SEND = -1;
    private static int READY_TO_SEND = 0;
    private static int SEND_COMPLETE = 1;

    private static ObexClient obexClient;
    private static DBusPath session;
    private static ObjectPush objectPush;

    public static String OBEX_PATH = "/org/bluez/obex";
    private static int stateOfSend = NO_SEND;
    private static boolean isSending = false;
    private static boolean oppFlag;

    public static DBusPath creteSession(String destAddr) throws BluezFailedException, BluezInvalidArgumentsException {
        obexClient = getObexClient();
        Map<String, Variant<?>> session_args = new HashMap<String, Variant<?>>();
        Variant<String> val = new Variant<String>("opp");
        session_args.put("Target", val);
        session = obexClient.CreateSession(destAddr, session_args);
        System.out.println("Session path: " + session.getPath());
        return session;
    }

    public DBusPath CreateSession(String s, Map<String, Variant<?>> map) throws BluezInvalidArgumentsException, BluezFailedException {
        try {
            DBusConnection connection = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION);
            obexClient = connection.getRemoteObject("org.bluez.obex",OBEX_PATH,ObexClient.class);
            session = obexClient.CreateSession(s,map);
            System.out.println("Session Path: "+session.getPath());
            return session;
        } catch (DBusException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void RemoveSession(DBusPath dBusPath) throws BluezInvalidArgumentsException, BluezNotAuthorizedException {
        obexClient = getObexClient();
        obexClient.RemoveSession(session);
    }


    public static void sendFile(String srcFile) {

        objectPush = getObjectPush();
        CustomTuple<String, Map<String, Variant<?>>> sendFileInfo = objectPush.SendFile(srcFile);
    }

    public static ObjectPush getObjectPush() {

        try {
            DBusConnection dbus = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION);
            return dbus.getRemoteObject("org.bluez.obex", session.getPath(), ObjectPush.class);
        } catch (DBusException e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean isRemote() {
        return false;
    }

    public String getObjectPath() {
        return null;
    }

    public static ObexClient getObexClient() {

        try {
            DBusConnection dbus = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION);
            return dbus.getRemoteObject("org.bluez.obex", OBEX_PATH, ObexClient.class);
        } catch (DBusException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void setReadyToSend() {

        isSending = false;
        stateOfSend = READY_TO_SEND;
    }

    public static boolean isSendCompleted() {

        return stateOfSend == SEND_COMPLETE;
    }


    public static void addPropertiesHandler() {
        try {
            DBusSigHandler<Property.PropertiesChanged> propertyClassChanged = new DBusSigHandler<Property.PropertiesChanged>() {

                Property p;

                public void handle(Property.PropertiesChanged propObj) {
                    System.out.println("-----------" + propObj.getInterfaceName() + ":");
                    Map<String, Variant<?>> props = propObj.getProperties();
                    for(String k : props.keySet()) {
                        System.out.println(String.format("Props change: %s => %s", k, props.get(k)));
                    }
                    System.out.println("-----------" + propObj.getInterfaceName());

                    if(propObj.getInterfaceName().startsWith("org.bluez.obex.Transfer")){
                        Set<String> keys = props.keySet();
                        for(String k : keys) {
                            String v = props.get(k).toString();


                            if(k.equals("Status") && v.equals("[complete]")) {
                                System.out.println(String.format("[complete]_2 : %d", stateOfSend));
                                System.out.println("File is received");
                                if (stateOfSend == READY_TO_SEND) {
                                    stateOfSend = SEND_COMPLETE;
                                }
                            }
                        }
                    }
                }
            };


            DBusConnection dbus = DBusConnection.getConnection(DBusConnection.DBusBusType.SESSION);
            dbus.addSigHandler(Property.PropertiesChanged.class, propertyClassChanged);
        } catch (DBusException e) {
            e.printStackTrace();
        }
    }

}
