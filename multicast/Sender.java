import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Sender {

    public static void main (String [] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");

        try {
            InetAddress group = InetAddress.getByName("225.6.7.8");

            MulticastSocket socket = new MulticastSocket();

            String msg = "Multicast UDP é top! ";

            DatagramPacket dPacket = new DatagramPacket(msg.getBytes(), msg.length(), group, 3456);

            socket.send(dPacket);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}