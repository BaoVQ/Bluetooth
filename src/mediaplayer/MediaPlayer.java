package mediaplayer;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;

@DBusInterfaceName(value = "org.bluez.MediaPlayer1")

public interface MediaPlayer extends DBusInterface {
    void Play() ;

    void Pause();

    void Stop();

    void Next();

    void Previous();
}
