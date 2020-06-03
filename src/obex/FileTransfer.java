package obex;

import org.bluez.datatypes.TwoTuple;
import org.bluez.exceptions.BluezFailedException;
import org.bluez.exceptions.BluezInvalidArgumentsException;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.types.Variant;

import java.util.Map;

public class FileTransfer implements org.bluez.obex.FileTransfer {
    public void ChangeFolder(String s) throws BluezInvalidArgumentsException, BluezFailedException {

    }

    public void CreateFolder(String s) throws BluezInvalidArgumentsException, BluezFailedException {

    }

    public Map<String, Variant<?>>[] ListFolder() throws BluezFailedException {
        return new Map[0];
    }

    public TwoTuple<DBusPath, Map<String, Variant<?>>> GetFile(String s, String s1) throws BluezInvalidArgumentsException, BluezFailedException {
        return null;
    }

    public TwoTuple<DBusPath, Map<String, Variant<?>>> PutFile(String s, String s1) throws BluezInvalidArgumentsException, BluezFailedException {
        return null;
    }

    public void CopyFile(String s, String s1) throws BluezInvalidArgumentsException, BluezFailedException {

    }

    public void MoveFile(String s, String s1) throws BluezInvalidArgumentsException, BluezFailedException {

    }

    public void Delete(String s) throws BluezInvalidArgumentsException, BluezFailedException {

    }

    public boolean isRemote() {
        return false;
    }

    public String getObjectPath() {
        return null;
    }
}
