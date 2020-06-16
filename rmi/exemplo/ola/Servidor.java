package exemplo.ola;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor implements Ola {

    public Servidor() {

    }

    @Override
    public String digaOla() throws RemoteException {
        
        return "Olá mundo!";
    }

    public static void main(String[] args) {
        try {
            Servidor obj = new Servidor();

            // Definindo o nome do servidor
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");

            Ola stub = (Ola) UnicastRemoteObject.exportObject(obj, 0);  
           
            // Criando servico de registro
            Registry registry = LocateRegistry.createRegistry(12345);

            // Registrando objeto distribuıdo
            registry.bind("Ola", stub);

            System.out.println("Servidor está pronto");
        } catch (Exception e) {

            System.err.println("Exceção no servidor: " +  e.toString());
            e.printStackTrace();
        }
       

    }
    
}