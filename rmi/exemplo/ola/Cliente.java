package exemplo.ola;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Cliente {
    
    private Cliente() {

    }

    public static void main(String[] args) {

        try {
            // Obtendo refer^encia do servico de registro
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12345);
            
            // Procurando pelo objeto distribu´ıdo registrado previamente com o Ola
            Ola stub = (Ola) registry.lookup("Ola");

            // Invocando m´etodos do objeto distribuıdo
            String resposta = stub.digaOla();
            System.out.println("Resposta: " + resposta);
            
        } catch (Exception e) {
            System.err.println("Exceção no cliente: " + e.getMessage());
        }
    }
}