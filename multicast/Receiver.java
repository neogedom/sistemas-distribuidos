import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {

    public static void main(String[] args) {
        // Garantia de que estamos usando IPv4. Multicast não funciona mais em IPv6
     System.setProperty("java.net.preferIPv4Stack", "true"); 

     MulticastSocket socket = null;

     try {
         //O intervalo de IPs multicast é de 254.0.0.0 a 239.255.255.255 (ou seja, classe D do endereçamento IP)
         InetAddress group = InetAddress.getByName("225.6.7.8");

          //Criação do Socket Multicast anexado à porta para onde será mandado o datagrama
        socket = new MulticastSocket(3456);

         //Entrada no grupo de multicast como cliente para o endereço de IP declarado na variável group
         socket.joinGroup(group);

         //Organizando um listener que "ouvirá" na porta anexada ao socket 10 vezes
         while(true) {

            // Criando um array de bytes para receber que determinará o tamanho que o pacote deve ser em bytes
            byte[] buffer = new byte[100];
            // Preparando o DatagramPacket que irá receber o pacote vindo do Sender
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Recebendo o datagrama. Essa é uma operação bloqueante que não terminará até que o pacote seja recebido.
            socket.receive(packet);
            if (new String(buffer).equals("tchau")) {
                System.out.println("Terminando o programa");
                break;
            }

            System.out.println(new String(buffer));
         }
         
         
     } catch (IOException e) {
         e.printStackTrace();
    } finally {
        socket.close();
    }

    }

}