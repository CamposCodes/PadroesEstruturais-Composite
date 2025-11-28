package padroesestruturais.composite;

public class CardapioBar {

    private SecaoCardapio menuPrincipal;

    public SecaoCardapio getMenuPrincipal() {
        if (menuPrincipal == null) {
            throw new NullPointerException("Cardápio não possui menu principal");
        }
        return menuPrincipal;
    }

    public void setMenuPrincipal(SecaoCardapio menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    public String getCardapioCompleto() {
        if (menuPrincipal == null) {
            throw new NullPointerException("Cardápio não possui menu principal");
        }
        return menuPrincipal.getDescricao();
    }

    public double getPrecoTotal() {
        if (menuPrincipal == null) {
            throw new NullPointerException("Cardápio não possui menu principal");
        }
        return menuPrincipal.getPreco();
    }
}
