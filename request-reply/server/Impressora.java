public class Impressora {

    // ID = 0
    public Integer imprimeValor(Object valor) {
        System.out.println("Estou na classe Impressora, no método imprimeValor. O valor é: " + (Integer) valor);
        return (Integer) valor;
    }
    

    // ID = 1
    public Integer somaComDois(Object valor) {
        Integer soma = (Integer) valor + 2;
        System.out.println("Estou na classe Impressora, no método somaComDois. O valor é: " + soma);
        return soma;
    }
}