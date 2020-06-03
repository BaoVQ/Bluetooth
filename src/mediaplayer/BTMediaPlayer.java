package mediaplayer;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.Properties;

public class BTMediaPlayer {
    DBusPath path;

    private static final String DEFAULT_BUS = "org.bluez";
    public static final String MEDIAPLAYER_INTERFACE = "org.bluez.MediaPlayer1";

    public static final String POSITION = "Position";
    public static final String TRACK = "Track";
    public static final String DEVICE = "Device";
    public static final String STATUS = "Status";

    public BTMediaPlayer(DBusPath path) {
        this.path = path;
    }

    public void play() throws DBusException {
        MediaPlayer mp = getMediaPlayerInterface();
        try {
            mp.Play();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public void pause() throws DBusException {
        MediaPlayer mp = getMediaPlayerInterface();
        try {
            mp.Pause();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private MediaPlayer getMediaPlayerInterface() {

        try {
            DBusConnection dbus = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
            return dbus.getRemoteObject("org.bluez", path.getPath(), MediaPlayer.class);
        } catch (DBusException e) {

            e.printStackTrace();
        }
        return null;
    }

    public Properties getProperties() throws DBusException {
        try {
            DBusConnection conn = DBusConnection.getConnection(DBusConnection.DBusBusType.SYSTEM);
            Properties props = conn.getRemoteObject(DEFAULT_BUS,	path.getPath(), Properties.class);
            return props;
        } catch (DBusException e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> T Get(String iface, String key) throws DBusException {
        Properties props = getProperties();
        return props.Get(iface, key);
    }

    public <T> void Set(String iface, String key, T value) throws DBusException {
        Properties props = getProperties();

        props.Set(iface, key, value);
    }

    public String getStatus() throws DBusException {
        try {
            return Get(MEDIAPLAYER_INTERFACE, STATUS);
        } catch (DBusException e) {
            e.printStackTrace();
            return "";
        }
    }
}
