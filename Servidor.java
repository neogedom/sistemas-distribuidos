import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

    private int serverPort = 8888;
    public static int messageLenght = 1000;
    DatagramSocket socket;
    Mensagem msg;

    public Servidor() throws SocketException {
        socket = new DatagramSocket(serverPort);

        while (true) {
            DatagramPacket packetFromRequest = getRequest();
            
            // Criação dinâmica de thread
            ExecutorService executor = Executors.newCachedThreadPool();
            executor.execute(new Worker(packetFromRequest.getData(), packetFromRequest.getAddress(), packetFromRequest.getPort()));
        }
    }

    public DatagramPacket getRequest() {
        try {
            byte[] buffer = new byte[messageLenght];
            DatagramPacket packetFromRequest = new DatagramPacket(buffer, buffer.length);
            socket.receive(packetFromRequest);

            return packetFromRequest;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendReply (byte[] reply, InetAddress clientAddress, int clientPort) {
        // msg = new Mensagem(requestId, result);
        // byte [] buffer = msg.marshall();
        DatagramPacket packetForReply = new DatagramPacket(reply, reply.length);
        try {
            socket.send(packetForReply);
        } catch (IOException e) {
           e.printStackTrace();
        }

    }

    class Worker implements Runnable {
        InetAddress clientAddress;
        int clientPort;
        int requestId;
        Mensagem msg;
        
        public Worker(byte [] dataFromRequest, InetAddress clientAddress, int clientPort) {
            
            this.msg = (Mensagem) Marshaller.unmarshall(dataFromRequest);
            this.clientAddress = clientAddress;
            this.clientPort = clientPort;
            this.requestId = msg.getRequestId();
        }

        public void run() {
            Object argumento = Marshaller.unmarshall(msg.getArguments());

            Impressora impressora = new Impressora();
            byte[] resultadoOperacao = Marshaller.marshall(impressora.imprimeValor(argumento));

            Mensagem resultMensagem = new Mensagem(requestId, resultadoOperacao);
            byte[] replyToSend = resultMensagem.marshall();
            sendReply(replyToSend, clientAddress, clientPort);
        }
    }

    
}