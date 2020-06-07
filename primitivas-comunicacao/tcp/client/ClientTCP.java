import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * ClientTCP
 */
public class ClientTCP {

    public static void main(String[] args) {
        try {
            //O cliente instancia o objeto Socket com o endereço de IP do servidor e o número da porta
            // Então, o construtor de Socket() tenta conectar no servidor
            Socket client = new Socket("localhost", 5555);

            //Uma vez conectados servidor e cliente, a comunicação acontece por meio de I/O streams
            // OutputStream do servidor <---------> InputStream do cliente
            DataInputStream inputStream = new DataInputStream(client.getInputStream());
            String msg = inputStream.readUTF();
            System.out.println(msg);
            DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
            outputStream.writeUTF("Oi servidor, eu sou seu cliente");
            


        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}