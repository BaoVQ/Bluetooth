package obex;

import org.freedesktop.dbus.Tuple;

import java.io.Serializable;

public class CustomTuple<A, B>  extends Tuple implements Serializable {
    public final A a;
    public final B b;

    public CustomTuple(A a, B b) {
        this.a = a;
        this.b = b;
    }
}
