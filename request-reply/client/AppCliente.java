import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AppCliente {

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
        try {
            Cliente cliente = new Cliente();
            RemoteObjectReference serverReference;
            serverReference = new RemoteObjectReference(InetAddress.getByName("localhost"), 8888);
            Integer valorForRequest = 10;

            System.out.println("Qual método?");
            System.out.println("0 - imprimeValor()");
            System.out.println("1 - somaComDois()");
            
            int operationId = scanner.nextInt();
            if (operationId == 0 || operationId == 1){
                byte [] reply = cliente.doOperation(serverReference, operationId, Marshaller.marshall(valorForRequest));
                Mensagem resultReply = (Mensagem) Marshaller.unmarshall(reply);
                Integer valorFromReply = (Integer) Marshaller.unmarshall(resultReply.getArguments());
                System.out.println("A resposta chegou! O valor é " + valorFromReply);     
            } else {
                System.out.println("Valor inválido. Digite 0 ou 1");
            }
            
           
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}