import java.io.Serializable;

public class Mensagem implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private static int next = 0;
    private int messageType;
    private int requestId;
    private RemoteObjectReference remoteReference;
    private int operationId;
    private byte[] arguments;

    // Construtor para mensagens de requisição
    public Mensagem(RemoteObjectReference remoteReference, int operationId, byte[] arguments) {
        this.messageType = 0;
        this.requestId = next++; // Cada nova mensagem de requisição, incrementa-se o requestId
        this.remoteReference = remoteReference;
        this.operationId = operationId;
        this.arguments = arguments;
    }

    // Construtor para mensagens de resposta
    public Mensagem( int requestId, byte[] result) {
        this.messageType = 1;
        this.arguments = result;
    }

    // Se auto empacota (marshalling) para transmissão pela rede
    // Útil para comunicação UDP
    public byte[] marshall() {
        return Marshaller.marshall(this);
    }

    // Desempacota (unmarshalling) um array de bytes e converte para a classe
    public Mensagem unmarshall(byte[] bytes) {
        return (Mensagem) Marshaller.unmarshall(bytes);
    }
    
    // Retorna o tamanho do estado "marshalled"
    public int length() {
        return marshall().length;
    }

    public byte[] getArguments () {
        return arguments;
    }

    public int getMessageType() {
        return messageType;
    }

    public int getRequestId() {
        return requestId;
    }

    public RemoteObjectReference getRemoteReference() {
        return remoteReference;
    }

    public int getOperationId() {
        return operationId;
    }

    


    
    
}