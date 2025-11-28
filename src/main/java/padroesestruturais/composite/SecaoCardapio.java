package padroesestruturais.composite;

import java.util.ArrayList;
import java.util.List;

public class SecaoCardapio implements ItemCardapio {

    private String nome;
    private List<ItemCardapio> itens;
    private boolean ehCombo;

    public SecaoCardapio(String nome) {
        this.nome = nome;
        this.itens = new ArrayList<>();
        this.ehCombo = false;
    }

    public SecaoCardapio(String nome, boolean ehCombo) {
        this.nome = nome;
        this.itens = new ArrayList<>();
        this.ehCombo = ehCombo;
    }

    public String getNome() {
        return nome;
    }

    public void addItem(ItemCardapio item) {
        this.itens.add(item);
    }

    public List<ItemCardapio> getItens() {
        return itens;
    }

    public boolean isEhCombo() {
        return ehCombo;
    }

    @Override
    public double getPreco() {
        double precoTotal = 0;
        for (ItemCardapio item : itens) {
            precoTotal += item.getPreco();
        }
        
        // Combos têm 10% de desconto
        if (ehCombo && precoTotal > 0) {
            precoTotal = precoTotal * 0.9;
        }
        
        return precoTotal;
    }

    @Override
    public String getDescricao() {
        StringBuilder descricao = new StringBuilder();
        
        if (ehCombo) {
            descricao.append("Combo: ").append(nome);
            if (!itens.isEmpty()) {
                descricao.append(" - R$ ").append(String.format("%.2f", getPreco()).replace(".", ","));
            }
            descricao.append("\n");
        } else {
            descricao.append("Seção: ").append(nome).append("\n");
        }
        
        for (ItemCardapio item : itens) {
            descricao.append(item.getDescricao());
        }
        
        return descricao.toString();
    }
}
