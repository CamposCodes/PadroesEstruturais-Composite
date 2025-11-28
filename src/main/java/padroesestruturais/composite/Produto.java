package padroesestruturais.composite;

public class Produto implements ItemCardapio {

    private String nome;
    private double preco;
    private int calorias;

    public Produto(String nome, double preco, int calorias) {
        this.nome = nome;
        this.preco = preco;
        this.calorias = calorias;
    }

    public String getNome() {
        return nome;
    }

    public int getCalorias() {
        return calorias;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public String getDescricao() {
        return "Produto: " + nome + " - R$ " + String.format("%.2f", preco).replace(".", ",") + 
               " (" + calorias + " kcal)\n";
    }
}
