import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP {
    public static void main(String... args) {
        DatagramSocket socket = null;
        byte[] buffRequest = new byte[1000];
        byte[] buffResponse = null;
        try {
            //Conecte usando usando um socket UDP em uma porta específica
            socket = new DatagramSocket(3000);

            //Objetos DatagramPacket (pacotes autocontidos) são usados para se comunicar por meio de DatagramSockets
            // Todo DatagramPacket consiste em um buffer de dados, um host remoto para o qual os dados precisam ser enviados
            // e uma porta em que o agente remoto será ouvido
            
            System.out.println("Aguardando cliente...");
            while (true) {
                // Prepara o DatagramPacket (datagrama) que encapsulará o datagrama vindo do cliente (request)
                DatagramPacket dgFromClient = new DatagramPacket(buffRequest, buffRequest.length);
                // Recebe a mensagem do cliente dentro do datagrama
                socket.receive(dgFromClient);
                System.out.println("Cliente: " + bytesToString(dgFromClient.getData()));

                String string = "Okay, mensagem recebida.";
                buffResponse = string.getBytes();
                // Prepara o DatagramPacket (datagrama) que conterá a mensagem de resposta ao cliente (response)
                DatagramPacket dgFromServer = new DatagramPacket(buffResponse, buffResponse.length, dgFromClient.getAddress(), dgFromClient.getPort());
                // Envia o datagrama
                socket.send(dgFromServer);
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }


    // Método de conversão da mensagem que vem em bytes para o formato String
    public static StringBuilder bytesToString (byte [] msgInBytes) {

        if (msgInBytes == null) {
            return null;
        }

        StringBuilder string = new StringBuilder();
        for (byte b : msgInBytes) {
            if (b != 0) {
                string.append((char) b);
            }
        }

        return string;
    }
}