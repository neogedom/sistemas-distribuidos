import java.io.Serializable;
import java.net.InetAddress;

public class RemoteObjectReference implements Serializable {
    private static final long serialVersionUID = 1L;

    private InetAddress ipAddress;
    private int port;
    private int time;
    private int objectNumber;
    private Class iface;

    public RemoteObjectReference(InetAddress ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }
        
    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public int getPort () {
        return port;
    }


    
}