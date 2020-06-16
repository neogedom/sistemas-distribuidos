import java.net.SocketException;

public class AppServidor {

    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}