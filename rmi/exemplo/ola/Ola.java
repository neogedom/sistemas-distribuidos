package exemplo.ola;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Ola extends Remote {
    String digaOla () throws RemoteException;
}
