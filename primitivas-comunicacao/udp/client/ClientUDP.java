import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * ClientUDP
 */
public class ClientUDP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatagramSocket socket = null;
        byte [] buffRequest = null;


        try {
            socket = new DatagramSocket();
            // O DatagramPacket a ser enviado espera um buffer, um tamanaho, um host do tipo InetAddress e uma porta
            
            while (true) {
                
                String msg = scanner.nextLine();
                if (msg.equals("tchau")){
                    break;
                }
                buffRequest = msg.getBytes();

                //Pega o endereço localhost e encapsula em um objeto do tipo InetAddress
                InetAddress serverHost = InetAddress.getByName("localhost");
                //Cria um DatagramPacket (datagrama) e coloca a mensagem, o endereço do servidor e a porta (request)   
                DatagramPacket dgFromClient = new DatagramPacket(buffRequest, buffRequest.length, serverHost, 3000);
                // Envia o datagrama para o servidor
                socket.send(dgFromClient);

                
                byte [] buffResponse = new byte[1000];
                //Cria um DatagramPacket (datagrama) para encapsular a mensagem a ser recebida (response)
                DatagramPacket dgFromServer = new DatagramPacket(buffResponse, buffResponse.length);
                //Recebe a mensagem e coloca dentro do datagrama
                socket.receive(dgFromServer);
                System.out.println("Servidor: " + bytesToString(dgFromServer.getData()));
            }
           
            scanner.close();
            socket.close();
            

            
        } catch (IOException e) {
           e.printStackTrace();
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