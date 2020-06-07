import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/**
 * Cliente
 */
public class Cliente {

    DatagramSocket socket;
    public static int messageLenght = 1000;

    Cliente() throws SocketException {
        socket = new DatagramSocket();
    }

    public byte[] doOperation(RemoteObjectReference objectReference, int operationId, byte[] arguments) {
        InetAddress serverIp = objectReference.getIpAddress();
        int serverPort = objectReference.getPort();
        Mensagem msg = new Mensagem(objectReference, operationId, arguments);
        byte[] messageBuff = msg.marshall();
        DatagramPacket packetForRequest = new DatagramPacket(messageBuff, messageBuff.length, serverIp, serverPort);

        try {
            socket.send(packetForRequest);

            //Recebendo a resposta
            byte [] buffer = new byte[messageLenght];
            DatagramPacket packetFromReply = new DatagramPacket(buffer, buffer.length);
            socket.receive(packetFromReply);
            

            return packetFromReply.getData();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
        
    }
}