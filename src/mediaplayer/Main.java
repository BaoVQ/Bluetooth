package mediaplayer;

import org.freedesktop.dbus.exceptions.DBusException;

public class Main {
    public static void main(String[] args) {
        //bluetoothd -C -p a2dp, avrcp

        try {
            BTMediaPlayer bluetoothMediaPlayer = Utils.getMediaPlayer();

            bluetoothMediaPlayer.play();
        } catch (DBusException e) {
            e.printStackTrace();
        }

    }
}
