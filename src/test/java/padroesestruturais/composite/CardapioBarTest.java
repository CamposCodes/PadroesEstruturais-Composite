package padroesestruturais.composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardapioBarTest {

    @Test
    void deveRetornarCardapioCompletoDoBar() {
        // Seção Bebidas Quentes
        SecaoCardapio bebidasQuentes = new SecaoCardapio("Bebidas Quentes");
        Produto cafe = new Produto("Café Expresso", 5.00, 10);
        Produto cappuccino = new Produto("Cappuccino", 8.50, 150);
        Produto chocolateQuente = new Produto("Chocolate Quente", 9.00, 250);
        bebidasQuentes.addItem(cafe);
        bebidasQuentes.addItem(cappuccino);
        bebidasQuentes.addItem(chocolateQuente);

        // Seção Bebidas Geladas
        SecaoCardapio bebidasGeladas = new SecaoCardapio("Bebidas Geladas");
        Produto sucoLaranja = new Produto("Suco de Laranja Natural", 7.00, 120);
        Produto refrigerante = new Produto("Refrigerante", 6.00, 140);
        bebidasGeladas.addItem(sucoLaranja);
        bebidasGeladas.addItem(refrigerante);

        // Categoria Bebidas (contém subcategorias)
        SecaoCardapio bebidas = new SecaoCardapio("Bebidas");
        bebidas.addItem(bebidasQuentes);
        bebidas.addItem(bebidasGeladas);

        // Seção Salgados
        SecaoCardapio salgados = new SecaoCardapio("Salgados");
        Produto coxinha = new Produto("Coxinha", 6.50, 200);
        Produto pastel = new Produto("Pastel", 7.00, 220);
        salgados.addItem(coxinha);
        salgados.addItem(pastel);

        // Combo Café da Manhã
        SecaoCardapio comboCafe = new SecaoCardapio("Café da Manhã", true);
        Produto paoQueijo = new Produto("Pão de Queijo", 4.00, 180);
        Produto cafeSimples = new Produto("Café", 5.00, 10);
        comboCafe.addItem(paoQueijo);
        comboCafe.addItem(cafeSimples);

        // Seção Combos
        SecaoCardapio combos = new SecaoCardapio("Combos");
        combos.addItem(comboCafe);

        // Menu Principal
        SecaoCardapio menuPrincipal = new SecaoCardapio("Menu Principal - Café & Bar");
        menuPrincipal.addItem(bebidas);
        menuPrincipal.addItem(salgados);
        menuPrincipal.addItem(combos);

        CardapioBar cardapio = new CardapioBar();
        cardapio.setMenuPrincipal(menuPrincipal);

        assertEquals("Seção: Menu Principal - Café & Bar\n" +
                "Seção: Bebidas\n" +
                "Seção: Bebidas Quentes\n" +
                "Produto: Café Expresso - R$ 5,00 (10 kcal)\n" +
                "Produto: Cappuccino - R$ 8,50 (150 kcal)\n" +
                "Produto: Chocolate Quente - R$ 9,00 (250 kcal)\n" +
                "Seção: Bebidas Geladas\n" +
                "Produto: Suco de Laranja Natural - R$ 7,00 (120 kcal)\n" +
                "Produto: Refrigerante - R$ 6,00 (140 kcal)\n" +
                "Seção: Salgados\n" +
                "Produto: Coxinha - R$ 6,50 (200 kcal)\n" +
                "Produto: Pastel - R$ 7,00 (220 kcal)\n" +
                "Seção: Combos\n" +
                "Combo: Café da Manhã - R$ 8,10\n" +
                "Produto: Pão de Queijo - R$ 4,00 (180 kcal)\n" +
                "Produto: Café - R$ 5,00 (10 kcal)\n", cardapio.getCardapioCompleto());
    }

    @Test
    void deveCalcularPrecoTotalComDesconto() {
        SecaoCardapio combo = new SecaoCardapio("Lanche Completo", true);
        Produto sanduiche = new Produto("Sanduíche", 15.00, 400);
        Produto batata = new Produto("Batata Frita", 10.00, 350);
        combo.addItem(sanduiche);
        combo.addItem(batata);

        // Preço sem desconto: 25.00
        // Preço com 10% desconto: 22.50
        assertEquals(22.50, combo.getPreco(), 0.01);
    }

    @Test
    void deveRetornarExcecaoCardapioSemMenuPrincipal() {
        try {
            CardapioBar cardapio = new CardapioBar();
            cardapio.getCardapioCompleto();
            fail();
        }
        catch (NullPointerException e) {
            assertEquals("Cardápio não possui menu principal", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoAoCalcularPrecoSemMenu() {
        try {
            CardapioBar cardapio = new CardapioBar();
            cardapio.getPrecoTotal();
            fail();
        }
        catch (NullPointerException e) {
            assertEquals("Cardápio não possui menu principal", e.getMessage());
        }
    }

    @Test
    void deveRetornarSecaoVazia() {
        SecaoCardapio secaoVazia = new SecaoCardapio("Sobremesas");
        
        CardapioBar cardapio = new CardapioBar();
        cardapio.setMenuPrincipal(secaoVazia);

        assertEquals("Seção: Sobremesas\n", cardapio.getCardapioCompleto());
        assertEquals(0.0, cardapio.getPrecoTotal(), 0.01);
    }

    @Test
    void deveRetornarEstruturaComQuatroNiveis() {
        Produto item = new Produto("Café Gourmet", 12.00, 15);
        
        SecaoCardapio nivel3 = new SecaoCardapio("Cafés Premium");
        nivel3.addItem(item);
        
        SecaoCardapio nivel2 = new SecaoCardapio("Cafés Especiais");
        nivel2.addItem(nivel3);
        
        SecaoCardapio nivel1 = new SecaoCardapio("Bebidas");
        nivel1.addItem(nivel2);
        
        SecaoCardapio raiz = new SecaoCardapio("Menu Completo");
        raiz.addItem(nivel1);

        CardapioBar cardapio = new CardapioBar();
        cardapio.setMenuPrincipal(raiz);

        assertEquals("Seção: Menu Completo\n" +
                "Seção: Bebidas\n" +
                "Seção: Cafés Especiais\n" +
                "Seção: Cafés Premium\n" +
                "Produto: Café Gourmet - R$ 12,00 (15 kcal)\n", cardapio.getCardapioCompleto());
    }

    @Test
    void deveAplicarDescontoApenasEmCombos() {
        SecaoCardapio secaoNormal = new SecaoCardapio("Lanches");
        Produto item1 = new Produto("X-Burger", 20.00, 500);
        secaoNormal.addItem(item1);
        
        SecaoCardapio combo = new SecaoCardapio("Combo X-Burger", true);
        Produto item2 = new Produto("X-Burger", 20.00, 500);
        combo.addItem(item2);

        assertEquals(20.00, secaoNormal.getPreco(), 0.01);
        assertEquals(18.00, combo.getPreco(), 0.01);
    }
}
