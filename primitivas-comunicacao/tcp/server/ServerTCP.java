import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerTCP
 */
public class ServerTCP {

    public static void main(String[] args) {
        try {
            // Um objeto ServerSocket é instanciado pelo servidor com um nº de porta para comunicação (bind)
            ServerSocket server = new ServerSocket(5555);
            
            System.out.println("Aguardando conexão");
            while(true) {   
                // O servidor chama o método accept() e espera até que o cliente se conecte na porta dada (listen)
                Socket server_client = server.accept();
                //Uma vez conectados servidor e cliente, a comunicação acontece por meio de I/O streams
                // OutputStream do servidor <---------> InputStream do cliente
                DataOutputStream outputStream = new DataOutputStream(server_client.getOutputStream());
                outputStream.writeUTF("Olá, eu sou seu servidor");
                DataInputStream inputStream = new DataInputStream(server_client.getInputStream());
                System.out.println(inputStream.readUTF());
                outputStream.flush();
                outputStream.close();
                //server_client.close();
                //server.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}